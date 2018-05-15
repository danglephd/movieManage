package org.vn.movieviewer.view;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

/**
 *
 * @author Code4LifeVn
 */
public class GoogleTranslator {

    private final String googleTranslatorURL = "https://translate.google.com.vn/translate_a/single?";
    private LANGUAGE srcLang = LANGUAGE.ENGLISH;
    private LANGUAGE destLang = LANGUAGE.VIETNAMESE;
    private static final String USER_AGENT = "Mozilla/5.0";
    private String userAgent = "Mozilla/5.0";
    private String at = "at";

    //url = "https://translate.google.com.vn/translate_a/single?client=t&sl=en&tl=vi&hl=en
    //&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8
    //&oe=UTF-8&source=bh&ssel=0&tsel=0&kc=1&tk=549287.922957&q=Hey!%20Shut%20off%20the%20hose!";
    public String translate(String query) {
        try {
            URL url = new URL(this.buildURLRequestWith(query));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("User-Agent", this.userAgent);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30000);
            conn.connect();

            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            StringBuilder contentBuilder = new StringBuilder();

            JsonParser jsonParser = new JsonParser();
            JsonArray elementArray = jsonParser.parse(response.toString()).getAsJsonArray();
            if (elementArray.size() <= 0) {
                return "";
            }
            JsonArray elementArray2 = jsonParser.parse(elementArray.get(0).toString()).getAsJsonArray();

            for (JsonElement element : elementArray2) {
                JsonArray elementArray3 = jsonParser.parse(element.toString()).getAsJsonArray();
                contentBuilder.append(elementArray3.get(0));
//                System.out.println(elementArray3.get(0));
//                System.out.println(elementArray3.get(1));
            }
            return contentBuilder.toString().trim().replace(" .", ". ");
        } catch (Exception e) {
            return "";
        }
    }

    private String buildURLRequestWith(String query) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.googleTranslatorURL);
        urlBuilder.append("client=t");
        urlBuilder.append("&sl=").append(this.srcLang);
        urlBuilder.append("&tl=").append(this.destLang);
        urlBuilder.append("&hl=").append(this.srcLang);
        urlBuilder.append("&dt=").append(this.at);
        urlBuilder.append("&dt=").append("bd");
        urlBuilder.append("&dt=").append("ex");
        urlBuilder.append("&dt=").append("ld");
        urlBuilder.append("&dt=").append("md");
        urlBuilder.append("&dt=").append("qca");
        urlBuilder.append("&dt=").append("rw");
        urlBuilder.append("&dt=").append("rm");
        urlBuilder.append("&dt=").append("ss");
        urlBuilder.append("&dt=").append("t");
        urlBuilder.append("&ie=").append("UTF-8");
        urlBuilder.append("&oe=").append("UTF-8");
        urlBuilder.append("&source=").append("bh");
        urlBuilder.append("&ssel=").append("0");
        urlBuilder.append("&tsel=").append("0");
        urlBuilder.append("&kc=").append("1");
        urlBuilder.append("&tk=").append("549287.922957");
        urlBuilder.append("&sc=1");
        String queryEncoded = "";
        try {
            queryEncoded = URLEncoder.encode(query, "UTF-8");
        } catch (Exception e) {
        }
        urlBuilder.append("&q=").append(queryEncoded);
        return urlBuilder.toString();
    }

    public static String sendGet(String strtoTransl) {
        try {
            String url = "https://translate.google.com.vn/translate_a/single?client=z&sl=%s&tl=%s-CN&ie=UTF-8&oe=UTF-8&dt=t&dt=rm&q=";

            url += URLEncoder.encode(strtoTransl, "UTF-8");

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            // add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonParser jsonParser = new JsonParser();
            JsonArray elementArray = jsonParser.parse(response.toString()).getAsJsonArray();
            if (elementArray.size() <= 0) {
                return "";
            }
            JsonArray elementArray2 = jsonParser.parse(elementArray.get(0).toString()).getAsJsonArray();

            StringBuilder contentBuilder = new StringBuilder();
            for (JsonElement element : elementArray2) {
                JsonArray elementArray3 = jsonParser.parse(element.toString()).getAsJsonArray();
                contentBuilder.append(elementArray3.get(0).toString());
            }
            return contentBuilder.toString();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

        return "";
    }

    public LANGUAGE getSrcLang() {
        return srcLang;
    }

    public void setSrcLang(LANGUAGE srcLang) {
        this.srcLang = srcLang;
    }

    public LANGUAGE getDestLang() {
        return destLang;
    }

    public void setDestLang(LANGUAGE destLang) {
        this.destLang = destLang;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public static enum LANGUAGE {
        ENGLISH("en"),
        VIETNAMESE("vi"),
        AUTO("auto");

        private String lang = "";

        private LANGUAGE(String lang) {
            this.lang = lang;
        }

        @Override
        public String toString() {
            return this.lang;
        }
    }

}
