package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.ListagemParcelaController;
import br.com.vga.mymoney.util.Mensagem;

public class ListagemParcelaView extends JPanel {
    private static final long serialVersionUID = 1L;

    private JPanel pnFiltro;
    private JLabel lblFiltrarPor;
    private JComboBox<String> cbFiltro;
    private JButton btnFiltrar;
    private JScrollPane scrollParcelas;
    private JPanel pnParcelas;
    private JButton btnSair;

    private Mensagem mensagem;

    private final ListagemParcelaController controller;

    public ListagemParcelaView(ListagemParcelaController controller) {
	this.controller = controller;
	initComponents();
    }

    public JPanel getPnParcelas() {
	return pnParcelas;
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Listagem de Parcelas  ", TitledBorder.LEADING,
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

	scrollParcelas = new JScrollPane();
	scrollParcelas.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollParcelas.setBounds(10, 86, 660, 325);
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
	btnSair.setBounds(570, 422, 100, 25);
	add(btnSair);
    }

    protected void btnFiltrarActionPerformed(ActionEvent e) {
	String filtro = cbFiltro.getSelectedItem().toString();
	controller.filtrarPor(filtro);
    }

    protected void btnSairActionPerformed(ActionEvent e) {
	controller.sair();
    }
}
