package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.util.Formatador;

public class PanelTitulo extends JPanel {
    private static final long serialVersionUID = 1L;

    private final Titulo titulo;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JTextField txtData;
    private JTextField txtConta;

    public PanelTitulo(Titulo titulo) {
	super();
	this.titulo = titulo;
	initComponents();
    }

    private void initComponents() {
	setBounds(227, 150, 600, 25);
	setLayout(null);

	txtConta = new JTextField(" " + titulo.getConta().getNome());
	txtConta.setBackground(new Color(204, 255, 204));
	txtConta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtConta.setBounds(5, 0, 100, 25);
	txtConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtConta.setFocusable(false);
	add(txtConta);
	txtConta.setColumns(10);

	txtData = new JTextField(Formatador.dataTexto(titulo.getData()));
	txtData.setBackground(new Color(204, 255, 204));
	txtData.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtData.setBounds(110, 0, 100, 25);
	txtData.setHorizontalAlignment(SwingConstants.CENTER);
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setFocusable(false);
	add(txtData);
	txtData.setColumns(10);

	txtDescricao = new JTextField(" " + titulo.getDescricao());
	txtDescricao.setBackground(new Color(204, 255, 204));
	txtDescricao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtDescricao.setBounds(215, 0, 275, 25);
	txtDescricao.setFocusable(false);
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtDescricao);
	txtDescricao.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(titulo.getValor())
		+ " ");
	txtValor.setBackground(new Color(204, 255, 204));
	txtValor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtValor.setBounds(495, 0, 100, 25);
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor);
	txtValor.setColumns(10);

	corStatusTitulo();
    }

    // Status padrão aberto
    private void corStatusTitulo() {
	// aberto Color(204, 255, 204) == verde
	// quitado Color(153, 204, 255) == azul

	Color azul = new Color(153, 204, 255);

	for (Parcela p : titulo.getParcelas())
	    if (p.getPaga() != null && !p.getPaga())
		return;

	txtConta.setBackground(azul);
	txtData.setBackground(azul);
	txtDescricao.setBackground(azul);
	txtValor.setBackground(azul);
    }

}
