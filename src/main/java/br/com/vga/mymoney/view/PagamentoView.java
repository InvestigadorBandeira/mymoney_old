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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Pagamento;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.DecimalFormattedField;
import br.com.vga.mymoney.view.components.PanelParcela;
import br.com.vga.mymoney.view.tables.PanelHearder;

import com.toedter.calendar.JDateChooser;

public class PagamentoView extends JDialog {
    private static final long serialVersionUID = 1L;
    private JLabel lblDataPagamento;
    private JDateChooser txtDataPagamento;
    private JLabel lblValorTotal;
    private DecimalFormattedField txtValorTotal;
    private JLabel lblConta;
    private JComboBox<Conta> cbConta;
    private JPanel pnPagamento;
    private JScrollPane scrollListagem;
    private JLabel lblParcelasIncluidas;
    private JLabel lblTotalDasParcelas;
    private JLabel lblQuantidadeDeParcelas;
    private JPanel pnParcelasIncluidas;
    private Mensagem mensagem;

    private final List<Parcela> parcelas;
    private JButton btnEfetuarPagamento;
    private final ContaDao contaDao;
    private final PagamentoDao pagamentoDao;
    private final ParcelaDao parcelaDao;
    private JPanel pnParcelas;

    public PagamentoView(List<Parcela> parcelas, ContaDao contaDao,
	    PagamentoDao pagamentoDao, ParcelaDao parcelaDao) {

	this.contaDao = contaDao;
	this.pagamentoDao = pagamentoDao;
	this.parcelaDao = parcelaDao;

	initComponents();
	montaComboConta();
	this.parcelas = parcelas;
	atualizaQtdeTotal();
	montaPnParcelas();
	this.setLocationRelativeTo(null);
    }

    private void initComponents() {
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setModal(true);
	setTitle("Efetuar Pagamento");
	setBounds(100, 100, 790, 550);
	getContentPane().setLayout(null);

	pnPagamento = new JPanel();
	pnPagamento.setBackground(new Color(250, 250, 210));
	pnPagamento.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnPagamento.setBounds(10, 11, 764, 120);
	getContentPane().add(pnPagamento);
	pnPagamento.setLayout(null);

	lblConta = new JLabel("Conta");
	lblConta.setBounds(10, 11, 130, 25);
	pnPagamento.add(lblConta);
	lblConta.setFont(new Font("Tahoma", Font.BOLD, 12));

	cbConta = new JComboBox<>();
	cbConta.setBounds(150, 12, 270, 25);
	pnPagamento.add(cbConta);

	lblDataPagamento = new JLabel("Data Pagamento");
	lblDataPagamento.setBounds(10, 47, 130, 25);
	pnPagamento.add(lblDataPagamento);
	lblDataPagamento.setFont(new Font("Tahoma", Font.BOLD, 12));

	txtDataPagamento = new JDateChooser(Calendar.getInstance().getTime());
	txtDataPagamento.setBounds(150, 47, 130, 25);
	pnPagamento.add(txtDataPagamento);
	txtDataPagamento.setFont(new Font("Tahoma", Font.BOLD, 12));

	lblValorTotal = new JLabel("Valor Total");
	lblValorTotal.setBounds(10, 83, 130, 25);
	pnPagamento.add(lblValorTotal);
	lblValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));

	txtValorTotal = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValorTotal.setFocusable(false);
	txtValorTotal.setBounds(150, 83, 130, 25);
	pnPagamento.add(txtValorTotal);
	txtValorTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValorTotal.setColumns(10);

	pnParcelasIncluidas = new JPanel();
	pnParcelasIncluidas.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnParcelasIncluidas.setBackground(new Color(250, 250, 210));
	pnParcelasIncluidas.setBounds(10, 142, 764, 333);
	getContentPane().add(pnParcelasIncluidas);
	pnParcelasIncluidas.setLayout(null);

	lblParcelasIncluidas = new JLabel("Parcelas Inclu\u00EDdas");
	lblParcelasIncluidas.setBounds(10, 11, 744, 20);
	pnParcelasIncluidas.add(lblParcelasIncluidas);
	lblParcelasIncluidas.setHorizontalAlignment(SwingConstants.CENTER);
	lblParcelasIncluidas.setFont(new Font("Tahoma", Font.BOLD, 14));

	scrollListagem = new JScrollPane();
	scrollListagem.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollListagem.setBounds(10, 42, 744, 244);
	pnParcelasIncluidas.add(scrollListagem);

	pnParcelas = new JPanel();
	scrollListagem.setViewportView(pnParcelas);

	lblTotalDasParcelas = new JLabel("Total das Parcelas:");
	lblTotalDasParcelas.setBounds(10, 297, 240, 25);
	pnParcelasIncluidas.add(lblTotalDasParcelas);
	lblTotalDasParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

	lblQuantidadeDeParcelas = new JLabel("Quantidade de Parcelas: 0");
	lblQuantidadeDeParcelas.setBounds(514, 297, 240, 25);
	pnParcelasIncluidas.add(lblQuantidadeDeParcelas);
	lblQuantidadeDeParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
	lblQuantidadeDeParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

	btnEfetuarPagamento = new JButton("Efetuar Pagamento");
	btnEfetuarPagamento.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnEfetuarPagamentoActionPerformed(e);
	    }
	});
	btnEfetuarPagamento.setBounds(10, 486, 150, 25);
	getContentPane().add(btnEfetuarPagamento);

	//
	mensagem = new Mensagem(this, getTitle());
    }

    private void montaComboConta() {
	List<Conta> contas = contaDao.findAll();

	for (Conta conta : contas)
	    cbConta.addItem(conta);
    }

    private void atualizaQtdeTotal() {
	lblQuantidadeDeParcelas.setText("Quantidade de Parcelas: "
		+ parcelas.size());

	BigDecimal total = new BigDecimal("0.0");
	for (Parcela p : parcelas)
	    total = total.add(p.getValor()).add(p.getAcrescimo())
		    .subtract(p.getDesconto());

	lblTotalDasParcelas.setText("Total das Parcelas: "
		+ Formatador.valorTexto(total));

	txtValorTotal.setText(total.toString());
    }

    private void montaPnParcelas() {
	if (parcelas == null || parcelas.isEmpty()) {
	    pnParcelas.removeAll();
	    pnParcelas.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelParcela> panelParcelas = new ArrayList<>();

	for (Parcela parcela : parcelas)
	    panelParcelas.add(new PanelParcela(parcela));

	for (int i = 0; i < panelParcelas.size(); i++)
	    layout.append("[25px]");

	pnParcelas.removeAll();

	// Define layout
	pnParcelas.setLayout(new MigLayout("", "[840px]", layout.toString()));

	pnParcelas.add(new PanelHearder(panelParcelas.get(0)), "cell 0 0,grow");

	for (int i = 0; i < panelParcelas.size(); i++)
	    pnParcelas.add(panelParcelas.get(i), "cell 0 " + (i + 1) + ",grow");

	pnParcelas.updateUI();
    }

    protected void btnEfetuarPagamentoActionPerformed(ActionEvent e) {
	int indexConta = cbConta.getSelectedIndex();
	Calendar data = txtDataPagamento.getCalendar();
	BigDecimal valorTotal = new BigDecimal(txtValorTotal.getValue()
		.toString());

	if (indexConta == -1) {
	    mensagem.aviso("Selecione uma Conta.");
	    cbConta.requestFocus();
	    return;
	}

	if (data == null) {
	    mensagem.aviso("Data inválida.");
	    txtDataPagamento.requestFocus();
	    return;
	}

	if (valorTotal.signum() == -1) {
	    mensagem.aviso("Valor não pode ser ou negativo.");
	    txtValorTotal.requestFocus();
	    return;
	}

	Conta conta = cbConta.getItemAt(indexConta);

	Pagamento pagamento = new Pagamento();
	pagamento.setConta(conta);
	pagamento.setData(data);
	pagamento.setParcelas(parcelas);
	pagamento.setValorTotal(valorTotal);

	// refatorar
	pagamentoDao.save(pagamento);

	for (Parcela p : parcelas) {
	    p.setPagamento(pagamento);
	    p.setPaga(true);
	    parcelaDao.update(p);
	}

	mensagem.info("Pagamento efetuado com sucesso.");
	parcelas.clear();

	this.dispose();
    }
}
