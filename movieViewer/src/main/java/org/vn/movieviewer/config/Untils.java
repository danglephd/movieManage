/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author danglph
 */
public class Untils {
    public static Properties loadPropertiesFile(String path) {
        try {
            //CreatePropertiesFile();

            Properties p = new Properties();

            //Create InputStream to read file
            FileInputStream is = new FileInputStream(path);

            //Load path to properties file
            p.load(is);

            return p;
        } catch (FileNotFoundException e) {
            //TcpMultiClient.logger.error("Class-CommonFeature, Function-loadTextFileToPropertiesFile(String path)\n", e);
            return null;
        } catch (IOException e) {
            //TcpMultiClient.logger.error("Class-CommonFeature, Function-loadTextFileToPropertiesFile(String path)\n", e);
            return null;
        }
    }
    
    public static InputVerifier fieldYearReleaseVerifier = new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            JTextField temp = (JTextField) input;
            try {
                int number = Integer.parseInt(temp.getText());
                if (number < 1900) {
                    JOptionPane.showMessageDialog(null, "Năm phát hành không được nhỏ hơn 1900!");
                    return false;
                } else if (number > 3000) {
                    JOptionPane.showMessageDialog(null, "Năm phát hành không được lớn hơn 3000!");
                    return false;
                }
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Chỉ được nhập số!");
            }
            return false;
        }
    };

}
