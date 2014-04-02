package br.com.vga.mymoney.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.pattern.PagamentoObserver;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.view.tables.TableMoney;

public class PanelParcelaPagamento extends JPanel implements TableMoney {
    private static final long serialVersionUID = 1L;

    private JTextField txtObservacao;
    private JTextField txtSubCategoria;
    private JTextField txtValor;
    private JTextField txtDataVencimento;
    private DecimalFormattedField txtAcrescimo;
    private DecimalFormattedField txtDesconto;
    private JTextField txtTotalAPagar;
    private JCheckBox checkPagar;

    private final Parcela parcela;
    private PagamentoObserver observer;

    public PanelParcelaPagamento(Parcela parcela, PagamentoObserver observer) {
	super();
	this.parcela = parcela;
	this.observer = observer;
	initComponents();
    }

    @Override
    public String[] getCabecalho() {
	String[] cabecalho = { "PAGAR?", "VENCIMENTO", "VALOR", "CATEGORIA",
		"OBSERVAÇÃO", "ACRÉSCIMO", "DESCONTO", "A PAGAR" };

	return cabecalho;
    }

    @Override
    public int[] getLargura() {
	int[] largura = { 80, 100, 100, 100, 200, 100, 100, 100 };

	return largura;
    }

    private void initComponents() {
	setBounds(0, 0, 925, 25);
	setLayout(null);

	checkPagar = new JCheckBox("");
	checkPagar.setHorizontalAlignment(SwingConstants.CENTER);
	checkPagar.setBackground(Color.WHITE);
	checkPagar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		checkPagarActionPerformed(e);
	    }
	});
	checkPagar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	checkPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
	// checkPagar.setBackground(new Color(204, 255, 204));
	checkPagar.setBounds(5, 0, 80, 25);
	add(checkPagar);

	txtDataVencimento = new JTextField(Formatador.dataTexto(parcela
		.getDataVencimento()));
	txtDataVencimento.setHorizontalAlignment(SwingConstants.CENTER);
	txtDataVencimento.setBackground(new Color(204, 255, 204));
	txtDataVencimento.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtDataVencimento.setBounds(90, 0, 100, 25);
	txtDataVencimento.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDataVencimento.setFocusable(false);
	add(txtDataVencimento);
	txtDataVencimento.setColumns(10);

	txtValor = new JTextField(Formatador.valorTexto(parcela.getValor())
		+ " ");
	txtValor.setBackground(new Color(204, 255, 204));
	txtValor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	txtValor.setBounds(195, 0, 100, 25);
	txtValor.setHorizontalAlignment(SwingConstants.RIGHT);
	txtValor.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtValor.setFocusable(false);
	add(txtValor);
	txtValor.setColumns(10);

	txtSubCategoria = new JTextField(" "
		+ parcela.getSubCategoria().getNome());
	txtSubCategoria.setBackground(new Color(204, 255, 204));
	txtSubCategoria.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtSubCategoria.setBounds(300, 0, 100, 25);
	txtSubCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtSubCategoria.setFocusable(false);
	add(txtSubCategoria);
	txtSubCategoria.setColumns(10);

	txtObservacao = new JTextField(" " + parcela.getObservacao());
	txtObservacao.setBackground(new Color(204, 255, 204));
	txtObservacao.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	txtObservacao.setBounds(405, 0, 200, 25);
	txtObservacao.setFocusable(false);
	txtObservacao.setFont(new Font("Tahoma", Font.BOLD, 12));
	add(txtObservacao);
	txtObservacao.setColumns(10);

	txtAcrescimo = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtAcrescimo.setFocusable(false);
	txtAcrescimo.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent e) {
		txtAcrescimoFocusLost(e);
	    }
	});
	txtAcrescimo.setHorizontalAlignment(SwingConstants.RIGHT);
	txtAcrescimo.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtAcrescimo.setColumns(10);
	txtAcrescimo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	// txtAcrescimo.setBackground(new Color(204, 255, 204));
	txtAcrescimo.setBounds(610, 0, 100, 25);
	add(txtAcrescimo);

	txtDesconto = new DecimalFormattedField(DecimalFormattedField.NUMERO);
	txtDesconto.setFocusable(false);
	txtDesconto.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent e) {
		txtDescontoFocusLost(e);
	    }
	});
	txtDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
	txtDesconto.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtDesconto.setColumns(10);
	txtDesconto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	// txtDesconto.setBackground(new Color(204, 255, 204));
	txtDesconto.setBounds(715, 0, 100, 25);
	add(txtDesconto);

	txtTotalAPagar = new JTextField();
	txtTotalAPagar.setHorizontalAlignment(SwingConstants.RIGHT);
	txtTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
	txtTotalAPagar.setFocusable(false);
	txtTotalAPagar.setColumns(10);
	txtTotalAPagar.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	// txtTotalAPagar.setBackground(new Color(204, 255, 204));
	txtTotalAPagar.setBounds(820, 0, 100, 25);
	add(txtTotalAPagar);

	corStatusParcela();
	calculaTotalAPagar();
    }

    // Status padrão aberta, já setada nos campus
    // adiciona cor vermelha nas parcelas vencidas
    // ou azul nas quitadas
    private void corStatusParcela() {
	// aberta Color(204, 255, 204) == verde
	// quitada Color(153, 204, 255) == azul
	// vencida Color(240, 128, 128) == vermelho

	Color azul = new Color(153, 204, 255);
	Color vermelho = new Color(240, 128, 128);

	if (!parcela.getPaga()) {
	    // vencida
	    if (parcela.getDataVencimento().before(Calendar.getInstance())) {
		// checkPagar.setBackground(vermelho);
		txtDataVencimento.setBackground(vermelho);
		txtValor.setBackground(vermelho);
		txtObservacao.setBackground(vermelho);
		txtSubCategoria.setBackground(vermelho);
		// txtAcrescimo.setBackground(vermelho);
		// txtDesconto.setBackground(vermelho);
		// txtTotalAPagar.setBackground(vermelho);
	    }
	}
	// paga
	else {
	    // checkPagar.setBackground(azul);
	    txtDataVencimento.setBackground(azul);
	    txtValor.setBackground(azul);
	    txtObservacao.setBackground(azul);
	    txtSubCategoria.setBackground(azul);
	    // txtAcrescimo.setBackground(azul);
	    // txtDesconto.setBackground(azul);
	    // txtTotalAPagar.setBackground(azul);
	}
    }

    private void calculaTotalAPagar() {
	BigDecimal total = parcela.getValor();

	total = total.add(parcela.getAcrescimo()).subtract(
		parcela.getDesconto());

	txtTotalAPagar.setText(Formatador.valorTexto(total) + " ");
    }

    protected void txtAcrescimoFocusLost(FocusEvent e) {
	String acrescimo = txtAcrescimo.getValue().toString();
	parcela.setAcrescimo(new BigDecimal(acrescimo));
	calculaTotalAPagar();
	observer.calculaTotal();
    }

    protected void txtDescontoFocusLost(FocusEvent e) {
	String desconto = txtDesconto.getValue().toString();
	parcela.setDesconto(new BigDecimal(desconto));
	calculaTotalAPagar();
	observer.calculaTotal();
    }

    protected void checkPagarActionPerformed(ActionEvent e) {
	if (checkPagar.isSelected()) {
	    txtDesconto.setFocusable(true);
	    txtAcrescimo.setFocusable(true);
	    observer.incluirParcela(parcela);
	} else {
	    txtDesconto.setFocusable(false);
	    txtDesconto.setText("0.0");
	    parcela.setDesconto(new BigDecimal("0.0"));
	    txtAcrescimo.setFocusable(false);
	    txtAcrescimo.setText("0.0");
	    parcela.setAcrescimo(new BigDecimal("0.0"));
	    calculaTotalAPagar();
	    observer.excluirParcela(parcela);
	}
    }
}
