package br.com.vga.mymoney.view.tables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.SubCategoria;

public class ParcelaTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private List<Parcela> parcelas;

    // Array com os nomes das colunas.
    private final String[] colunas = new String[] { "#", "Vencimento", "Valor",
	    "Categoria", "Observação" };

    // Constantes representando o índice das colunas
    public final int INDICE = 0;
    public final int DATA_VENCIMENTO = 1;
    public final int VALOR = 2;
    public final int SUBCATEGORIA = 3;
    public final int OBSERVACAO = 4;

    public ParcelaTableModel() {
	parcelas = new ArrayList<>();
    }

    public void setParcelas(List<Parcela> parcelas) {
	this.parcelas = parcelas;
	fireTableDataChanged();
    }

    public void setParcela(Parcela parcela) {
	parcelas.add(parcela);

	// Pega a quantidade de registros e subtrai 1 para
	// achar o último índice. A subtração é necessária
	// porque os índices começam em zero.
	int ultimoIndice = getRowCount() - 1;

	// Notifica a mudança.
	fireTableRowsInserted(ultimoIndice, ultimoIndice);
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
	case INDICE:
	    return rowIndex + 1;

	case DATA_VENCIMENTO:
	    return parcela.getDataVencimento();

	case VALOR:
	    return parcela.getValor();

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
	case INDICE:
	    return Integer.class;

	case DATA_VENCIMENTO:
	    return String.class;

	case VALOR:
	    return BigDecimal.class;

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
