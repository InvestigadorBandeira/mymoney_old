package br.com.vga.mymoney.view.tables;

import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.view.components.CalendarRenderer;
import br.com.vga.mymoney.view.components.DecimalRenderer;

public class ParcelaTable extends JTable {
    private static final long serialVersionUID = 1L;

    private final ParcelaTableModel model = new ParcelaTableModel();

    public ParcelaTable() {
	configura();
    }

    private void configura() {
	setModel(model);
	setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	getColumnModel().getColumn(model.INDICE).setResizable(false);
	getColumnModel().getColumn(model.DATA_VENCIMENTO).setResizable(false);
	getColumnModel().getColumn(model.VALOR).setResizable(false);
	getColumnModel().getColumn(model.SUBCATEGORIA).setResizable(false);
	getColumnModel().getColumn(model.OBSERVACAO).setResizable(false);

	getColumnModel().getColumn(model.INDICE).setPreferredWidth(25);
	getColumnModel().getColumn(model.DATA_VENCIMENTO).setPreferredWidth(80);
	getColumnModel().getColumn(model.VALOR).setPreferredWidth(70);
	getColumnModel().getColumn(model.SUBCATEGORIA).setPreferredWidth(200);
	getColumnModel().getColumn(model.OBSERVACAO).setPreferredWidth(250);

	getColumnModel().getColumn(model.DATA_VENCIMENTO).setCellRenderer(
		new CalendarRenderer());
	getColumnModel().getColumn(model.VALOR).setCellRenderer(
		new DecimalRenderer());

    }

    public void adicionaParcelas(List<Parcela> parcelas) {
	model.setParcelas(parcelas);
    }

    public void adicionaParcela(Parcela parcela) {
	model.setParcela(parcela);
    }

}
