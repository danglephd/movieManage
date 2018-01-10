/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.renderer;

import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danglph
 */
public class TableModelGeneral extends DefaultTableModel {

    //for paging
    protected HashMap rowCache;
//    private List<String> idList;
//    private int pageSize = 100;

    public TableModelGeneral(String[] columnNames) {
        //new String[]{"Chọn", "Mã thiết bị", "Thời gian", "Trạng thái", "Server","Chu kỳ", "Danh sách đèn", "Điều khiển đèn", "Lịch hoạt động"}
        super(columnNames, 0);
    }
}
