package br.com.vga.mymoney.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.vga.mymoney.controller.ListagemTituloController;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.util.Mensagem;

public class ListagemTituloView extends JPanel {
    private static final long serialVersionUID = 1L;

    private List<Parcela> parcelas;
    private Mensagem mensagem;

    private final ListagemTituloController controller;
    private JPanel pnFiltro;
    private JLabel lblFiltrarPor;
    private JComboBox<String> cbFiltro;
    private JButton btnFiltrar;
    private JScrollPane scrollTitulos;
    private JPanel pnTitulos;

    public ListagemTituloView(ListagemTituloController listagemTituloController) {
	this.controller = listagemTituloController;
	initComponents();
    }

    public JPanel getPnTitulos() {
	return pnTitulos;
    }

    private void initComponents() {
	setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
		"  Listagem de T\u00EDtulos  ", TitledBorder.LEADING,
		TitledBorder.TOP, null, null));
	setBounds(30, 20, 680, 489);
	setLayout(null);

	//
	mensagem = new Mensagem(this, "Cadastro de Títulos");

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
		"ABERTOS", "QUITADOS", "TODOS" }));
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

	scrollTitulos = new JScrollPane();
	scrollTitulos.setBorder(new TitledBorder(null, "",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	scrollTitulos.setBounds(10, 88, 660, 330);
	add(scrollTitulos);

	pnTitulos = new JPanel();
	scrollTitulos.setViewportView(pnTitulos);
    }

    protected void btnFiltrarActionPerformed(ActionEvent e) {
	String filtro = cbFiltro.getSelectedItem().toString();

	controller.filtrarPor(filtro);

    }
}
