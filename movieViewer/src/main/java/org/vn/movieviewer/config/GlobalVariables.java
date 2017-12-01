/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.config;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author danglph
 */
public class GlobalVariables {
    public static final ImageIcon imgDelete = new ImageIcon(ClassLoader.getSystemResource("Images/delete-icon.png"));
    public static final ImageIcon imgViewDetail = new ImageIcon(ClassLoader.getSystemResource("Images/view-detail-icon.png"));
    public static String folderReloadPath = "";    
    
    public static final String separatorComa = ",";
    public static final String separatorSemicolon = ";";
    public static final String separatorSlash = "/";
    public static final String separatorOr = "|";
}
