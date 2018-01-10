package org.vn.movieviewer.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellColorRenderer extends DefaultTableCellRenderer  {

//    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if ((boolean)table.getModel().getValueAt(row, 3)) {
            renderer.setBackground(new Color(255,170,198));
        } else {
            renderer.setBackground(new Color(158,234,55));
        }

        return renderer;
    }
}
