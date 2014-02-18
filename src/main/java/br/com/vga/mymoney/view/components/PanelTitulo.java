package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.entity.Titulo;

public class PanelTitulo extends JPanel {
    private static final long serialVersionUID = 1L;

    private Titulo titulo;
    private JLabel lblConta;
    private JLabel lblData;
    private JLabel lblDescricao;
    private JLabel lblValor;

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
	setLayout(new MigLayout("", "[50px][50px][150px][50px]", "[]"));

	lblConta = new JLabel("Conta");
	lblConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(lblConta, "cell 0 0");

	lblData = new JLabel("Data");
	lblData.setHorizontalAlignment(SwingConstants.CENTER);
	lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(lblData, "cell 1 0");

	lblDescricao = new JLabel("Descri\u00E7\u00E3o");
	lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(lblDescricao, "cell 2 0");

	lblValor = new JLabel("Valor");
	lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(lblValor, "cell 3 0");
    }

}
