package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelParcela extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;

    private JTextField txtObservacao;
    private JTextField txtSubCategoria;
    private JTextField txtValor;
    private JTextField txtDataVencimento;
    private JTextField txtAcrescimo;
    private JTextField txtDesconto;
    private JTextField txtPagaEm;

    private final Parcela parcela;

    public PanelParcela(Parcela parcela) {
	super();
	this.parcela = parcela;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "VENCIMENTO", "VALOR", "CATEGORIA",
		"OBSERVAÇÃO", "ACRÉSCIMO", "DESCONTO", "PAGO EM" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 100, 100, 100, 200, 100, 100, 100 };

	return largura;
    }

    private void initComponents() {
	setBounds(0, 0, 840, 25);
	setLayout(null);

	txtDataVencimento = new JTextField(Formatador.dataTexto(parcela
		.getDataVencimento()));
	txtDataVencimento.setHorizontalAlignment(SwingConstants.CENTER);
	txtDataVencimento.setBackground(new Color(204, 255, 204));
	txtDataVencimento.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtDataVencimento.setBounds(5, 0, 100, 25);
	txtDataVencimento.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDataVencimento.setFocusable(false);
	add(txtDataVencimento);
	txtDataVencimento.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(parcela.getValor())
		+ " ");
	txtValor.setBackground(new Color(204, 255, 204));
	txtValor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtValor.setBounds(110, 0, 100, 25);
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor);
	txtValor.setColumns(10);

	txtSubCategoria = new JTextField(" "
		+ parcela.getSubCategoria().getNome());
	txtSubCategoria.setBackground(new Color(204, 255, 204));
	txtSubCategoria.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtSubCategoria.setBounds(215, 0, 100, 25);
	txtSubCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtSubCategoria.setFocusable(false);
	add(txtSubCategoria);
	txtSubCategoria.setColumns(10);

	txtObservacao = new JTextField(" " + parcela.getObservacao());
	txtObservacao.setBackground(new Color(204, 255, 204));
	txtObservacao.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtObservacao.setBounds(320, 0, 200, 25);
	txtObservacao.setFocusable(false);
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtObservacao);
	txtObservacao.setColumns(10);

	txtAcrescimo = new JTextField(Formatador.valorTexto(parcela
		.getAcrescimo()) + " ");
	txtAcrescimo.setHorizontalAlignment(SwingConstants.RIGHT);
	txtAcrescimo.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtAcrescimo.setFocusable(false);
	txtAcrescimo.setColumns(10);
	txtAcrescimo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtAcrescimo.setBackground(new Color(204, 255, 204));
	txtAcrescimo.setBounds(525, 0, 100, 25);
	add(txtAcrescimo);

	txtDesconto = new JTextField(Formatador.valorTexto(parcela
		.getDesconto()) + " ");
	txtDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
	txtDesconto.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDesconto.setFocusable(false);
	txtDesconto.setColumns(10);
	txtDesconto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtDesconto.setBackground(new Color(204, 255, 204));
	txtDesconto.setBounds(630, 0, 100, 25);
	add(txtDesconto);

	txtPagaEm = new JTextField();
	txtPagaEm.setHorizontalAlignment(SwingConstants.CENTER);
	txtPagaEm.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtPagaEm.setFocusable(false);
	txtPagaEm.setColumns(10);
	txtPagaEm.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtPagaEm.setBackground(new Color(204, 255, 204));
	txtPagaEm.setBounds(735, 0, 100, 25);
	add(txtPagaEm);

	corStatusTitulo();
    }

    // Status padrão aberto
    private void corStatusTitulo() {
	// aberto Color(204, 255, 204) == verde
	// quitado Color(153, 204, 255) == azul

	Color azul = new Color(153, 204, 255);

	if (parcela.getPaga() != null && !parcela.getPaga())
	    return;

	txtDataVencimento.setBackground(azul);
	txtValor.setBackground(azul);
	txtObservacao.setBackground(azul);
	txtSubCategoria.setBackground(azul);
	txtAcrescimo.setBackground(azul);
	txtDesconto.setBackground(azul);
	txtPagaEm.setBackground(azul);

	txtPagaEm.setText(Formatador
		.dataTexto(parcela.getPagamento().getData()));
    }
}
