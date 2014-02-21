package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Formatador;

public class PanelListagem extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTextField txtDescricao;
    private JTextField txtData;
    private JTextField txtContaDestino;
    private JTextField txtContaOrigem;
    private JTextField txtValor;
    private JTextField txtObservacao;
    private Transferencia transferencia;

    public PanelListagem(Transferencia transferencia) {
	super();
	this.transferencia = transferencia;
	initComponents();
    }

    private void initComponents() {
	setBounds(227, 150, 835, 25);
	setLayout(null);

	txtContaOrigem = new JTextField(" " + transferencia.getContaOrigem());
	txtContaOrigem.setHorizontalAlignment(SwingConstants.LEFT);
	txtContaOrigem.setBackground(new Color(153, 204, 255));
	txtContaOrigem.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtContaOrigem.setBounds(5, 0, 100, 25);
	txtContaOrigem.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtContaOrigem.setFocusable(false);
	add(txtContaOrigem);
	txtContaOrigem.setColumns(10);

	txtContaDestino = new JTextField(" " + transferencia.getContaDestino());
	txtContaDestino.setBackground(new Color(153, 204, 255));
	txtContaDestino.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtContaDestino.setBounds(110, 0, 100, 25);
	txtContaDestino.setHorizontalAlignment(SwingConstants.LEFT);
	txtContaDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtContaDestino.setFocusable(false);
	add(txtContaDestino);
	txtContaDestino.setColumns(10);

	txtData = new JTextField(Formatador.dataTexto(transferencia.getData()));
	txtData.setHorizontalAlignment(SwingConstants.CENTER);
	txtData.setBackground(new Color(153, 204, 255));
	txtData.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtData.setBounds(215, 0, 100, 25);
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setFocusable(false);
	add(txtData);
	txtData.setColumns(10);

	txtDescricao = new JTextField(" " + transferencia.getDescricao());
	txtDescricao.setBackground(new Color(153, 204, 255));
	txtDescricao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtDescricao.setBounds(320, 0, 200, 25);
	txtDescricao.setFocusable(false);
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtDescricao);
	txtDescricao.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(transferencia
		.getValor()) + " ");
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setBackground(new Color(153, 204, 255));
	txtValor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtValor.setBounds(525, 0, 100, 25);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor);
	txtValor.setColumns(10);

	txtObservacao = new JTextField(" " + transferencia.getObservacao());
	txtObservacao.setBackground(new Color(153, 204, 255));
	txtObservacao.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtObservacao.setBounds(630, 0, 200, 25);
	txtObservacao.setFocusable(false);
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtObservacao);
	txtObservacao.setColumns(10);

	// corStatusTitulo();
    }

    // Status padrão aberto
    private void corStatusTitulo() {
	// aberto Color(204, 255, 204) == verde
	// quitado Color(153, 204, 255) == azul

	Color azul = new Color(153, 204, 255);

	txtContaOrigem.setBackground(azul);
	txtContaDestino.setBackground(azul);
	txtDescricao.setBackground(azul);
	txtData.setBackground(azul);
    }

}
