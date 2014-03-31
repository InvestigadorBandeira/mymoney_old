package br.com.vga.mymoney.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.dao.ReceitaDao;
import br.com.vga.mymoney.dao.TransferenciaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Receita;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.PrincipalView;
import br.com.vga.mymoney.view.components.PanelSaldoConta;

public class PrincipalController {

    private PrincipalView view;
    private Mensagem mensagem;
    private JPanel telas;

    private ContaDao contaDao;
    private PagamentoDao pagamentoDao;
    private TransferenciaDao transferenciaDao;
    private ReceitaDao receitaDao;

    private TituloController tituloController;
    private ListagemTituloController listagemTituloController;
    private ListagemParcelaController listagemParcelaController;
    private TransferenciaController transferenciaController;
    private GrupoController grupoController;
    private ContaController contaController;
    private CategoriaController categoriaController;

    public PrincipalController(EntityManager em) {
	view = new PrincipalView(this);
	mensagem = new Mensagem(view, view.getTitle());
	telas = view.getPnTelas();

	contaDao = new ContaDao(em);
	pagamentoDao = new PagamentoDao(em);
	transferenciaDao = new TransferenciaDao(em);
	receitaDao = new ReceitaDao(em);

	tituloController = new TituloController(em, telas);
	listagemTituloController = new ListagemTituloController(em, telas);
	listagemParcelaController = new ListagemParcelaController(em, telas);
	transferenciaController = new TransferenciaController(em, telas);
	grupoController = new GrupoController(em, telas);
	contaController = new ContaController(em, telas);
	categoriaController = new CategoriaController(em, telas);

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

	    // refatorar
	    for (Transferencia t : transferenciaDao.findAll())
		if (t.getContaOrigem().equals(conta))
		    saldo = saldo.subtract(t.getValor());
		else if (t.getContaDestino().equals(conta))
		    saldo = saldo.add(t.getValor());

	    // refatorar
	    for (Receita r : receitaDao.findAll())
		if (r.getConta().equals(conta))
		    saldo = saldo.add(r.getValor());

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

    public void incluirGrupo() {
	grupoController.exibeView();
    }

    public void incluirConta() {
	contaController.exibeView();
    }

    public void incluirCategoria() {
	categoriaController.exibeView();
    }
}
