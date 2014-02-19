package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.util.Formatador;

public class PanelTitulo2 extends JPanel {
    private static final long serialVersionUID = 1L;

    private final Titulo titulo;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JTextField txtData;
    private JTextField txtConta;

    public PanelTitulo2(Titulo titulo) {
	super();
	this.titulo = titulo;
	initComponents();
    }

    private void initComponents() {
	setBackground(Color.WHITE);
	setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(227, 150, 590, 25);
	setLayout(null);

	txtConta = new JTextField(" " + titulo.getConta().getNome());
	txtConta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtConta.setBounds(9, 0, 100, 25);
	txtConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtConta.setFocusable(false);
	add(txtConta);
	txtConta.setColumns(10);

	txtData = new JTextField(Formatador.dataTexto(titulo.getData()));
	txtData.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtData.setBounds(113, 0, 100, 25);
	txtData.setHorizontalAlignment(SwingConstants.CENTER);
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setFocusable(false);
	add(txtData);
	txtData.setColumns(10);

	txtDescricao = new JTextField(" " + titulo.getDescricao());
	txtDescricao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtDescricao.setBounds(217, 0, 260, 25);
	txtDescricao.setFocusable(false);
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtDescricao);
	txtDescricao.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(titulo.getValor())
		+ " ");
	txtValor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtValor.setBounds(481, 0, 100, 25);
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor);
	txtValor.setColumns(10);
    }

}
