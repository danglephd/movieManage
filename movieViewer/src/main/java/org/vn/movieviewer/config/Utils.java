/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.vn.movieviewer.renderer.PagingTable;

/**
 *
 * @author danglph
 */
public class Utils {
        private static Logger logger = Logger.getLogger(Utils.class);

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
    
    public static void updatePagingView(PagingTable pagingTable, JLabel jLTotalPages, JLabel jLDataFrom, JTextField jTFCurrentPage) {
        jLTotalPages.setText("/" + pagingTable.getTotalPage());
        jTFCurrentPage.setText(pagingTable.getCurentPage() + "");
        if (pagingTable.getTotalPage() == 0) {
            jLDataFrom.setText("Không có dữ liệu");
        } else {
            jLDataFrom.setText("Dữ liệu từ " + (pagingTable.getStart() + 1) + " - " + pagingTable.getLimit() + "/" + pagingTable.getTotalRow());
        }
    }
    
    public static void printFolderSize(File file){
        
        File folder = file;
        File[] arrFile = folder.listFiles();

        long size = FileUtils.sizeOfDirectory(file);
        List<File> lstFile = new ArrayList<>();
        List<File> lstFolder = new ArrayList<>();
        for (int i = 0; i < arrFile.length; i++) {
            File file1 = arrFile[i];
            if (file1.isDirectory()) {
                lstFolder.add(file1);
            } else if (file1.isFile()) {
                lstFile.add(file1);
            }
        }
        logger.debug("folder size ~ " + viewSize(size, 0) + "(" + size + ")");
        logger.debug("nuber folders ~ " + lstFolder.size());
        logger.debug("number files ~ " + lstFile.size());
    }
    
    private static String viewSize(long size, int div) {
        if (size > 1024) {
            return viewSize(size / 1024, div + 1);
        }
        return size + " " + (div == 0 ? "bytes" : (div == 1 ? "KB" : div == 2 ? "MB" : (div == 3 ? "GB" : "TB")));
    }

    public static ImageIcon getImagePosterLocal(String pathSource) {
        String imgPath = "";

        File folder = new File(pathSource);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null || listOfFiles.length <= 0) {
            return null;
        }
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String mimetype = new MimetypesFileTypeMap().getContentType(file);
                String type = mimetype.split("/")[0];
                if (type.equals("image")) {
                    imgPath = file.getPath();
                    break;
                }
            }
        }
        if (imgPath.equals("")) {
            return null;
        }
        return new ImageIcon(imgPath);
    }
    
    public static ImageIcon getImagePoster(String path) {
        try {
            System.out.println("Get Image from " + path);
            URL url = new URL(path);
            BufferedImage image = ImageIO.read(url);
            System.out.println("Load image into frame...");
            return new ImageIcon(image);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    
    public static String object2Json(Object data) {

        Gson gson = new GsonBuilder()
                .create();
        return gson.toJson(data);
    }

    public static <T> T jSon2Object(JsonElement jsonElement, Class<T> enClass) {

        Gson gson = new GsonBuilder()
                .create();
        return gson.fromJson(jsonElement, enClass);
    }
}
