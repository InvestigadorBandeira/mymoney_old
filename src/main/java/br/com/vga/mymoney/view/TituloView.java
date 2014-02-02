package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.TituloController;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.view.components.DecimalFormattedField;
import br.com.vga.mymoney.view.tables.ParcelaTable;

import com.toedter.calendar.JDateChooser;

public class TituloView extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel pnTitulo;
    private JLabel lblConta;
    private JLabel lblData;
    private JLabel lblDescricao;
    private JLabel lblValor;
    private JComboBox<Conta> cbConta;
    private JDateChooser txtData;
    private JTextField txtDescricao;
    private DecimalFormattedField txtValor;
    private JPanel panel;
    private JLabel lblParcelasIncluidas;
    private JScrollPane scrollPane;
    private JLabel lblTotalDasParcelas;
    private JLabel lblQuantidadeDeParcelas;
    private JButton btnGerenciarParcelas;
    private ParcelaTable tbParcelas;
    private JButton btnSalvarTitulo;

    private List<Parcela> parcelas;

    private final TituloController controller;

    public TituloView(TituloController controller) {
	this.controller = controller;
	initComponents();
	this.setLocationRelativeTo(null);
    }

    private void initComponents() {
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setModal(true);
	setTitle("Cadastro de T\u00EDtulos");
	setResizable(false);
	setBounds(100, 100, 580, 500);
	getContentPane().setLayout(null);

	pnTitulo = new JPanel();
	pnTitulo.setBackground(new Color(250, 250, 210));
	pnTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnTitulo.setBounds(10, 11, 554, 156);
	getContentPane().add(pnTitulo);
	pnTitulo.setLayout(null);

	lblConta = new JLabel("Conta");
	lblConta.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblConta.setBounds(10, 11, 130, 25);
	pnTitulo.add(lblConta);

	cbConta = new JComboBox<>();
	cbConta.setBounds(150, 12, 270, 25);
	pnTitulo.add(cbConta);

	lblData = new JLabel("Data");
	lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblData.setBounds(10, 47, 130, 25);
	pnTitulo.add(lblData);

	txtData = new JDateChooser();
	txtData.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtData.setBounds(150, 47, 130, 25);
	pnTitulo.add(txtData);

	lblDescricao = new JLabel("descri\u00E7\u00E3o");
	lblDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblDescricao.setBounds(10, 83, 130, 25);
	pnTitulo.add(lblDescricao);

	txtDescricao = new JTextField();
	txtDescricao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDescricao.setColumns(10);
	txtDescricao.setBounds(150, 86, 320, 25);
	pnTitulo.add(txtDescricao);

	lblValor = new JLabel("Valor");
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblValor.setBounds(10, 119, 130, 25);
	pnTitulo.add(lblValor);

	txtValor = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setColumns(10);
	txtValor.setBounds(150, 119, 130, 25);
	pnTitulo.add(txtValor);

	panel = new JPanel();
	panel.setLayout(null);
	panel.setBorder(new TitledBorder(null, "",

	TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel.setBackground(new Color(250, 250, 210));
	panel.setBounds(10, 178, 554, 247);
	getContentPane().add(panel);

	lblParcelasIncluidas = new JLabel("Parcelas Inclu\u00EDdas");
	lblParcelasIncluidas.setHorizontalAlignment(SwingConstants.CENTER);
	lblParcelasIncluidas.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblParcelasIncluidas.setBounds(10, 11, 534, 20);
	panel.add(lblParcelasIncluidas);

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 42, 534, 120);
	panel.add(scrollPane);

	tbParcelas = new ParcelaTable();
	scrollPane.setViewportView(tbParcelas);

	lblTotalDasParcelas = new JLabel("Total das Parcelas:");
	lblTotalDasParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTotalDasParcelas.setBounds(10, 173, 240, 25);
	panel.add(lblTotalDasParcelas);

	lblQuantidadeDeParcelas = new JLabel("Quantidade de Parcelas: 0");
	lblQuantidadeDeParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
	lblQuantidadeDeParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblQuantidadeDeParcelas.setBounds(304, 173, 240, 25);
	panel.add(lblQuantidadeDeParcelas);

	btnGerenciarParcelas = new JButton("Gerenciar Parcelas");
	btnGerenciarParcelas.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnGerenciarParcelasActionPerformed(e);
	    }
	});
	btnGerenciarParcelas.setBounds(10, 209, 150, 25);
	panel.add(btnGerenciarParcelas);

	btnSalvarTitulo = new JButton("Salvar T\u00EDtulo");
	btnSalvarTitulo.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSalvarTituloActionPerformed(e);
	    }
	});
	btnSalvarTitulo.setBounds(10, 436, 150, 25);
	getContentPane().add(btnSalvarTitulo);
    }

    public void montaComboConta(List<Conta> contas) {
	for (Conta conta : contas)
	    cbConta.addItem(conta);
    }

    protected void btnGerenciarParcelasActionPerformed(ActionEvent e) {
	if (parcelas == null)
	    parcelas = new ArrayList<>();

	ParcelaView view = new ParcelaView(parcelas);
	view.setVisible(true);

    }

    protected void btnSalvarTituloActionPerformed(ActionEvent e) {
    }
}
