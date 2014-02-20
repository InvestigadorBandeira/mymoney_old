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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.TransferenciaController;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.DecimalFormattedField;

import com.toedter.calendar.JDateChooser;

public class TransferenciaView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel pnTransferencia;
    private JLabel lblContaDeOrigem;
    private JLabel lblData;
    private JLabel lblDescricao;
    private JLabel lblValor;
    private JComboBox<Conta> cbContaOrigem;
    private JDateChooser txtData;
    private JTextField txtDescricao;
    private DecimalFormattedField txtValor;
    private JLabel lblObservacao;
    private JTextField txtObservacao;
    private JLabel lblContaDeDestino;
    private JComboBox<Conta> cbContaDestino;
    private JButton btnFazerTransferencia;
    private JButton btnSair;

    private final TransferenciaController controller;
    private Mensagem mensagem;

    public TransferenciaView(TransferenciaController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Fazer Transfer\u00EAncia  ", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(30, 20, 575, 310);
	setLayout(null);

	pnTransferencia = new JPanel();
	pnTransferencia.setBackground(new Color(250, 250, 210));
	pnTransferencia.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnTransferencia.setBounds(10, 25, 554, 234);
	add(pnTransferencia);
	pnTransferencia.setLayout(null);

	lblContaDeOrigem = new JLabel("Conta de Origem");
	lblContaDeOrigem.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblContaDeOrigem.setBounds(10, 11, 130, 25);
	pnTransferencia.add(lblContaDeOrigem);

	cbContaOrigem = new JComboBox<>();
	cbContaOrigem.setBounds(150, 12, 270, 25);
	pnTransferencia.add(cbContaOrigem);

	lblContaDeDestino = new JLabel("Conta de Destino");
	lblContaDeDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblContaDeDestino.setBounds(10, 47, 130, 25);
	pnTransferencia.add(lblContaDeDestino);

	cbContaDestino = new JComboBox<Conta>();
	cbContaDestino.setBounds(150, 50, 270, 25);
	pnTransferencia.add(cbContaDestino);

	lblData = new JLabel("Data");
	lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblData.setBounds(10, 83, 130, 25);
	pnTransferencia.add(lblData);

	txtData = new JDateChooser();
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setBounds(150, 83, 130, 25);
	pnTransferencia.add(txtData);

	lblDescricao = new JLabel("descri\u00E7\u00E3o");
	lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDescricao.setBounds(10, 119, 130, 25);
	pnTransferencia.add(lblDescricao);

	txtDescricao = new JTextField();
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDescricao.setColumns(10);
	txtDescricao.setBounds(150, 119, 320, 25);
	pnTransferencia.add(txtDescricao);

	txtValor = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setColumns(10);
	txtValor.setBounds(150, 155, 130, 25);
	pnTransferencia.add(txtValor);

	lblObservacao = new JLabel("Observa\u00E7\u00E3o");
	lblObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblObservacao.setBounds(10, 191, 130, 25);
	pnTransferencia.add(lblObservacao);

	txtObservacao = new JTextField();
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtObservacao.setColumns(10);
	txtObservacao.setBounds(150, 191, 320, 25);
	pnTransferencia.add(txtObservacao);

	lblValor = new JLabel("Valor");
	lblValor.setBounds(10, 155, 130, 25);
	pnTransferencia.add(lblValor);
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));

	btnFazerTransferencia = new JButton("Fazer Transfer\u00EAncia");
	btnFazerTransferencia.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSalvarTituloActionPerformed(e);
	    }
	});
	btnFazerTransferencia.setBounds(10, 270, 150, 25);
	add(btnFazerTransferencia);

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(464, 270, 100, 25);
	add(btnSair);

	//
	mensagem = new Mensagem(this, "Cadastro de Títulos");
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

    protected void btnSalvarTituloActionPerformed(ActionEvent e) {
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
	    mensagem.aviso("Data inválida.");
	    txtData.requestFocus();
	    return;
	}

	if (valor.equals(new BigDecimal("0.0"))) {
	    mensagem.aviso("Valor não pode ser ZERO.");
	    txtValor.requestFocus();
	    return;
	}

	Conta contaOrigem = (Conta) cbContaOrigem.getSelectedItem();
	Conta contaDestino = (Conta) cbContaDestino.getSelectedItem();

	if (contaOrigem.equals(contaDestino)) {
	    mensagem.aviso("Contas de Origem e Destino não podem ser iguais.");
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
