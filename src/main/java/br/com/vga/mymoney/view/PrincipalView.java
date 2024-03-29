package br.com.vga.mymoney.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.controller.PrincipalController;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.util.Conexao;
import br.com.vga.mymoney.view.components.PanelSaldoConta;

public class PrincipalView extends JFrame {
    private JMenuBar menuBar;
    private JPanel pnTelas;
    private JPanel pnListagem;
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
    private JMenuItem mntmListagemDeParcelas;
    private JMenu mnTransferencias;
    private JMenuItem mntmLancar;
    private JMenuItem mntmCategoria;
    private JSeparator separator;
    private JMenuItem mntmSubcategoria;
    private JMenu mnReceitas;
    private JMenuItem mntmLancarReceita;
    private JMenu mnPagamentos;
    private JMenuItem mntmParcelasAPagar;
    private JMenuItem mntmListagemDePagamentos;

    public PrincipalView(PrincipalController controller) {
	this.controller = controller;
	initComponents();
    }

    public JPanel getPnTelas() {
	return pnTelas;
    }

    private void initComponents() {
	setIconImage(Toolkit
		.getDefaultToolkit()
		.getImage(
			PrincipalView.class
				.getResource("/br/com/vga/mymoney/images/icon_64x64.png")));
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

	pnListagem = new JPanel();
	pnListagem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,

	TitledBorder.TOP, null, null));
	pnListagem.setLayout(new MigLayout("", "[]", "[]"));

	toolBar = new JToolBar();
	toolBar.setFloatable(false);
	GroupLayout groupLayout = new GroupLayout(getContentPane());
	groupLayout.setHorizontalGroup(groupLayout
		.createParallelGroup(Alignment.LEADING)
		.addGroup(
			groupLayout
				.createSequentialGroup()
				.addContainerGap()
				.addComponent(pnListagem,
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
								pnListagem,
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
	mntmGrupo.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmGrupoActionPerformed(e);
	    }
	});
	mnCadastrar.add(mntmGrupo);

	mntmConta = new JMenuItem("Conta");
	mntmConta.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmContaActionPerformed(e);
	    }
	});
	mnCadastrar.add(mntmConta);

	separator = new JSeparator();
	mnCadastrar.add(separator);

	mntmCategoria = new JMenuItem("Categoria");
	mntmCategoria.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmCategoriaActionPerformed(e);
	    }
	});
	mnCadastrar.add(mntmCategoria);

	mntmSubcategoria = new JMenuItem("SubCategoria");
	mntmSubcategoria.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmSubcategoriaActionPerformed(e);
	    }
	});
	mnCadastrar.add(mntmSubcategoria);

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

	mntmListagemDeParcelas = new JMenuItem("Listagem de Parcelas");
	mntmListagemDeParcelas.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmListagemDeParcelasActionPerformed(e);
	    }
	});
	mnTitulos.add(mntmListagemDeParcelas);

	mnTransferencias = new JMenu("Transfer\u00EAncias");
	mnFinanceiro.add(mnTransferencias);

	mntmLancar = new JMenuItem("Lan\u00E7ar");
	mntmLancar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmLancarActionPerformed(e);
	    }
	});
	mnTransferencias.add(mntmLancar);

	mnReceitas = new JMenu("Receitas");
	mnFinanceiro.add(mnReceitas);

	mntmLancarReceita = new JMenuItem("Lan\u00E7ar");
	mntmLancarReceita.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		mntmLancarReceitaActionPerformed(arg0);
	    }
	});
	mnReceitas.add(mntmLancarReceita);

	mnPagamentos = new JMenu("Pagamentos");
	mnFinanceiro.add(mnPagamentos);

	mntmParcelasAPagar = new JMenuItem("Parcelas a Pagar");
	mntmParcelasAPagar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmParcelasAPagarActionPerformed(e);
	    }
	});
	mnPagamentos.add(mntmParcelasAPagar);

	mntmListagemDePagamentos = new JMenuItem("Listagem de Pagamentos");
	mntmListagemDePagamentos.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		mntmListagemDePagamentosActionPerformed(e);
	    }
	});
	mnPagamentos.add(mntmListagemDePagamentos);

    }

    public void montaListagemSaldoContas(Map<Conta, BigDecimal> saldos) {
	pnListagem.removeAll();

	StringBuilder layout = new StringBuilder("[40px][1px]");

	for (int i = 1; i < saldos.size(); i++)
	    layout.append("[40px]");

	List<PanelSaldoConta> panelSaldoContas = new ArrayList<>();

	for (Conta c : saldos.keySet())
	    panelSaldoContas.add(new PanelSaldoConta(c, saldos.get(c)));

	// Define layout
	pnListagem.setLayout(new MigLayout("", "[200px]", layout.toString()));

	pnListagem.add(panelSaldoContas.get(panelSaldoContas.size() - 1),
		"cell 0 0,grow");
	pnListagem.add(new JPanel(), "cell 0 1,grow");

	for (int i = 0; i < panelSaldoContas.size() - 1; i++)
	    pnListagem.add(panelSaldoContas.get(i), "cell 0 " + (i + 2)
		    + ",grow");

	pnListagem.updateUI();
    }

    protected void mntmIncluirActionPerformed(ActionEvent e) {
	controller.incluirTitulo();
    }

    protected void mntmListagemActionPerformed(ActionEvent e) {
	controller.listarTitulos();
    }

    protected void mntmListagemDeParcelasActionPerformed(ActionEvent e) {
	controller.listarParcelas();
    }

    protected void mntmLancarActionPerformed(ActionEvent e) {
	controller.fazerTransferencia();
    }

    protected void mntmGrupoActionPerformed(ActionEvent e) {
	controller.incluirGrupo();
    }

    protected void mntmContaActionPerformed(ActionEvent e) {
	controller.incluirConta();
    }

    protected void mntmCategoriaActionPerformed(ActionEvent e) {
	controller.incluirCategoria();
    }

    protected void mntmSubcategoriaActionPerformed(ActionEvent e) {
	controller.incluirSubCategoria();
    }

    protected void mntmLancarReceitaActionPerformed(ActionEvent e) {
	controller.lancarReceita();
    }

    protected void mntmParcelasAPagarActionPerformed(ActionEvent e) {
	controller.parcelasAPagar();
    }

    protected void mntmListagemDePagamentosActionPerformed(ActionEvent e) {
	controller.listarPagamentos();
    }

    private void fechar() {
	int opcao = JOptionPane.showConfirmDialog(null,
		"Deseja sair do aplicativo?", "ATEN��O",
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
