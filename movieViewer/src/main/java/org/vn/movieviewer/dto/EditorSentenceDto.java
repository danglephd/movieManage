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
public class EditorSentenceDto {
    private int rowNumber;
    private StringBuilder content = new StringBuilder();
    private StringBuilder contentTranslate = new StringBuilder();
    private StringBuilder matchContent = new StringBuilder();
    private String action = "";
    private String proccess = "";
    
    public final static String APPLY = "DELETE";
    public final static String DENY = "DENY";

    public EditorSentenceDto() {
    }

    public EditorSentenceDto(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * @return the rowNumber
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * @return the content
     */
    public StringBuilder getContent() {
        return content;
    }

    /**
     * @param rowNumber the rowNumber to set
     */
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
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

    public void initMatchContent(String matchContent) {
        this.matchContent = new StringBuilder();
        this.matchContent.append(matchContent);
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
    
    /**
     * @return the matchContent
     */
    public StringBuilder getMatchContent() {
        return matchContent;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param matchContent the matchContent to set
     */
    public void setMatchContent(StringBuilder matchContent) {
        this.matchContent = matchContent;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the proccess
     */
    public String getProccess() {
        return proccess;
    }

    /**
     * @param proccess the proccess to set
     */
    public void setProccess(String proccess) {
        this.proccess = proccess;
    }
    
}
