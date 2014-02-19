package br.com.vga.mymoney.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.PrincipalController;
import br.com.vga.mymoney.util.Conexao;

public class PrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JPanel pnTelas;
    private JPanel pnContas;
    private JToolBar toolBar;
    private JButton btnContas;
    private JMenu mnArquivo;
    private JMenuItem mntmSair;
    private JMenu mnCadastrar;
    private JMenuItem mntmConta;
    private JMenuItem mntmGrupo;
    private JMenu mnFinanceiro;

    private final PrincipalController controller;
    private JMenu mnTitulos;
    private JMenuItem mntmIncluir;
    private JMenuItem mntmListagem;

    public PrincipalView(PrincipalController controller) {
	this.controller = controller;
	initComponents();
    }

    public JPanel getPnContas() {
	return pnContas;
    }

    public JPanel getPnTelas() {
	return pnTelas;
    }

    private void initComponents() {
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		thisWindowClosing(e);
	    }
	});
	setMinimumSize(new Dimension(740, 540));
	setTitle("MyMoney - Sistema Financeiro Pessoal");
	setBounds(100, 100, 740, 580);
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

	mnArquivo = new JMenu("Arquivo");
	menuBar.add(mnArquivo);

	mnCadastrar = new JMenu("Cadastrar");
	mnArquivo.add(mnCadastrar);

	mntmGrupo = new JMenuItem("Grupo");
	mnCadastrar.add(mntmGrupo);

	mntmConta = new JMenuItem("Conta");
	mnCadastrar.add(mntmConta);

	mntmSair = new JMenuItem("Sair");
	mnArquivo.add(mntmSair);

	mnFinanceiro = new JMenu("Financeiro");
	menuBar.add(mnFinanceiro);

	mnTitulos = new JMenu("T\u00EDtulos");
	mnFinanceiro.add(mnTitulos);

	mntmIncluir = new JMenuItem("Incluir");
	mntmIncluir.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmIncluirActionPerformed(e);
	    }
	});
	mnTitulos.add(mntmIncluir);

	mntmListagem = new JMenuItem("Listagem");
	mntmListagem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmListagemActionPerformed(e);
	    }
	});
	mnTitulos.add(mntmListagem);

    }

    protected void mntmIncluirActionPerformed(ActionEvent e) {
	controller.incluirTitulo();
    }

    protected void mntmListagemActionPerformed(ActionEvent e) {
	controller.listarTitulos();
    }

    private void fechar() {
	int opcao = JOptionPane.showConfirmDialog(null,
		"Deseja sair do aplicativo?", "ATENÇÃO",
		JOptionPane.YES_NO_OPTION);

	if (opcao == JOptionPane.YES_OPTION) {
	    try {
		Conexao.getInstance().getEntityManagerFactory().close();
		System.exit(0);
	    } catch (Exception e) {
		System.err.print("ERRO: " + e.getMessage());
	    }
	}
    }

    protected void thisWindowClosing(WindowEvent e) {
	fechar();
    }

}
