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
import br.com.vga.mymoney.entity.Pagamento;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelPagamento extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;
    private JTextField txtData;
    private JTextField txtConta;
    private JTextField txtValorTotal;
    private JButton btnEditar;
    private JButton btnExcluir;

    private final Pagamento pagamento;

    private final CrudController<Pagamento> controller;

    public PanelPagamento(Pagamento pagamento,
	    CrudController<Pagamento> controller) {
	super();
	this.pagamento = pagamento;
	this.controller = controller;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "CONTA", "DATA", "VALOR TOTAL" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 100, 100, 100 };

	return largura;
    }

    private void initComponents() {
	setBounds(227, 150, 390, 25);
	setLayout(null);

	txtConta = new JTextField(" " + pagamento.getConta());
	txtConta.setHorizontalAlignment(SwingConstants.LEFT);
	txtConta.setBackground(new Color(153, 204, 255));
	txtConta.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtConta.setBounds(5, 0, 100, 25);
	txtConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtConta.setFocusable(false);
	add(txtConta);
	txtConta.setColumns(10);

	txtData = new JTextField(Formatador.dataTexto(pagamento.getData()));
	txtData.setHorizontalAlignment(SwingConstants.CENTER);
	txtData.setBackground(new Color(153, 204, 255));
	txtData.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtData.setBounds(110, 0, 100, 25);
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setFocusable(false);
	add(txtData);
	txtData.setColumns(10);

	txtValorTotal = new JTextField(Formatador.valorTexto(pagamento
		.getValorTotal()) + " ");
	txtValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValorTotal.setBackground(new Color(153, 204, 255));
	txtValorTotal.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtValorTotal.setBounds(215, 0, 100, 25);
	txtValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValorTotal.setFocusable(false);
	add(txtValorTotal);
	txtValorTotal.setColumns(10);

	btnEditar = new JButton();
	btnEditar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnEditarActionPerformed(e);
	    }
	});
	btnEditar.setToolTipText("Editar Pagamento.");
	btnEditar.setIcon(new ImageIcon(PanelPagamento.class
		.getResource("/br/com/vga/mymoney/images/edit_16x16.png")));
	btnEditar.setBounds(320, 0, 30, 25);
	add(btnEditar);

	btnExcluir = new JButton();
	btnExcluir.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnExcluirActionPerformed(e);
	    }
	});
	btnExcluir.setIcon(new ImageIcon(PanelPagamento.class
		.getResource("/br/com/vga/mymoney/images/delete_16x16.png")));
	btnExcluir.setToolTipText("Excluir Pagamento.");
	btnExcluir.setBounds(355, 0, 30, 25);
	add(btnExcluir);
    }

    protected void btnEditarActionPerformed(ActionEvent e) {
	controller.atualizar(pagamento);
    }

    protected void btnExcluirActionPerformed(ActionEvent e) {
	controller.excluir(pagamento);
    }
}
