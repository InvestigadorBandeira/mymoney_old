package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.ContaController;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.DecimalFormattedField;
import br.com.vga.mymoney.view.components.PanelConta;
import br.com.vga.mymoney.view.tables.PanelHearder;

public class ContaView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel pnCadastro;
    private JLabel lblNome;
    private JTextField txtNome;
    private JPanel pnListagem;
    private JLabel lblListagem;
    private JScrollPane scrollContas;
    private JButton btnSair;
    private JButton btnSalvarConta;
    private JPanel pnContas;
    private DecimalFormattedField txtSaldoInicial;
    private JLabel lblSaldoInicial;
    private JLabel lblGrupo;
    private JComboBox<Grupo> cbGrupo;

    private Mensagem mensagem;
    private final ContaController controller;

    public ContaView(ContaController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Cadastro de Contas", TitledBorder.LEADING, TitledBorder.TOP,
		null, null));
	setBounds(30, 20, 575, 489);
	setLayout(null);

	pnCadastro = new JPanel();
	pnCadastro.setBackground(new Color(250, 250, 210));
	pnCadastro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnCadastro.setBounds(10, 25, 554, 125);
	add(pnCadastro);
	pnCadastro.setLayout(null);

	lblNome = new JLabel("Nome");
	lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblNome.setBounds(10, 11, 130, 25);
	pnCadastro.add(lblNome);

	txtNome = new JTextField();
	txtNome.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtNome.setColumns(10);
	txtNome.setBounds(150, 11, 320, 25);
	pnCadastro.add(txtNome);

	lblSaldoInicial = new JLabel("Saldo Inicial");
	lblSaldoInicial.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblSaldoInicial.setBounds(10, 47, 130, 25);
	pnCadastro.add(lblSaldoInicial);

	txtSaldoInicial = new DecimalFormattedField("#,##0.00;-#,##0.00");
	txtSaldoInicial.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtSaldoInicial.setColumns(10);
	txtSaldoInicial.setBounds(150, 47, 130, 25);
	pnCadastro.add(txtSaldoInicial);

	lblGrupo = new JLabel("Grupo");
	lblGrupo.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblGrupo.setBounds(10, 83, 130, 25);
	pnCadastro.add(lblGrupo);

	cbGrupo = new JComboBox<Grupo>();
	cbGrupo.setBounds(150, 83, 270, 25);
	pnCadastro.add(cbGrupo);

	btnSalvarConta = new JButton("Salvar Conta");
	btnSalvarConta.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnSalvarContaActionPerformed(e);
	    }
	});
	btnSalvarConta.setBounds(10, 161, 150, 25);
	add(btnSalvarConta);

	pnListagem = new JPanel();
	pnListagem.setLayout(null);
	pnListagem.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnListagem.setBackground(new Color(250, 250, 210));
	pnListagem.setBounds(10, 197, 554, 242);
	add(pnListagem);

	lblListagem = new JLabel("Listagem de Contas Cadastradas");
	lblListagem.setHorizontalAlignment(SwingConstants.CENTER);
	lblListagem.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblListagem.setBounds(10, 11, 534, 20);
	pnListagem.add(lblListagem);

	scrollContas = new JScrollPane();
	scrollContas.setBounds(10, 42, 534, 189);
	pnListagem.add(scrollContas);

	pnContas = new JPanel();
	scrollContas.setViewportView(pnContas);

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(464, 450, 100, 25);
	add(btnSair);

	//
	mensagem = new Mensagem(this, "Cadastro de Contas");
    }

    public void montaComboGrupo(List<Grupo> grupos) {
	for (Grupo grupo : grupos)
	    cbGrupo.addItem(grupo);
    }

    public void montaListagemContas(List<Conta> contas) {
	if (contas == null || contas.isEmpty()) {
	    pnContas.removeAll();
	    pnContas.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelConta> panelContas = new ArrayList<>();

	for (Conta conta : contas)
	    panelContas.add(new PanelConta(conta, controller));

	for (int i = 0; i < panelContas.size(); i++)
	    layout.append("[25px]");

	pnContas.removeAll();

	// Define layout
	pnContas.setLayout(new MigLayout("", "[590px]", layout.toString()));

	pnContas.add(new PanelHearder(panelContas.get(0)), "cell 0 0,grow");

	for (int i = 0; i < panelContas.size(); i++)
	    pnContas.add(panelContas.get(i), "cell 0 " + (i + 1) + ",grow");

	pnContas.updateUI();
    }

    public void atualizaCampos() {
	txtNome.setText("");
	txtSaldoInicial.setText("0.0");
	txtNome.requestFocus();
    }

    protected void btnSalvarContaActionPerformed(ActionEvent e) {
	String nome = txtNome.getText().trim();
	BigDecimal saldoInicial = new BigDecimal(txtSaldoInicial.getValue()
		.toString());
	Grupo grupo = cbGrupo.getItemAt(cbGrupo.getSelectedIndex());

	if (nome.isEmpty()) {
	    mensagem.aviso("Nome é obrigatório.");
	    txtNome.requestFocus();
	    return;
	}

	if (grupo == null) {
	    mensagem.aviso("Grupo inválido.");
	    cbGrupo.requestFocus();
	    return;
	}

	Conta conta = new Conta();
	conta.setNome(nome);
	conta.setSaldoInicial(saldoInicial);
	conta.setGrupo(grupo);
	controller.salvar(conta);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
