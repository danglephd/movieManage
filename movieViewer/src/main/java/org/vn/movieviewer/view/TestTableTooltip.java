package org.vn.movieviewer.view;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TestTableTooltip {

    String html
            = "<html><body>"
            + "<img src='"
            + "https://images-na.ssl-images-amazon.com/images/M/MV5BMzMxMTFlMDYtNjIyNS00YzQ4LWJlMDAtNGQwY2RlZGJiMmM1XkEyXkFqcGdeQXVyNzEyMTA5MTU@._V1_SY1000_SX700_AL_.jpg"
            + "' > ";

    public TestTableTooltip() {
        String[] cols = {"COL", "COL", "COL"};
        String[][] data = {
            {"Hello", "Hello", "Hello"},
            {"Hello", "Hello", "Hello"},
            {"Hello", "Hello", "Hello"}
        };
        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    jc.setToolTipText(html + "<br/>"
                        + getValueAt(row, column).toString()
                        + ":  row, col (" + row + ", " + column + ")"
                        + "</body></html>");
                }
                return c;
            }
        };

        JFrame frame = new JFrame();
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TestTableTooltip();
            }
        });
    }
}