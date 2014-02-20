package br.com.vga.mymoney.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.PrincipalView;
import br.com.vga.mymoney.view.components.PanelSaldoConta;

public class PrincipalController {

    private PrincipalView view;
    private Mensagem mensagem;
    private JPanel telas;

    private ContaDao contaDao;
    private PagamentoDao pagamentoDao;

    private TituloController tituloController;
    private ListagemTituloController listagemTituloController;
    private ListagemParcelaController listagemParcelaController;
    private TransferenciaController transferenciaController;

    public PrincipalController(EntityManager em) {
	view = new PrincipalView(this);
	mensagem = new Mensagem(view, view.getTitle());
	telas = view.getPnTelas();

	contaDao = new ContaDao(em);
	pagamentoDao = new PagamentoDao(em);

	tituloController = new TituloController(em, telas);
	listagemTituloController = new ListagemTituloController(em, telas);
	listagemParcelaController = new ListagemParcelaController(em, telas);
	transferenciaController = new TransferenciaController(em, telas);

	atualizaContas();
	view.setExtendedState(view.MAXIMIZED_BOTH);
	view.setVisible(true);
    }

    // refatorar
    public void atualizaContas() {
	StringBuilder layout = new StringBuilder("[40px][1px]");
	BigDecimal saldoGlobal = BigDecimal.ZERO;

	List<Conta> contas = contaDao.findAll();
	int qtdeContas = contas.size();

	List<PanelSaldoConta> panelSaldoContas = new ArrayList<>();

	for (int i = 0; i < qtdeContas; i++) {
	    layout.append("[40px]");

	    Conta conta = contas.get(i);
	    BigDecimal saldo = conta.getSaldoInicial();

	    // novo método
	    saldo = saldo.subtract(pagamentoDao.totalPgtoPorConta(conta));

	    saldoGlobal = saldoGlobal.add(saldo);
	    panelSaldoContas.add(new PanelSaldoConta(conta, saldo));
	}

	view.getPnContas().removeAll();

	// Define layout
	view.getPnContas().setLayout(
		new MigLayout("", "[200px]", layout.toString()));

	Conta saldo = new Conta();
	saldo.setNome("Saldo Global");

	view.getPnContas().add(new PanelSaldoConta(saldo, saldoGlobal),
		"cell 0 0,grow");
	view.getPnContas().add(new JPanel(), "cell 0 1,grow");

	for (int i = 0; i < panelSaldoContas.size(); i++)
	    view.getPnContas().add(panelSaldoContas.get(i),
		    "cell 0 " + (i + 2) + ",grow");

	view.getPnContas().updateUI();
    }

    public void incluirTitulo() {
	tituloController.exibeView();

    }

    public void listarTitulos() {
	listagemTituloController.exibeView();

    }

    public void listarParcelas() {
	listagemParcelaController.exibeView();
    }

    public void fazerTransferencia() {
	transferenciaController.exibeView();
    }
}
