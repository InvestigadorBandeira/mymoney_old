package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.ReceitaController;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Receita;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.DecimalFormattedField;
import br.com.vga.mymoney.view.components.PanelReceita;
import br.com.vga.mymoney.view.tables.PanelHearder;

import com.toedter.calendar.JDateChooser;

public class ReceitaView extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private JPanel pnReceita;
    private JPanel pnFundo;
    private JLabel lblConta;
    private JComboBox<Conta> cbConta;
    private JLabel lblSubCategoria;
    private JComboBox<SubCategoria> cbSubCategoria;
    private JLabel lblData;
    private JDateChooser txtData;
    private JLabel lblDescricao;
    private JTextField txtDescricao;
    private DecimalFormattedField txtValor;
    private JLabel lblObservacao;
    private JTextField txtObservacao;
    private JLabel lblValor;
    private JButton btnLancarReceita;
    private JButton btnSair;
    private JPanel pnListagem;
    private JPanel pnFiltro;
    private JLabel lblFiltrarPor;
    private JScrollPane scrollReceitas;
    private JPanel pnListReceitas;

    private final ReceitaController controller;
    private Mensagem mensagem;

    public ReceitaView(ReceitaController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBounds(30, 20, 580, 400);
	pnReceita = new JPanel();
	pnReceita.setBorder(null);
	addTab("Lan\u00E7ar Receita", null, pnReceita, null);
	pnReceita.setLayout(null);

	pnFundo = new JPanel();
	pnFundo.setLayout(null);
	pnFundo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnFundo.setBackground(new Color(250, 250, 210));
	pnFundo.setBounds(10, 11, 554, 234);
	pnReceita.add(pnFundo);

	lblConta = new JLabel("Conta");
	lblConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblConta.setBounds(10, 11, 130, 25);
	pnFundo.add(lblConta);

	cbConta = new JComboBox<Conta>();
	cbConta.setBounds(150, 12, 270, 25);
	pnFundo.add(cbConta);

	lblData = new JLabel("Data");
	lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblData.setBounds(10, 47, 130, 25);
	pnFundo.add(lblData);

	txtData = new JDateChooser();
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setBounds(150, 47, 130, 25);
	pnFundo.add(txtData);

	lblDescricao = new JLabel("Descri\u00E7\u00E3o");
	lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDescricao.setBounds(10, 83, 130, 25);
	pnFundo.add(lblDescricao);

	txtDescricao = new JTextField();
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDescricao.setColumns(10);
	txtDescricao.setBounds(150, 83, 320, 25);
	pnFundo.add(txtDescricao);

	lblValor = new JLabel("Valor");
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblValor.setBounds(10, 119, 130, 25);
	pnFundo.add(lblValor);

	txtValor = new DecimalFormattedField("#,##0.00;-#,##0.00");
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setColumns(10);
	txtValor.setBounds(150, 119, 130, 25);
	pnFundo.add(txtValor);

	lblSubCategoria = new JLabel("SubCategoria");
	lblSubCategoria.setBounds(10, 155, 130, 25);
	pnFundo.add(lblSubCategoria);
	lblSubCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));

	cbSubCategoria = new JComboBox<SubCategoria>();
	cbSubCategoria.setBounds(150, 155, 270, 25);
	pnFundo.add(cbSubCategoria);

	lblObservacao = new JLabel("Observa\u00E7\u00E3o");
	lblObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblObservacao.setBounds(10, 191, 130, 25);
	pnFundo.add(lblObservacao);

	txtObservacao = new JTextField();
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtObservacao.setColumns(10);
	txtObservacao.setBounds(150, 191, 320, 25);
	pnFundo.add(txtObservacao);

	btnLancarReceita = new JButton("Lan\u00E7ar Receita");
	btnLancarReceita.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnLancarReceitaActionPerformed(e);
	    }
	});
	btnLancarReceita.setBounds(10, 256, 150, 25);
	pnReceita.add(btnLancarReceita);

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(464, 256, 100, 25);
	pnReceita.add(btnSair);

	pnListagem = new JPanel();
	pnListagem.setBorder(null);
	addTab("Listagem de Receitas", null, pnListagem, null);
	pnListagem.setLayout(null);

	pnFiltro = new JPanel();
	pnFiltro.setLayout(null);
	pnFiltro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

	TitledBorder.TOP, null, null));
	pnFiltro.setBackground(new Color(250, 250, 210));
	pnFiltro.setBounds(10, 11, 555, 50);
	pnListagem.add(pnFiltro);

	lblFiltrarPor = new JLabel("Filtrar por");
	lblFiltrarPor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblFiltrarPor.setBounds(10, 11, 100, 25);
	pnFiltro.add(lblFiltrarPor);

	scrollReceitas = new JScrollPane();
	scrollReceitas.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollReceitas.setBounds(10, 70, 555, 262);
	pnListagem.add(scrollReceitas);

	pnListReceitas = new JPanel();
	scrollReceitas.setViewportView(pnListReceitas);

	//
	mensagem = new Mensagem(this, "Cadastro de Receitas");
    }

    public void montaComboConta(List<Conta> contas) {
	for (Conta conta : contas)
	    cbConta.addItem(conta);
    }

    public void montaComboSubCategoria(List<SubCategoria> subCategorias) {
	for (SubCategoria subCategoria : subCategorias)
	    cbSubCategoria.addItem(subCategoria);
    }

    public void atualizaCampos() {
	txtData.setDate(null);
	txtValor.setText("0.0");
	txtDescricao.setText("");
	txtObservacao.setText("");
	cbConta.requestFocus();
    }

    public void montaListagemReceitas(List<Receita> receitas) {
	if (receitas == null || receitas.isEmpty()) {
	    pnListReceitas.removeAll();
	    pnListReceitas.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelReceita> panelReceitas = new ArrayList<>();

	for (Receita receita : receitas)
	    panelReceitas.add(new PanelReceita(receita, controller));

	for (int i = 0; i < panelReceitas.size(); i++)
	    layout.append("[25px]");

	pnListReceitas.removeAll();

	// Define layout
	pnListReceitas
		.setLayout(new MigLayout("", "[905px]", layout.toString()));

	pnListReceitas.add(new PanelHearder(panelReceitas.get(0)),
		"cell 0 0,grow");

	for (int i = 0; i < panelReceitas.size(); i++)
	    pnListReceitas.add(panelReceitas.get(i), "cell 0 " + (i + 1)
		    + ",grow");

	pnListReceitas.updateUI();
    }

    protected void btnLancarReceitaActionPerformed(ActionEvent e) {
	int indexConta = cbConta.getSelectedIndex();
	int indexSubCategoria = cbSubCategoria.getSelectedIndex();
	Calendar data = txtData.getCalendar();
	String descricao = txtDescricao.getText().trim();
	BigDecimal valor = new BigDecimal(txtValor.getValue().toString());
	String observacao = txtObservacao.getText().trim();

	if (indexConta == -1) {
	    mensagem.aviso("Selecione uma Conta.");
	    cbConta.requestFocus();
	    return;
	}

	if (indexSubCategoria == -1) {
	    mensagem.aviso("Selecione uma SubCategoria.");
	    cbSubCategoria.requestFocus();
	    return;
	}

	if (data == null) {
	    mensagem.aviso("Data inválida.");
	    txtData.requestFocus();
	    return;
	}

	if (valor.equals(new BigDecimal("0.0")) || valor.signum() == -1) {
	    mensagem.aviso("Valor não pode ser ZERO ou negativo.");
	    txtValor.requestFocus();
	    return;
	}

	Conta conta = (Conta) cbConta.getSelectedItem();
	SubCategoria subCategoria = (SubCategoria) cbSubCategoria
		.getSelectedItem();

	Receita receita = new Receita();
	receita.setConta(conta);
	receita.setSubCategoria(subCategoria);
	receita.setData(data);
	receita.setDescricao(descricao);
	receita.setValor(valor);
	receita.setObservacao(observacao);

	controller.salvar(receita);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
