package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.TransferenciaController;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.DecimalFormattedField;

import com.toedter.calendar.JDateChooser;

public class TransferenciaView extends JTabbedPane {
    private static final long serialVersionUID = 1L;
    private JPanel pnTransferencia;
    private JPanel pnFundo;
    private JLabel lblContaDeOrigem;
    private JComboBox<Conta> cbContaOrigem;
    private JLabel lblContaDeDestino;
    private JComboBox<Conta> cbContaDestino;
    private JLabel lblData;
    private JDateChooser txtData;
    private JLabel lblDescricao;
    private JTextField txtDescricao;
    private DecimalFormattedField txtValor;
    private JLabel lblObservacao;
    private JTextField txtObservacao;
    private JLabel lblValor;
    private JButton btnFazerTransferencia;
    private JButton btnSair;
    private JPanel pnListagem;

    //
    private TransferenciaController controller;
    private Mensagem mensagem;

    public TransferenciaView(TransferenciaController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBounds(30, 20, 580, 400);

	pnTransferencia = new JPanel();
	pnTransferencia.setBorder(null);
	addTab("Fazer Transfer\u00EAncia", null, pnTransferencia, null);
	pnTransferencia.setLayout(null);

	pnFundo = new JPanel();
	pnFundo.setLayout(null);
	pnFundo.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnFundo.setBackground(new Color(250, 250, 210));
	pnFundo.setBounds(10, 11, 554, 234);
	pnTransferencia.add(pnFundo);

	lblContaDeOrigem = new JLabel("Conta de Origem");
	lblContaDeOrigem.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblContaDeOrigem.setBounds(10, 11, 130, 25);
	pnFundo.add(lblContaDeOrigem);

	cbContaOrigem = new JComboBox<Conta>();
	cbContaOrigem.setBounds(150, 12, 270, 25);
	pnFundo.add(cbContaOrigem);

	lblContaDeDestino = new JLabel("Conta de Destino");
	lblContaDeDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblContaDeDestino.setBounds(10, 47, 130, 25);
	pnFundo.add(lblContaDeDestino);

	cbContaDestino = new JComboBox<Conta>();
	cbContaDestino.setBounds(150, 50, 270, 25);
	pnFundo.add(cbContaDestino);

	lblData = new JLabel("Data");
	lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblData.setBounds(10, 83, 130, 25);
	pnFundo.add(lblData);

	txtData = new JDateChooser();
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setBounds(150, 83, 130, 25);
	pnFundo.add(txtData);

	lblDescricao = new JLabel("descri\u00E7\u00E3o");
	lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDescricao.setBounds(10, 119, 130, 25);
	pnFundo.add(lblDescricao);

	txtDescricao = new JTextField();
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDescricao.setColumns(10);
	txtDescricao.setBounds(150, 119, 320, 25);
	pnFundo.add(txtDescricao);

	lblValor = new JLabel("Valor");
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblValor.setBounds(10, 155, 130, 25);
	pnFundo.add(lblValor);

	txtValor = new DecimalFormattedField("#,##0.00;-#,##0.00");
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setColumns(10);
	txtValor.setBounds(150, 155, 130, 25);
	pnFundo.add(txtValor);

	lblObservacao = new JLabel("Observa\u00E7\u00E3o");
	lblObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblObservacao.setBounds(10, 191, 130, 25);
	pnFundo.add(lblObservacao);

	txtObservacao = new JTextField();
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtObservacao.setColumns(10);
	txtObservacao.setBounds(150, 191, 320, 25);
	pnFundo.add(txtObservacao);

	btnFazerTransferencia = new JButton("Fazer Transfer\u00EAncia");
	btnFazerTransferencia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnFazerTransferenciaActionPerformed(e);
	    }
	});
	btnFazerTransferencia.setBounds(10, 256, 150, 25);
	pnTransferencia.add(btnFazerTransferencia);

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(464, 256, 100, 25);
	pnTransferencia.add(btnSair);

	//
	mensagem = new Mensagem(this, "Cadastro de T�tulos");

	pnListagem = new JPanel();
	pnListagem.setBorder(null);
	addTab("Listagem de Transfer\u00EAncias", null, pnListagem, null);
    }

    public void montaCombosConta(List<Conta> contas) {
	for (Conta conta : contas) {
	    cbContaOrigem.addItem(conta);
	    cbContaDestino.addItem(conta);
	}
    }

    public void atualizaCampos() {
	txtData.setDate(null);
	txtValor.setText("0.0");
	txtDescricao.setText("");
	txtObservacao.setText("");
	cbContaOrigem.requestFocus();
    }

    protected void btnFazerTransferenciaActionPerformed(ActionEvent e) {
	int indexContaOrigem = cbContaOrigem.getSelectedIndex();
	int indexContaDestino = cbContaOrigem.getSelectedIndex();
	Calendar data = txtData.getCalendar();
	String descricao = txtDescricao.getText().trim();
	BigDecimal valor = new BigDecimal(txtValor.getValue().toString());
	String observacao = txtObservacao.getText().trim();

	if (indexContaOrigem == -1) {
	    mensagem.aviso("Selecione uma Conta de Origem.");
	    cbContaOrigem.requestFocus();
	    return;
	}

	if (indexContaDestino == -1) {
	    mensagem.aviso("Selecione uma Conta de Destino.");
	    cbContaDestino.requestFocus();
	    return;
	}

	if (data == null) {
	    mensagem.aviso("Data inv�lida.");
	    txtData.requestFocus();
	    return;
	}

	if (valor.equals(new BigDecimal("0.0"))) {
	    mensagem.aviso("Valor n�o pode ser ZERO.");
	    txtValor.requestFocus();
	    return;
	}

	Conta contaOrigem = (Conta) cbContaOrigem.getSelectedItem();
	Conta contaDestino = (Conta) cbContaDestino.getSelectedItem();

	if (contaOrigem.equals(contaDestino)) {
	    mensagem.aviso("Contas de Origem e Destino n�o podem ser iguais.");
	    cbContaDestino.requestFocus();
	    return;
	}

	Transferencia transferencia = new Transferencia();
	transferencia.setContaOrigem(contaOrigem);
	transferencia.setContaDestino(contaDestino);
	transferencia.setData(data);
	transferencia.setDescricao(descricao);
	transferencia.setValor(valor);
	transferencia.setObservacao(observacao);

	controller.salvar(transferencia);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }

}
