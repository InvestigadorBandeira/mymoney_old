package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.view.components.DecimalFormattedField;
import br.com.vga.mymoney.view.tables.ParcelaTable;

import com.toedter.calendar.JDateChooser;

public class ParcelaView extends JDialog {
    private static final long serialVersionUID = 1L;
    private JLabel lblDataVencimento;
    private JDateChooser txtDataVencimento;
    private JLabel lblValor;
    private DecimalFormattedField txtValor;
    private JLabel lblCategoria;
    private JComboBox<SubCategoria> cbCategoria;
    private JLabel lblObservacao;
    private JTextField txtObservacao;
    private JPanel pnParcela;
    private JButton btnAdicionarParcela;
    private JScrollPane scrollPane;
    private JLabel lblParcelasIncluidas;
    private ParcelaTable tbParcelas;
    private JLabel lblQtdeParcelas;
    private JLabel lblQuantidadeDeParcelas;
    private JPanel pnParcelasIncluidas;
    private JButton btnExcluirParcela;

    public ParcelaView() {

	initComponents();
    }

    private void initComponents() {
	setResizable(false);
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setModal(true);
	setTitle("Cadastro de Parcelas");
	setBounds(100, 100, 580, 500);
	getContentPane().setLayout(null);

	pnParcela = new JPanel();
	pnParcela.setBackground(new Color(250, 250, 210));
	pnParcela.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	pnParcela.setBounds(10, 11, 554, 156);
	getContentPane().add(pnParcela);
	pnParcela.setLayout(null);

	lblDataVencimento = new JLabel("Data Vencimento");
	lblDataVencimento.setBounds(10, 11, 130, 25);
	pnParcela.add(lblDataVencimento);
	lblDataVencimento.setFont(new Font("Tahoma", Font.BOLD, 12));

	txtDataVencimento = new JDateChooser();
	txtDataVencimento.setBounds(150, 11, 130, 25);
	pnParcela.add(txtDataVencimento);
	txtDataVencimento.setFont(new Font("Tahoma", Font.BOLD, 12));

	lblValor = new JLabel("Valor");
	lblValor.setBounds(10, 47, 130, 25);
	pnParcela.add(lblValor);
	lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));

	txtValor = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtValor.setBounds(150, 47, 130, 25);
	pnParcela.add(txtValor);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setColumns(10);

	lblCategoria = new JLabel("Categoria");
	lblCategoria.setBounds(10, 83, 130, 25);
	pnParcela.add(lblCategoria);
	lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));

	cbCategoria = new JComboBox<>();
	cbCategoria.setBounds(150, 84, 270, 25);
	pnParcela.add(cbCategoria);

	lblObservacao = new JLabel("Observa\u00E7\u00E3o");
	lblObservacao.setBounds(10, 119, 130, 25);
	pnParcela.add(lblObservacao);
	lblObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));

	txtObservacao = new JTextField();
	txtObservacao.setBounds(150, 119, 320, 25);
	pnParcela.add(txtObservacao);
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtObservacao.setColumns(10);

	btnAdicionarParcela = new JButton("Adicionar Parcela");
	btnAdicionarParcela.setBounds(10, 178, 150, 25);
	getContentPane().add(btnAdicionarParcela);

	pnParcelasIncluidas = new JPanel();
	pnParcelasIncluidas.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pnParcelasIncluidas.setBackground(new Color(250, 250, 210));
	pnParcelasIncluidas.setBounds(10, 220, 554, 247);
	getContentPane().add(pnParcelasIncluidas);
	pnParcelasIncluidas.setLayout(null);

	lblParcelasIncluidas = new JLabel("Parcelas Inclu\u00EDdas");
	lblParcelasIncluidas.setBounds(10, 11, 534, 20);
	pnParcelasIncluidas.add(lblParcelasIncluidas);
	lblParcelasIncluidas.setHorizontalAlignment(SwingConstants.CENTER);
	lblParcelasIncluidas.setFont(new Font("Tahoma", Font.BOLD, 14));

	scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 42, 534, 120);
	pnParcelasIncluidas.add(scrollPane);

	tbParcelas = new ParcelaTable();
	scrollPane.setViewportView(tbParcelas);

	lblQtdeParcelas = new JLabel("Total das Parcelas:");
	lblQtdeParcelas.setBounds(10, 173, 240, 25);
	pnParcelasIncluidas.add(lblQtdeParcelas);
	lblQtdeParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

	lblQuantidadeDeParcelas = new JLabel("Quantidade de Parcelas: 0");
	lblQuantidadeDeParcelas.setBounds(304, 173, 240, 25);
	pnParcelasIncluidas.add(lblQuantidadeDeParcelas);
	lblQuantidadeDeParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
	lblQuantidadeDeParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

	btnExcluirParcela = new JButton("Excluir Parcela");
	btnExcluirParcela.setBounds(10, 209, 150, 25);
	pnParcelasIncluidas.add(btnExcluirParcela);
    }
}
