package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.ParcelaPagamentoController;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.PanelParcelaPagamento;
import br.com.vga.mymoney.view.tables.PanelHearder;

public class ParcelaPagamentoView extends JPanel {
    private static final long serialVersionUID = 1L;

    private JPanel pnFiltro;
    private JLabel lblFiltrarPor;
    private JComboBox<String> cbFiltro;
    private JButton btnFiltrar;
    private JScrollPane scrollParcelas;
    private JPanel pnParcelas;
    private JButton btnSair;

    private Mensagem mensagem;

    private final ParcelaPagamentoController controller;
    private JButton btnPagarParcelas;
    private JLabel lblTotalAPagar;

    public ParcelaPagamentoView(ParcelaPagamentoController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Listagem de Parcelas a Pagar  ", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(30, 20, 970, 490);
	setLayout(null);

	pnFiltro = new JPanel();
	pnFiltro.setLayout(null);
	pnFiltro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

	TitledBorder.TOP, null, null));
	pnFiltro.setBackground(new Color(250, 250, 210));
	pnFiltro.setBounds(10, 25, 950, 50);
	add(pnFiltro);

	lblFiltrarPor = new JLabel("Filtrar por");
	lblFiltrarPor.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblFiltrarPor.setBounds(10, 11, 100, 25);
	pnFiltro.add(lblFiltrarPor);

	cbFiltro = new JComboBox<String>();
	cbFiltro.setModel(new DefaultComboBoxModel<String>(new String[] {
		"ABERTAS", "QUITADAS", "TODAS" }));
	cbFiltro.setBounds(120, 12, 140, 25);
	pnFiltro.add(cbFiltro);

	btnFiltrar = new JButton("Filtrar");
	btnFiltrar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnFiltrarActionPerformed(e);
	    }
	});
	btnFiltrar.setBounds(270, 13, 100, 25);
	pnFiltro.add(btnFiltrar);

	scrollParcelas = new JScrollPane();
	scrollParcelas.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollParcelas.setBounds(10, 86, 950, 325);
	add(scrollParcelas);

	pnParcelas = new JPanel();
	scrollParcelas.setViewportView(pnParcelas);

	//
	mensagem = new Mensagem(this, "Listagem de Parcelas");

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(860, 422, 100, 25);
	add(btnSair);

	btnPagarParcelas = new JButton("Pagar Parcelas");
	btnPagarParcelas.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		btnPagarParcelasActionPerformed(e);
	    }
	});
	btnPagarParcelas.setBounds(10, 422, 150, 25);
	add(btnPagarParcelas);

	lblTotalAPagar = new JLabel();
	lblTotalAPagar.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTotalAPagar.setBounds(170, 422, 300, 25);
	add(lblTotalAPagar);
    }

    public void montaListagemParcelas(List<Parcela> parcelas) {
	if (parcelas == null || parcelas.isEmpty()) {
	    pnParcelas.removeAll();
	    pnParcelas.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelParcelaPagamento> panelParcelas = new ArrayList<>();

	for (Parcela parcela : parcelas)
	    panelParcelas.add(new PanelParcelaPagamento(parcela, controller));

	for (int i = 0; i < panelParcelas.size(); i++)
	    layout.append("[25px]");

	pnParcelas.removeAll();

	// Define layout
	pnParcelas.setLayout(new MigLayout("", "[925px]", layout.toString()));

	pnParcelas.add(new PanelHearder(panelParcelas.get(0)), "cell 0 0,grow");

	for (int i = 0; i < panelParcelas.size(); i++)
	    pnParcelas.add(panelParcelas.get(i), "cell 0 " + (i + 1) + ",grow");

	pnParcelas.updateUI();
    }

    public void atualizaTotalAPagar(BigDecimal total) {
	lblTotalAPagar.setText(" Total a Pagar: "
		+ Formatador.valorTextoReal(total));
    }

    protected void btnFiltrarActionPerformed(ActionEvent e) {
	String filtro = cbFiltro.getSelectedItem().toString();
	controller.filtrarPor(filtro);
    }

    protected void btnPagarParcelasActionPerformed(ActionEvent e) {
	controller.pagarParcelas();
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }

}
