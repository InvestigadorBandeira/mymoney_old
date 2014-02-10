package br.com.vga.mymoney.view;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.PrincipalController;

public class PrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JPanel pnTelas;
    private JPanel pnContas;
    private JToolBar toolBar;
    private JButton btnContas;

    private PrincipalController controller;

    public PrincipalView(PrincipalController principalController) {
	this.controller = controller;
	initComponents();
    }

    private void initComponents() {
	setMinimumSize(new Dimension(620, 420));
	setTitle("MyMoney - Sistema Financeiro Pessoal");
	setBounds(100, 100, 620, 420);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	pnTelas = new JPanel();
	pnTelas.setLayout(null);
	pnTelas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

	TitledBorder.TOP, null, null));

	pnContas = new JPanel();
	pnContas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

	TitledBorder.TOP, null, null));
	pnContas.setLayout(new MigLayout("", "[]", "[]"));

	toolBar = new JToolBar();
	toolBar.setFloatable(false);
	GroupLayout groupLayout = new GroupLayout(getContentPane());
	groupLayout.setHorizontalGroup(groupLayout
		.createParallelGroup(Alignment.LEADING)
		.addGroup(
			groupLayout
				.createSequentialGroup()
				.addContainerGap()
				.addComponent(pnContas,
					GroupLayout.PREFERRED_SIZE, 200,
					GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pnTelas,
					GroupLayout.DEFAULT_SIZE, 378,
					Short.MAX_VALUE).addContainerGap())
		.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 604,
			Short.MAX_VALUE));
	groupLayout
		.setVerticalGroup(groupLayout
			.createParallelGroup(Alignment.LEADING)
			.addGroup(
				groupLayout
					.createSequentialGroup()
					.addComponent(toolBar,
						GroupLayout.PREFERRED_SIZE, 68,
						GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(
						ComponentPlacement.RELATED)
					.addGroup(
						groupLayout
							.createParallelGroup(
								Alignment.TRAILING)
							.addComponent(
								pnContas,
								GroupLayout.DEFAULT_SIZE,
								276,
								Short.MAX_VALUE)
							.addComponent(
								pnTelas,
								GroupLayout.DEFAULT_SIZE,
								276,
								Short.MAX_VALUE))
					.addContainerGap()));

	btnContas = new JButton("Contas");
	toolBar.add(btnContas);
	getContentPane().setLayout(groupLayout);

	menuBar = new JMenuBar();
	setJMenuBar(menuBar);

	//
	setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
