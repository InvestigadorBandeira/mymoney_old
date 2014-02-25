package br.com.vga.mymoney.view.tables;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class PanelHearder extends JPanel {
    private static final long serialVersionUID = 1L;

    private final TableMoney tabela;

    public PanelHearder(TableMoney tabela) {
	this.tabela = tabela;
	initComponents();
	geraCabecalho();
    }

    private void geraCabecalho() {
	Border border = new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null);
	Font font = new Font("Tahoma", Font.BOLD, 12);
	int alignment = 0; // center
	int x = 5;

	String[] cabecalho = tabela.getCabecalho();
	int[] largura = tabela.getLargura();

	for (int i = 0; i < cabecalho.length; i++) {
	    JLabel label = new JLabel(cabecalho[i]);
	    label.setBorder(border);
	    label.setHorizontalAlignment(alignment);
	    label.setFont(font);
	    label.setBounds(x, 0, largura[i], 25);
	    add(label);
	    x += largura[i] + 5;
	}

    }

    private void initComponents() {
	setBackground(Color.WHITE);
	setBounds(0, 0, 100, 25);
	setLayout(null);
    }

}
