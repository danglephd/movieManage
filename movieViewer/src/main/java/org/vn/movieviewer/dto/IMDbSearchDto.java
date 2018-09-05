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
public class MovieIMDbDto {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Poster;
//    private String Ratings;
    private String imdbRating;
    private String imdbVotes;
    private String BoxOffice;
    private String Production;

    public MovieIMDbDto() {
    }

    public MovieIMDbDto(String Title, String Year, String Rated, String Released, String Runtime, String Genre, String Director, String Writer, String Actors, String Plot, String Language, String Country, String Poster, String Ratings, String imdbRating, String imdbVotes, String BoxOffice, String Production) {
        this.Title = Title;
        this.Year = Year;
        this.Rated = Rated;
        this.Released = Released;
        this.Runtime = Runtime;
        this.Genre = Genre;
        this.Director = Director;
        this.Writer = Writer;
        this.Actors = Actors;
        this.Plot = Plot;
        this.Language = Language;
        this.Country = Country;
        this.Poster = Poster;
//        this.Ratings = Ratings;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.BoxOffice = BoxOffice;
        this.Production = Production;
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
     * @return the Rated
     */
    public String getRated() {
        return Rated;
    }

    /**
     * @return the Released
     */
    public String getReleased() {
        return Released;
    }

    /**
     * @return the Runtime
     */
    public String getRuntime() {
        return Runtime;
    }

    /**
     * @return the Genre
     */
    public String getGenre() {
        return Genre;
    }

    /**
     * @return the Director
     */
    public String getDirector() {
        return Director;
    }

    /**
     * @return the Writer
     */
    public String getWriter() {
        return Writer;
    }

    /**
     * @return the Actors
     */
    public String getActors() {
        return Actors;
    }

    /**
     * @return the Plot
     */
    public String getPlot() {
        return Plot;
    }

    /**
     * @return the Language
     */
    public String getLanguage() {
        return Language;
    }

    /**
     * @return the Country
     */
    public String getCountry() {
        return Country;
    }

    /**
     * @return the Poster
     */
    public String getPoster() {
        return Poster;
    }

    /**
     * @return the imdbRating
     */
    public String getImdbRating() {
        return imdbRating;
    }

    /**
     * @return the imdbVotes
     */
    public String getImdbVotes() {
        return imdbVotes;
    }

    /**
     * @return the BoxOffice
     */
    public String getBoxOffice() {
        return BoxOffice;
    }

    /**
     * @return the Production
     */
    public String getProduction() {
        return Production;
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
     * @param Rated the Rated to set
     */
    public void setRated(String Rated) {
        this.Rated = Rated;
    }

    /**
     * @param Released the Released to set
     */
    public void setReleased(String Released) {
        this.Released = Released;
    }

    /**
     * @param Runtime the Runtime to set
     */
    public void setRuntime(String Runtime) {
        this.Runtime = Runtime;
    }

    /**
     * @param Genre the Genre to set
     */
    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    /**
     * @param Director the Director to set
     */
    public void setDirector(String Director) {
        this.Director = Director;
    }

    /**
     * @param Writer the Writer to set
     */
    public void setWriter(String Writer) {
        this.Writer = Writer;
    }

    /**
     * @param Actors the Actors to set
     */
    public void setActors(String Actors) {
        this.Actors = Actors;
    }

    /**
     * @param Plot the Plot to set
     */
    public void setPlot(String Plot) {
        this.Plot = Plot;
    }

    /**
     * @param Language the Language to set
     */
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    /**
     * @param Country the Country to set
     */
    public void setCountry(String Country) {
        this.Country = Country;
    }

    /**
     * @param Poster the Poster to set
     */
    public void setPoster(String Poster) {
        this.Poster = Poster;
    }

    /**
     * @param imdbRating the imdbRating to set
     */
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    /**
     * @param imdbVotes the imdbVotes to set
     */
    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    /**
     * @param BoxOffice the BoxOffice to set
     */
    public void setBoxOffice(String BoxOffice) {
        this.BoxOffice = BoxOffice;
    }

    /**
     * @param Production the Production to set
     */
    public void setProduction(String Production) {
        this.Production = Production;
    }
}
