package br.com.vga.mymoney.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.dao.ReceitaDao;
import br.com.vga.mymoney.dao.TransferenciaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Receita;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.pattern.SaldoObserver;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.PrincipalView;

public class PrincipalController implements SaldoObserver {

    private final PrincipalView view;
    private final Mensagem mensagem;
    private final JPanel telas;

    private final ContaDao contaDao;
    private final PagamentoDao pagamentoDao;
    private final TransferenciaDao transferenciaDao;
    private final ReceitaDao receitaDao;

    private final TituloController tituloController;
    private final ListagemTituloController listagemTituloController;
    private final ListagemParcelaController listagemParcelaController;
    private final TransferenciaController transferenciaController;
    private final GrupoController grupoController;
    private final ContaController contaController;
    private final CategoriaController categoriaController;
    private final SubCategoriaController subCategoriaController;
    private final ReceitaController receitaController;
    private ParcelaPagamentoController parcelaPagamentoController;

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
	transferenciaController = new TransferenciaController(em, this, telas);
	grupoController = new GrupoController(em, telas);
	contaController = new ContaController(em, this, telas);
	categoriaController = new CategoriaController(em, telas);
	subCategoriaController = new SubCategoriaController(em, telas);
	receitaController = new ReceitaController(em, this, telas);
	parcelaPagamentoController = new ParcelaPagamentoController(em, telas);

	atualizaSaldoContas();
	view.setExtendedState(view.MAXIMIZED_BOTH);
	view.setVisible(true);
    }

    @Override
    public void atualizaSaldoContas() {
	BigDecimal valorSaldoGlobal = BigDecimal.ZERO;

	Conta contaSaldoGlobal = new Conta();
	contaSaldoGlobal.setNome("Saldo Global");

	Map<Conta, BigDecimal> saldos = new HashMap<Conta, BigDecimal>();

	List<Conta> contas = contaDao.findAll();
	int qtdeContas = contas.size();

	for (int i = 0; i < qtdeContas; i++) {

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

	    valorSaldoGlobal = valorSaldoGlobal.add(saldo);

	    saldos.put(conta, saldo);
	}

	saldos.put(contaSaldoGlobal, valorSaldoGlobal);

	view.montaListagemSaldoContas(saldos);
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

    public void incluirSubCategoria() {
	subCategoriaController.exibeView();
    }

    public void lancarReceita() {
	receitaController.exibeView();
    }

    public void parcelasAPagar() {
	parcelaPagamentoController.exibeView();
    }
}
