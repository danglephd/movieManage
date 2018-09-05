package org.vn.movieviewer.view;

import com.google.api.client.json.JsonFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.log4j.Logger;
import org.vn.movieviewer.config.JsonUtils;
import org.vn.movieviewer.config.Utils;
import org.vn.movieviewer.dto.IMDbResponseSearchDto;
import org.vn.movieviewer.dto.MovieIMDbDto;

/**
 *
 * @author danglph
 */
public class IMDbAPI {

    private static Logger logger = Logger.getLogger(IMDbAPI.class);

    private final String iMDbURL = "https://www.omdbapi.com/?";
    private String userAgent = "Mozilla/5.0";
    private String aPIKey = "";
    private String returnType = "";
    public static final String TYPE_MOVIE = "movie";
    public static  final String TYPE_SERIES = "series";
    public static  final String TYPE_EPISODE = "episode";

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
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(response.toString());
            MovieIMDbDto movieIMDbDto = JsonUtils.jSon2Object(element, MovieIMDbDto.class);
            logger.debug(movieIMDbDto);
            return movieIMDbDto;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * @param movieTitle
     *  @param type
     *          value : movie, series, episode
     *  @param year
     *          value : 0 -> dismiss
     *  @param page
     *          value : 0 -> dismiss
     * @return IMDbSearchDto
    */
    public IMDbResponseSearchDto search(String movieTitle, String type, int year, int page) {
        try {
            String encodeUrlStr = buildURLRequestSearch(this.getaPIKey(), 
                    URLEncoder.encode(movieTitle, "UTF-8"), 
                    type,
                    this.getReturnType(), 
                    year, 
                    page);
            URL url = new URL(encodeUrlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.addRequestProperty("User-Agent", this.getUserAgent());
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            logger.debug("Sending 'GET' request to URL : " + url);
//            print_https_cert(conn);
//            int responseCode = conn.getResponseCode();
//            logger.debug("Response Code : " + responseCode);
//            if(responseCode != 200){
//                logger.error("Response Code : " + responseCode);
//                return null;
//            }
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            conn.disconnect();
            in.close();
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(response.toString());
            IMDbResponseSearchDto responseIMDbDto = JsonUtils.jSon2Object(element, IMDbResponseSearchDto.class);
            logger.debug(responseIMDbDto);
            return responseIMDbDto;
        } catch (JsonSyntaxException | IOException e) {
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

    /*//>>>
    //returnType : "json", "xml"
    plot: "short", "full"
    type: movie, series, episode
    //*/
    private String buildURLRequestSearch(String aPIKey, 
                                        String movieTitle,
                                        String type, 
                                        String returnType, 
                                        int yearOfRelease, 
                                        int page) {
//        this.setaPIKey(aPIKey);
//        this.setReturnType(returnType);
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.iMDbURL);
        urlBuilder.append("apikey=").append(aPIKey);
        if(movieTitle == null || movieTitle.equals("") || movieTitle.length() < 2){
            logger.error("No movie title to search.");
            return null;
        }
        urlBuilder.append("&s=").append(movieTitle);            
        if(!type.equals("")){
            urlBuilder.append("&type=").append(type);
        }
        if(yearOfRelease > 0){
            urlBuilder.append("&y=").append(yearOfRelease);
        }
        if(!returnType.equals("")){
            urlBuilder.append("&r=").append(returnType);
        }
        if(page > 0){
            urlBuilder.append("&page=").append(page);
        }
        return urlBuilder.toString();
//        return "http://www.omdbapi.com/?apikey=faeb0cf9&t=peter rabbit";//&y=2018&r=json&plot=short";
    }
    
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
