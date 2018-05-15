package org.vn.movieviewer.view;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.log4j.Logger;
import org.vn.movieviewer.config.Utils;
import org.vn.movieviewer.dto.MovieIMDbDto;

/**
 *
 * @author danglph
 */
public class IMDbAPI {

    private static Logger logger = Logger.getLogger(IMDbAPI.class);

    private final String iMDbURL = "http://www.omdbapi.com/?";
    private String userAgent = "Mozilla/5.0";
    private String aPIKey = "";
    private String returnType = "";

    public IMDbAPI() {
        this.aPIKey = "faeb0cf9";
        this.returnType = "json";
    }

    public MovieIMDbDto searchByTitle(String movieTitle, int year, String plot) {
        try {
            String encodeUrlStr = this.buildURLRequestWith(this.getaPIKey(), URLEncoder.encode(movieTitle, "UTF-8"), this.getReturnType(), year, plot);
            URL url = new URL(encodeUrlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("User-Agent", this.getUserAgent());
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setConnectTimeout(30000);
            conn.connect();
//            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            System.out.println("\nSending 'GET' request to URL : " + url);
            print_https_cert(conn);
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            StringBuilder contentBuilder = new StringBuilder();

            JsonParser jsonParser = new JsonParser();
//            JsonArray elementArray = jsonParser.parse(response.toString()).getAsJsonArray();
//            if (elementArray.size() <= 0) {
//                return "";
//            }
//            JsonArray elementArray2 = jsonParser.parse(elementArray.get(0).toString()).getAsJsonArray();
            JsonElement element = jsonParser.parse(response.toString());
            MovieIMDbDto movieIMDbDto = Utils.jSon2Object(element, MovieIMDbDto.class);
            logger.debug(movieIMDbDto);
//            for (JsonElement element : elementArray2) {
//                JsonArray elementArray3 = jsonParser.parse(element.toString()).getAsJsonArray();
//                contentBuilder.append(elementArray3.get(0));
////                System.out.println(elementArray3.get(0));
////                System.out.println(elementArray3.get(1));
//            }
            return movieIMDbDto;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    private void print_https_cert(HttpURLConnection con) {

        if (con != null) {

            try {

                System.out.println("Response Code : " + con.getResponseCode());
//                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

//                Certificate[] certs = con.getServerCertificates();
//                for (Certificate cert : certs) {
//                    System.out.println("Cert Type : " + cert.getType());
//                    System.out.println("Cert Hash Code : " + cert.hashCode());
//                    System.out.println("Cert Public Key Algorithm : "
//                            + cert.getPublicKey().getAlgorithm());
//                    System.out.println("Cert Public Key Format : "
//                            + cert.getPublicKey().getFormat());
//                    System.out.println("\n");
//                }

            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /*//>>>
    //returnType : "json", "xml"
    plot: "short", "full"
    //*/
    private String buildURLRequestWith(String aPIKey, String movieTitle, String returnType, int yearOfRelease, String plot) {
        this.setaPIKey(aPIKey);
        this.setReturnType(returnType);
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.iMDbURL);
        urlBuilder.append("apikey=").append(this.getaPIKey());
        urlBuilder.append("&t=").append(movieTitle);
        urlBuilder.append("&y=").append(yearOfRelease);
        urlBuilder.append("&r=").append(this.getReturnType());
        urlBuilder.append("&plot=").append(plot);
        return urlBuilder.toString();
//        return "http://www.omdbapi.com/?apikey=faeb0cf9&t=peter rabbit";//&y=2018&r=json&plot=short";
    }

//    public static String sendGet(String strtoTransl) {
//        try {
//            String url = "https://translate.google.com.vn/translate_a/single?client=z&sl=%s&tl=%s-CN&ie=UTF-8&oe=UTF-8&dt=t&dt=rm&q=";
//
//            url += URLEncoder.encode(strtoTransl, "UTF-8");
//
//            URL obj = new URL(url);
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//            // optional default is GET
//            con.setRequestMethod("GET");
//
//            // add request header
//            con.setRequestProperty("User-Agent", USER_AGENT);
//
//            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : " + url);
//            System.out.println("Response Code : " + responseCode);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            JsonParser jsonParser = new JsonParser();
//            JsonArray elementArray = jsonParser.parse(response.toString()).getAsJsonArray();
//            if (elementArray.size() <= 0) {
//                return "";
//            }
//            JsonArray elementArray2 = jsonParser.parse(elementArray.get(0).toString()).getAsJsonArray();
//
//            StringBuilder contentBuilder = new StringBuilder();
//            for (JsonElement element : elementArray2) {
//                JsonArray elementArray3 = jsonParser.parse(element.toString()).getAsJsonArray();
//                contentBuilder.append(elementArray3.get(0).toString());
//            }
//            return contentBuilder.toString();
//        } catch (Exception e) {
//            // TODO: handle exception
//            System.out.println(e.getMessage());
//        }
//
//        return "";
//    }
    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * @return the aPIKey
     */
    public String getaPIKey() {
        return aPIKey;
    }

    /**
     * @return the returnType
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * @param aPIKey the aPIKey to set
     */
    public void setaPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    /**
     * @param returnType the returnType to set
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
