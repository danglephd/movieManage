/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.config;

import java.text.DecimalFormat;
import javax.swing.ImageIcon;

/**
 *
 * @author danglph
 */
public class GlobalVariables {
    public static final ImageIcon imgDelete = new ImageIcon(ClassLoader.getSystemResource("Images/delete-icon.png"));
    public static final ImageIcon imgViewDetail = new ImageIcon(ClassLoader.getSystemResource("Images/view-detail-icon.png"));
    public static String folderReloadPath = "";    
    public static String folderSrt = "D:\\Software\\Mu-vi\\srt";    
    public static String pathToSaveEngSrt = "D:\\Software\\Mu-vi\\srt\\eng_123.srt";    
    public static String pathToSaveVieSrt = "D:\\Software\\Mu-vi\\srt\\vie_123.srt";    
    
    public static final String separatorComa = ",";
    public static final String separatorSemicolon = ";";
    public static final String separatorSlash = "/";
    public static final String separatorOr = "|";
    public static final String separatorSpace = " ";
    public static final String separatorBreakLine = "\r\n";
    
    public static DecimalFormat df = new DecimalFormat("#.#");
}
