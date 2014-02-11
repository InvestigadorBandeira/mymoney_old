package br.com.vga.mymoney.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.util.Formatador;

public class PanelSaldoContas extends JPanel {
    private static final long serialVersionUID = 1L;

    private final Conta conta;
    private final BigDecimal saldo;

    public PanelSaldoContas(Conta conta, BigDecimal saldo) {
	super();
	this.conta = conta;
	this.saldo = saldo;

	initComponents();
    }

    private void initComponents() {
	setBackground(new Color(214, 223, 247));
	setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(227, 150, 150, 40);
	setLayout(new BorderLayout(0, 0));

	JLabel lblConta = new JLabel("  " + conta);
	lblConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(lblConta, BorderLayout.NORTH);

	JLabel lblValor = new JLabel(Formatador.valorTextoReal(saldo) + "  ");
	lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));

	if (saldo.signum() == -1)
	    lblValor.setForeground(Color.RED);

	add(lblValor, BorderLayout.CENTER);
    }

}
