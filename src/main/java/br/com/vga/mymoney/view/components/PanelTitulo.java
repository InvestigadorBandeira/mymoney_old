package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
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
	setBackground(new Color(214, 223, 247));
	setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(227, 150, 590, 35);
	setLayout(new MigLayout("", "[100px][100px][250px,grow][100px]", "[]"));

	txtConta = new JTextField(titulo.getConta().getNome());
	txtConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtConta.setFocusable(false);
	add(txtConta, "cell 0 0,growx");
	txtConta.setColumns(10);

	txtData = new JTextField(Formatador.dataTexto(titulo.getData()));
	txtData.setHorizontalAlignment(SwingConstants.CENTER);
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setFocusable(false);
	add(txtData, "cell 1 0,growx");
	txtData.setColumns(10);

	txtDescricao = new JTextField(titulo.getDescricao());
	txtDescricao.setFocusable(false);
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtDescricao, "cell 2 0,growx");
	txtDescricao.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(titulo.getValor()));
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor, "cell 3 0,growx");
	txtValor.setColumns(10);
    }

}
