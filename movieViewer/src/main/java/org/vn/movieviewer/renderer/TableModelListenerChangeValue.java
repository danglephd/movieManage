/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vn.movieviewer.renderer;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author danglph
 */
public class TableModelListenerChangeValue implements TableModelListener{

    @Override
    public void tableChanged(TableModelEvent e) {
        if(false){
            int row = e.getFirstRow();
            int column = e.getColumn();
            TableModelGeneral model = (TableModelGeneral) e.getSource();
            Object value = model.getValueAt(row, column);
        }
    }
    
}
