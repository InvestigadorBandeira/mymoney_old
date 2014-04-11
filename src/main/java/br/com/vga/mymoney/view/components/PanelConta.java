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
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelConta extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;

    private final Conta conta;
    private JTextField txtNome;
    private JButton btnEditar;
    private JButton btnExcluir;

    private CrudController<Conta> controller;
    private JTextField txtSaldoInicial;
    private JTextField txtGrupo;

    public PanelConta(Conta conta, CrudController<Conta> controller) {
	super();
	this.conta = conta;
	this.controller = controller;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "NOME", "SALDO INICIAL", "GRUPO" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 200, 100, 200 };

	return largura;
    }

    private void initComponents() {
	setBounds(227, 150, 590, 25);
	setLayout(null);

	// tipos padrões
	Color verde = new Color(204, 255, 204);
	Font fonte = new Font("Tahoma", Font.BOLD, 12);
	TitledBorder borda = new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null);
	//

	txtNome = new JTextField(" " + conta.getNome());
	txtNome.setBackground(verde);
	txtNome.setBorder(borda);
	txtNome.setBounds(5, 0, 200, 25);
	txtNome.setFont(fonte);
	txtNome.setFocusable(false);
	txtNome.setColumns(10);
	add(txtNome);

	txtSaldoInicial = new JTextField(Formatador.valorTexto(conta
		.getSaldoInicial()) + " ");
	txtSaldoInicial.setHorizontalAlignment(SwingConstants.RIGHT);
	txtSaldoInicial.setFont(fonte);
	txtSaldoInicial.setFocusable(false);
	txtSaldoInicial.setColumns(10);
	txtSaldoInicial.setBorder(borda);
	txtSaldoInicial.setBackground(verde);
	txtSaldoInicial.setBounds(210, 0, 100, 25);
	add(txtSaldoInicial);

	txtGrupo = new JTextField(" " + conta.getGrupo());
	txtGrupo.setFont(fonte);
	txtGrupo.setFocusable(false);
	txtGrupo.setColumns(10);
	txtGrupo.setBorder(borda);
	txtGrupo.setBackground(verde);
	txtGrupo.setBounds(315, 0, 200, 25);
	add(txtGrupo);

	btnEditar = new JButton();
	btnEditar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnEditarActionPerformed(e);
	    }
	});
	btnEditar.setToolTipText("Editar Grupo.");
	btnEditar.setIcon(new ImageIcon(PanelConta.class
		.getResource("/br/com/vga/mymoney/images/edit_16x16.png")));
	btnEditar.setBounds(520, 0, 30, 25);
	add(btnEditar);

	btnExcluir = new JButton();
	btnExcluir.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setToolTipText("Excluir Grupo.");
	btnExcluir.setIcon(new ImageIcon(PanelConta.class
		.getResource("/br/com/vga/mymoney/images/delete_16x16.png")));
	btnExcluir.setBounds(555, 0, 30, 25);
	add(btnExcluir);
    }

    protected void btnEditarActionPerformed(ActionEvent e) {
	controller.alterar(conta);
    }

    protected void btnExcluirActionPerformed(ActionEvent e) {
	controller.excluir(conta);
    }
}
