package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import br.com.vga.mymoney.controller.ListagemPagamentoController;
import br.com.vga.mymoney.entity.Pagamento;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.components.PanelPagamento;
import br.com.vga.mymoney.view.tables.PanelHearder;

public class ListagemPagamentoView extends JPanel {
    private static final long serialVersionUID = 1L;

    private JPanel pnFiltro;
    private JLabel lblFiltrarPor;
    private JComboBox<String> cbFiltro;
    private JButton btnFiltrar;
    private JScrollPane scrollPagamentos;
    private JPanel pnPagamentos;
    private JButton btnSair;

    private Mensagem mensagem;

    private final ListagemPagamentoController controller;

    public ListagemPagamentoView(ListagemPagamentoController controller) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Listagem de Pagamentos  ", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(30, 20, 680, 489);
	setLayout(null);

	pnFiltro = new JPanel();
	pnFiltro.setLayout(null);
	pnFiltro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

	TitledBorder.TOP, null, null));
	pnFiltro.setBackground(new Color(250, 250, 210));
	pnFiltro.setBounds(10, 25, 660, 50);
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

	scrollPagamentos = new JScrollPane();
	scrollPagamentos.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollPagamentos.setBounds(10, 86, 660, 325);
	add(scrollPagamentos);

	pnPagamentos = new JPanel();
	scrollPagamentos.setViewportView(pnPagamentos);

	btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSairActionPerformed(e);
	    }
	});
	btnSair.setBounds(570, 422, 100, 25);
	add(btnSair);

	//
	mensagem = new Mensagem(this, "Listagem de Pagamentos");
    }

    public void montaPnPagamentos(List<Pagamento> pagamentos) {
	if (pagamentos == null || pagamentos.isEmpty()) {
	    pnPagamentos.removeAll();
	    pnPagamentos.updateUI();
	    return;
	}

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelPagamento> panelPagamentos = new ArrayList<>();

	for (Pagamento pagamento : pagamentos)
	    panelPagamentos.add(new PanelPagamento(pagamento, controller));

	for (int i = 0; i < panelPagamentos.size(); i++)
	    layout.append("[25px]");

	pnPagamentos.removeAll();

	// Define layout
	pnPagamentos.setLayout(new MigLayout("", "[390px]", layout.toString()));

	pnPagamentos.add(new PanelHearder(panelPagamentos.get(0)),
		"cell 0 0,grow");

	for (int i = 0; i < panelPagamentos.size(); i++)
	    pnPagamentos.add(panelPagamentos.get(i), "cell 0 " + (i + 1)
		    + ",grow");

	pnPagamentos.updateUI();
    }

    protected void btnFiltrarActionPerformed(ActionEvent e) {
	String filtro = cbFiltro.getSelectedItem().toString();
	controller.filtrarPor(filtro);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
