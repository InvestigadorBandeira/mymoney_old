package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.CrudController;
import br.com.vga.mymoney.entity.Receita;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelReceita extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;

    private JTextField txtDescricao;
    private JTextField txtData;
    private JTextField txtSubCategoria;
    private JTextField txtConta;
    private JTextField txtValor;
    private JTextField txtObservacao;
    private final Receita receita;
    private JButton btnEditar;
    private JButton btnExcluir;

    private final CrudController<Receita> controller;

    public PanelReceita(Receita receita, CrudController<Receita> controller) {
	super();
	this.receita = receita;
	this.controller = controller;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "CONTA", "DATA", "DESCRIÇÃO", "VALOR",
		"SUBCATEGORIA", "OBSERVAÇÃO" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 100, 100, 200, 100, 100, 200 };

	return largura;
    }

    private void initComponents() {
	setBounds(227, 150, 905, 25);
	setLayout(null);

	txtConta = new JTextField(" " + receita.getConta());
	txtConta.setHorizontalAlignment(SwingConstants.LEFT);
	txtConta.setBackground(new Color(153, 204, 255));
	txtConta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtConta.setBounds(5, 0, 100, 25);
	txtConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtConta.setFocusable(false);
	add(txtConta);
	txtConta.setColumns(10);

	txtData = new JTextField(Formatador.dataTexto(receita.getData()));
	txtData.setHorizontalAlignment(SwingConstants.CENTER);
	txtData.setBackground(new Color(153, 204, 255));
	txtData.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtData.setBounds(110, 0, 100, 25);
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setFocusable(false);
	add(txtData);
	txtData.setColumns(10);

	txtDescricao = new JTextField(" " + receita.getDescricao());
	txtDescricao.setBackground(new Color(153, 204, 255));
	txtDescricao.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtDescricao.setBounds(215, 0, 200, 25);
	txtDescricao.setFocusable(false);
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtDescricao);
	txtDescricao.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(receita.getValor())
		+ " ");
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setBackground(new Color(153, 204, 255));
	txtValor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtValor.setBounds(420, 0, 100, 25);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor);
	txtValor.setColumns(10);

	txtSubCategoria = new JTextField(" " + receita.getSubCategoria());
	txtSubCategoria.setBackground(new Color(153, 204, 255));
	txtSubCategoria.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtSubCategoria.setBounds(525, 0, 100, 25);
	txtSubCategoria.setHorizontalAlignment(SwingConstants.LEFT);
	txtSubCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtSubCategoria.setFocusable(false);
	add(txtSubCategoria);
	txtSubCategoria.setColumns(10);

	txtObservacao = new JTextField(" " + receita.getObservacao());
	txtObservacao.setBackground(new Color(153, 204, 255));
	txtObservacao.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtObservacao.setBounds(630, 0, 200, 25);
	txtObservacao.setFocusable(false);
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtObservacao);
	txtObservacao.setColumns(10);

	btnEditar = new JButton();
	btnEditar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnEditarActionPerformed(e);
	    }
	});
	btnEditar.setToolTipText("Editar Receita.");
	btnEditar.setIcon(new ImageIcon(PanelReceita.class
		.getResource("/br/com/vga/mymoney/images/edit_16x16.png")));
	btnEditar.setBounds(835, 0, 30, 25);
	add(btnEditar);

	btnExcluir = new JButton();
	btnExcluir.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setIcon(new ImageIcon(PanelReceita.class
		.getResource("/br/com/vga/mymoney/images/delete_16x16.png")));
	btnExcluir.setToolTipText("Excluir Receita.");
	btnExcluir.setBounds(870, 0, 30, 25);
	add(btnExcluir);
    }

    protected void btnEditarActionPerformed(ActionEvent e) {
	controller.atualizar(receita);
    }

    protected void btnExcluirActionPerformed(ActionEvent e) {
	controller.excluir(receita);
    }
}
