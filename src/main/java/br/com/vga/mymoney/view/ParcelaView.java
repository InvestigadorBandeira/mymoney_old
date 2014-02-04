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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.dao.SubCategoriaDao;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.util.Conexao;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.util.Mensagem;
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
    private JLabel lblTotalDasParcelas;
    private JLabel lblQuantidadeDeParcelas;
    private JPanel pnParcelasIncluidas;
    private JButton btnExcluirParcela;
    private Mensagem mensagem;

    private final List<Parcela> parcelas;

    public ParcelaView(List<Parcela> parcelas) {
	initComponents();
	montaComboCategoria();
	this.parcelas = parcelas;
	tbParcelas.adicionaParcelas(parcelas);
	atualizaQtdeTotal();
	this.setLocationRelativeTo(null);
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
	btnAdicionarParcela.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnAdicionarParcelaActionPerformed(e);
	    }
	});
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

	lblTotalDasParcelas = new JLabel("Total das Parcelas:");
	lblTotalDasParcelas.setBounds(10, 173, 240, 25);
	pnParcelasIncluidas.add(lblTotalDasParcelas);
	lblTotalDasParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

	lblQuantidadeDeParcelas = new JLabel("Quantidade de Parcelas: 0");
	lblQuantidadeDeParcelas.setBounds(304, 173, 240, 25);
	pnParcelasIncluidas.add(lblQuantidadeDeParcelas);
	lblQuantidadeDeParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
	lblQuantidadeDeParcelas.setFont(new Font("Tahoma", Font.BOLD, 12));

	btnExcluirParcela = new JButton("Excluir Parcela");
	btnExcluirParcela.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnExcluirParcelaActionPerformed(e);
	    }
	});
	btnExcluirParcela.setBounds(10, 209, 150, 25);
	pnParcelasIncluidas.add(btnExcluirParcela);

	//
	mensagem = new Mensagem(this, getTitle());
    }

    // refatorar
    private void montaComboCategoria() {
	List<SubCategoria> categorias = new SubCategoriaDao(
		Conexao.getInstance()).findAll();

	for (SubCategoria categoria : categorias)
	    cbCategoria.addItem(categoria);
    }

    private void atualizaCampos() {
	txtDataVencimento.setDate(null);
	txtValor.setText("0.0");
	txtObservacao.setText("");
    }

    private void atualizaQtdeTotal() {
	lblQuantidadeDeParcelas.setText("Quantidade de Parcelas: "
		+ parcelas.size());

	BigDecimal total = new BigDecimal("0.0");
	for (Parcela p : parcelas)
	    total = total.add(p.getValor());

	lblTotalDasParcelas.setText("Total das Parcelas: "
		+ Formatador.valorTexto(total));
    }

    protected void btnAdicionarParcelaActionPerformed(ActionEvent e) {
	Calendar dataVencimento = txtDataVencimento.getCalendar();
	BigDecimal valor = new BigDecimal(txtValor.getValue().toString());
	SubCategoria subCategoria = cbCategoria.getItemAt(cbCategoria
		.getSelectedIndex());
	String observacao = txtObservacao.getText().trim();

	if (dataVencimento == null) {
	    mensagem.aviso("Data inválida.");
	    txtDataVencimento.requestFocus();
	    return;
	}

	if (valor.equals(new BigDecimal("0.0"))) {
	    mensagem.aviso("Valor não pode ser ZERO.");
	    txtValor.requestFocus();
	    return;
	}

	if (subCategoria == null) {
	    mensagem.aviso("Categoria inválida.");
	    cbCategoria.requestFocus();
	    return;
	}

	Parcela parcela = new Parcela();
	parcela.setDataVencimento(dataVencimento);
	parcela.setValor(valor);
	parcela.setSubCategoria(subCategoria);
	parcela.setObservacao(observacao);

	tbParcelas.adicionaParcela(parcela);
	atualizaQtdeTotal();
	atualizaCampos();
    }

    protected void btnExcluirParcelaActionPerformed(ActionEvent e) {
	int index = tbParcelas.getSelectedRow();

	if (index == -1) {
	    mensagem.aviso("Selecione uma Parcela para excluir.");
	    return;
	}

	// refatorar
	Parcela parcela = parcelas.get(index);

	int opcao = JOptionPane.showConfirmDialog(this, String.format(
		"Deseja excluir a parcela:\nVencimento: %s\n"
			+ "Valor: %s\nCategoria: %s\nObservação: %s\n",
		Formatador.dataTexto(parcela.getDataVencimento()),
		Formatador.valorTexto(parcela.getValor()),
		parcela.getSubCategoria(), parcela.getObservacao()),
		"Exclusão", JOptionPane.YES_NO_OPTION);

	if (opcao == JOptionPane.YES_OPTION) {
	    parcelas.remove(index);
	    tbParcelas.adicionaParcelas(parcelas);
	    atualizaQtdeTotal();
	}

    }
}
