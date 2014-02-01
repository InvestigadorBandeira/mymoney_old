package br.com.vga.mymoney.view.tables;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.vga.mymoney.view.components.CalendarRenderer;
import br.com.vga.mymoney.view.components.DecimalRenderer;

public class ParcelaTable extends JTable {
    private static final long serialVersionUID = 1L;

    ParcelaTableModel model;

    public ParcelaTable(ParcelaTableModel tableModel) {
	this.model = tableModel;
	configura();
    }

    private void configura() {
	setModel(model);
	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	getColumnModel().getColumn(model.DATA_VENCIMENTO).setResizable(false);
	getColumnModel().getColumn(model.VALOR).setResizable(false);
	getColumnModel().getColumn(model.SUBCATEGORIA).setResizable(false);
	getColumnModel().getColumn(model.OBSERVACAO).setResizable(false);

	getColumnModel().getColumn(model.DATA_VENCIMENTO).setPreferredWidth(50);
	getColumnModel().getColumn(model.VALOR).setPreferredWidth(50);
	getColumnModel().getColumn(model.SUBCATEGORIA).setPreferredWidth(100);
	getColumnModel().getColumn(model.OBSERVACAO).setPreferredWidth(250);

	getColumnModel().getColumn(model.DATA_VENCIMENTO).setCellRenderer(
		new CalendarRenderer());
	getColumnModel().getColumn(model.VALOR).setCellRenderer(
		new DecimalRenderer());

    }
}
