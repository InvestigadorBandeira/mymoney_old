package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.util.Formatador;

public class PanelParcela extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField txtPbservacao;
    private JTextField txtSubCategoria;
    private JTextField txtValor;
    private JTextField txtDataVencimento;

    private Parcela parcela;

    public PanelParcela(Parcela parcela) {
	super();
	this.parcela = parcela;
	initComponents();
    }

    private void initComponents() {
	setBounds(227, 150, 600, 25);
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

	txtPbservacao = new JTextField(" " + parcela.getObservacao());
	txtPbservacao.setBackground(new Color(204, 255, 204));
	txtPbservacao.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtPbservacao.setBounds(320, 0, 275, 25);
	txtPbservacao.setFocusable(false);
	txtPbservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtPbservacao);
	txtPbservacao.setColumns(10);

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
	txtPbservacao.setBackground(azul);
	txtSubCategoria.setBackground(azul);
    }

}
