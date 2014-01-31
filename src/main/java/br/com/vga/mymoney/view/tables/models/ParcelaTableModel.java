package br.com.vga.mymoney.view.tables.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.util.Formatador;

public class ParcelaTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private List<Parcela> parcelas;

    // Array com os nomes das colunas.
    private String[] colunas = new String[] { "Vencimento", "Valor",
	    "Categoria", "Observação" };;

    // Constantes representando o índice das colunas
    private static final int DATA_VENCIMENTO = 0;
    private static final int VALOR = 1;
    private static final int SUBCATEGORIA = 2;
    private static final int OBSERVACAO = 3;

    public ParcelaTableModel() {
	parcelas = new ArrayList<>();
    }

    public void setParcelas(List<Parcela> parcelas) {
	this.parcelas = parcelas;
	fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
	return parcelas.size();
    }

    @Override
    public int getColumnCount() {
	return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
	return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Parcela parcela = parcelas.get(rowIndex);

	switch (columnIndex) {
	case DATA_VENCIMENTO:
	    return Formatador.dataTexto(parcela.getDataVencimento());

	case VALOR:
	    return parcela.getValor().toString();

	case SUBCATEGORIA:
	    return parcela.getSubCategoria();

	case OBSERVACAO:
	    return parcela.getObservacao();

	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (columnIndex) {
	case DATA_VENCIMENTO:
	    return String.class;

	case VALOR:
	    return String.class;

	case SUBCATEGORIA:
	    return SubCategoria.class;

	case OBSERVACAO:
	    return String.class;

	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }

    public void clear() {
	parcelas.clear();
	fireTableDataChanged();
    }

}
