/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.controller;

import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.apache.log4j.Logger;
import org.vn.movieviewer.config.JsonUtils;

/**
 *
 * @author danglph
 */
public class APIRequest {

    private static Logger logger = Logger.getLogger(APIRequest.class);

    private String sURLRequest;
    private String aPIKey = "";
    private String userAgent = "Mozilla/5.0";

    public APIRequest() {
    }

    public APIRequest(String sURLRequest, String aPIKey) {
        this.sURLRequest = sURLRequest;
        this.aPIKey = aPIKey;
    }
    
    /**
     * key : . Ex: &s, &type,...
    **/
    private String buildURLRequest(Map<String, Object> mapValue, String aPIKey) {

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(this.getsURLRequest());
        if(!aPIKey.equals("")){
            urlBuilder.append("apikey=").append(aPIKey);            
        }
        for (Map.Entry<String, Object> entry : mapValue.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            urlBuilder.append(key).append(value);
        }
        
        return urlBuilder.toString();
    }
    
    
    public JsonElement callAPIbyGET(Map<String, Object> mapValue) {
        try {
            String encodeUrlStr = this.buildURLRequest(mapValue, this.getaPIKey());
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
            JsonElement element = JsonUtils.parseStr(response.toString());
//            MovieIMDbDto movieIMDbDto = Utils.jSon2Object(element, MovieIMDbDto.class);
//            logger.debug(movieIMDbDto);
            return element;
        } catch (Exception e) {
            getLogger().error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * @return the aPIKey
     */
    public String getaPIKey() {
        return aPIKey;
    }

    /**
     * @param aPIKey the aPIKey to set
     */
    public void setaPIKey(String aPIKey) {
        this.aPIKey = aPIKey;
    }

    /**
     * @return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @return the logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * @return the sURLRequest
     */
    public String getsURLRequest() {
        return sURLRequest;
    }

    /**
     * @param aLogger the logger to set
     */
    public static void setLogger(Logger aLogger) {
        logger = aLogger;
    }

    /**
     * @param sURLRequest the sURLRequest to set
     */
    public void setsURLRequest(String sURLRequest) {
        this.sURLRequest = sURLRequest;
    }

    /**
     * @param userAgent the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
