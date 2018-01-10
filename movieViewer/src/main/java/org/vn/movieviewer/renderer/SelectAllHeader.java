package org.vn.movieviewer.renderer;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;

public class SelectAllHeader extends JToggleButton implements TableCellRenderer {

    /**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private final Logger logger = Logger.getLogger(SelectAllHeader.class);
    private static final String ALL = "✓";
    private static final String NONE = "X";
    /**
	 * @uml.property  name="table"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private final JTable table;
    /**
	 * @uml.property  name="tableModel"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private final TableModel tableModel;
    /**
	 * @uml.property  name="header"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private JTableHeader header;
    /**
	 * @uml.property  name="tcm"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private TableColumnModel tcm;
    /**
	 * @uml.property  name="targetColumn"
	 */
    private int targetColumn;
    /**
	 * @uml.property  name="viewColumn"
	 */
    private int viewColumn;

    public SelectAllHeader(JTable table, int targetColumn) {
        super(ALL);
        this.table = table;
        this.tableModel = table.getModel();
        if (tableModel.getColumnClass(targetColumn) != Boolean.class) {
            throw new IllegalArgumentException("Boolean column required.");
        }
        this.targetColumn = targetColumn;
        this.header = table.getTableHeader();
        this.tcm = table.getColumnModel();
        this.applyUI();
        this.addItemListener(new ItemHandler());
        header.addMouseListener(new MouseHandler());
        tableModel.addTableModelListener(new ModelHandler());
        tcm.getColumn(0).setPreferredWidth(15);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        return this;
    }

    private class ItemHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            boolean state = e.getStateChange() == ItemEvent.SELECTED;
            setText((state) ? NONE : ALL);
            for (int r = 0; r < table.getRowCount(); r++) {
                table.setValueAt(state, r, viewColumn);
                //logger.error("state: "+state +"  -- r: "+r +"  -- viewColumn: "+viewColumn);
            }
        }
    }

    @Override
    public void updateUI() {
        super.updateUI();
        applyUI();
    }

    private void applyUI() {
        this.setFont(UIManager.getFont("TableHeader.font"));
        this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        this.setBackground(UIManager.getColor("TableHeader.background"));
        this.setForeground(UIManager.getColor("TableHeader.foreground"));
    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            viewColumn = header.columnAtPoint(e.getPoint());
            int modelColumn = tcm.getColumn(viewColumn).getModelIndex();
            if (modelColumn == targetColumn) {
                //logger.error("doClick: "+modelColumn);
                doClick();
            }
        }
    }

    private class ModelHandler implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            if (needsToggle()) {
               // logger.error("doClick: " + e.getColumn());

                doClick();
                header.repaint();
            }
        }
    }

    // Return true if this toggle needs to match the model.
    private boolean needsToggle() {
        boolean allTrue = true;
        boolean allFalse = true;
        for (int r = 0; r < tableModel.getRowCount(); r++) {
            boolean b = (Boolean) tableModel.getValueAt(r, targetColumn);
            allTrue &= b;
            allFalse &= !b;
        }
        return allTrue && !isSelected() || allFalse && isSelected();
    }
}
