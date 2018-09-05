/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.dto;

/**
 *
 * @author danglph
 */
public class IMDbResponseSearchDto {
    public static String SUCCESS = "True";
    public static String FAIL = "False";
    
    private String Response;
    private String totalResults;
    private String Error;
    private IMDbSearchDto[] Search;

    public IMDbResponseSearchDto(String Response, String totalResults, String Error, IMDbSearchDto[] Search) {
        this.Response = Response;
        this.totalResults = totalResults;
        this.Error = Error;
        this.Search = Search;
    }

    public IMDbResponseSearchDto() {
    }

    /**
     * @return the Response
     */
    public String getResponse() {
        return Response;
    }

    /**
     * @return the totalResults
     */
    public String getTotalResults() {
        return totalResults;
    }

    /**
     * @return the Error
     */
    public String getError() {
        return Error;
    }

    /**
     * @return the Search
     */
    public IMDbSearchDto[] getSearch() {
        return Search;
    }

    /**
     * @param Response the Response to set
     */
    public void setResponse(String Response) {
        this.Response = Response;
    }

    /**
     * @param totalResults the totalResults to set
     */
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @param Error the Error to set
     */
    public void setError(String Error) {
        this.Error = Error;
    }

    /**
     * @param Search the Search to set
     */
    public void setSearch(IMDbSearchDto[] Search) {
        this.Search = Search;
    }

}
