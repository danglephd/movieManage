/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.vn.movieviewer.dao.daoSmrtLearning;
import org.vn.movieviewer.dto.SmtLearning;

/**
 *
 * @author danglph
 */
public class SmartLearningController {

    public final static String DELETE = "DELETE";
    public final static String DELETE_PATTERN = "DELETE_PATTERN";
    public final static String FIND = "FIND";
    public final static String INSERT = "INSERT";
    public final static String REPLACE = "REPLACE";

    public final static String START_POS = "START_POS";
    public final static String END_POS = "END_POS";
    public final static String ANOTHER_POS = "ANOTHER_POS";
    public final static String ANY_POS = "ANY_POS";
    private static List<SmtLearning> smtLearnings = null;

    public static String isMatchAction(String action, String strToCompair) {
        if (smtLearnings == null) {
            smtLearnings = daoSmrtLearning.getByAction(action);
        }
        for (Iterator<SmtLearning> iterator = smtLearnings.iterator(); iterator.hasNext();) {
            SmtLearning next = iterator.next();
            if(next.getSmtValue2().equals(START_POS)){
                if(strToCompair.startsWith(next.getSmtValue1())){
                    return next.getSmtValue1();
                }   
            }else if(next.getSmtValue2().equals(END_POS)){
                if(strToCompair.endsWith(next.getSmtValue1())){
                    return next.getSmtValue1();
                }                
            }
        }
        return "";
    }

    private int action;
    private Object resource;

    public static void main(String args[]) {
        Map<String, String> mapCommonStr = findTheSame(DELETE);

        for (Map.Entry<String, String> entry : mapCommonStr.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
//            System.out.println(key + " >>> " + value);
            daoSmrtLearning.insert(DELETE_PATTERN, key, value);

        }
    }

    public static void learn(String action, String value1) {
        daoSmrtLearning.insert(action, value1);
    }

    private static Map<String, String> findTheSame(String action) {
        List<SmtLearning> lstSmtLearning = daoSmrtLearning.getByAction(action);

        Map<String, String> mapCommonStr = new HashMap();
        for (int i = 0; i < lstSmtLearning.size() - 1; i++) {
            SmtLearning stObject = lstSmtLearning.get(i);
            for (int j = i + 1; j < lstSmtLearning.size(); j++) {
                SmtLearning ndject = lstSmtLearning.get(j);
                Map<String, String> mapCommonSubStr = getAllCommonSubstrings(stObject.getSmtValue1(), ndject.getSmtValue1());
                if (mapCommonSubStr != null && mapCommonSubStr.size() > 0) {
                    for (Map.Entry<String, String> entry : mapCommonSubStr.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();

                        if (!mapCommonStr.containsKey(key)) {
                            mapCommonStr.put(key, value);
                        } else {

                        }
                    }
                }
            }
        }

        return mapCommonStr;
    }

    private static Map<String, String> getAllCommonSubstrings(String s, String t) {
        int[][] table = new int[s.length()][t.length()];
//        int longest = 0;
//        List<String> result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    continue;
                }

                table[i][j] = (i == 0 || j == 0) ? 1
                        : 1 + table[i - 1][j - 1];
//                if (table[i][j] > longest) {
//                    longest = table[i][j];
//                    result.clear();
//                }
//                if (table[i][j] == longest) {
//                    result.add(s.substring(i - longest + 1, i + 1));
//                }
            }
        }

        return printArr(table, s);
    }

    private static Map<String, String> printArr(int[][] table, String s) {
        int minComonLength = 4;
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < table.length; i++) {
            int[] row = table[i];
            for (int j = 0; j < row.length; j++) {
//                System.out.print(" " + row[j]);

                if (table[i][j] == 1) {
                    int longest = CallLongest(i, j, table, 1);
                    if (longest > minComonLength) {
                        String temp = s.substring(i, i + longest);
                        if (!result.containsKey(temp)) {
                            if (i == 0 || j == 0) {
                                result.put(temp, START_POS);
                            } else if ((i + longest >= table.length - 1)
                                    && (j + longest >= row.length - 1)) {
                                result.put(temp, END_POS);
                            } else {
                                result.put(temp, ANOTHER_POS);
                            }
                        } else {

                        }
                    }
                }

            }
//            System.out.println("");
        }
//        System.out.println("");
        return result;
    }

    private static int CallLongest(int i, int j, int[][] table, int presentValue) {
        if (i >= table.length - 1 || j >= table[i].length - 1) {
            return presentValue;
        }
//        System.out.println(i + "~" + j + "~" + table.length + "~" + table[i].length);
        if (table[i + 1][j + 1] == presentValue + 1) {
            return CallLongest(i + 1, j + 1, table, presentValue + 1);
        } else {
            return presentValue;
        }
    }

}
