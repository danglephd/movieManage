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
public class IMDbSearchDto {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;

    public IMDbSearchDto(String Title, String Year, String imdbID, String Type, String Poster) {
        this.Title = Title;
        this.Year = Year;
        this.imdbID = imdbID;
        this.Type = Type;
        this.Poster = Poster;
    }  

    public IMDbSearchDto() {
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @return the Year
     */
    public String getYear() {
        return Year;
    }

    /**
     * @return the imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @return the Poster
     */
    public String getPoster() {
        return Poster;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @param Year the Year to set
     */
    public void setYear(String Year) {
        this.Year = Year;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @param Poster the Poster to set
     */
    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

}
