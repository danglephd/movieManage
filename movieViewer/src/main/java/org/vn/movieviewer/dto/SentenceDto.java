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
public class SentenceDto {
    private int rowNumber;
    private String timeString;
    private StringBuilder content = new StringBuilder();
    private StringBuilder contentTranslate = new StringBuilder();
    private boolean isDifferent;

    public SentenceDto() {
    }

    public SentenceDto(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * @return the rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * @return the timeString
     */
    public String getTimeString() {
        return timeString;
    }

    /**
     * @return the content
     */
    public StringBuilder getContent() {
        return content;
    }

    /**
     * @return the isDifferent
     */
    public boolean getIsDifferent() {
        return isDifferent;
    }

    /**
     * @param rowNumber the rowNumber to set
     */
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * @param timeString the timeString to set
     */
    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content.append(content);
    }
    
    public void initContent(String content) {
        this.content = new StringBuilder();
        this.content.append(content);
    }

    /**
     * @param isDifferent the isDifferent to set
     */
    public void setIsDifferent(boolean isDifferent) {
        this.isDifferent = isDifferent;
    }

    /**
     * @return the contentTranslate
     */
    public StringBuilder getContentTranslate() {
        return contentTranslate;
    }

    /**
     * @param contentTranslate the contentTranslate to set
     */
    public void setContentTranslate(String contentTranslate) {
        this.contentTranslate.append(contentTranslate);
    }
    
    public void initContentTranslate(String contentTranslate) {
        this.contentTranslate = new StringBuilder();
        this.contentTranslate.append(contentTranslate);
    }

    public void compaireContent() {
        isDifferent = this.content.toString().equals(this.contentTranslate.toString());
    }
    
}
