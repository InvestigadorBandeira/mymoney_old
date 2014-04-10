package br.com.vga.mymoney.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.pattern.PagamentoObserver;
import br.com.vga.mymoney.pattern.SaldoObserver;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.PagamentoView;
import br.com.vga.mymoney.view.ParcelaPagamentoView;

public class ParcelaPagamentoController implements PagamentoObserver {

    private final ParcelaDao parcelaDao;
    private final ContaDao contaDao;
    private final PagamentoDao pagamentoDao;
    private ParcelaPagamentoView view;
    private Mensagem mensagem;
    private List<Parcela> parcelas;

    private final JPanel telas;
    private final SaldoObserver observer;

    public ParcelaPagamentoController(EntityManager em, SaldoObserver observer,
	    JPanel telas) {
	parcelaDao = new ParcelaDao(em);
	contaDao = new ContaDao(em);
	pagamentoDao = new PagamentoDao(em);

	this.observer = observer;
	this.telas = telas;
    }

    public void exibeView() {
	view = new ParcelaPagamentoView(this);
	filtraPorAbertas();
	mensagem = new Mensagem(view, "Listagem de Títulos");
	zeraParcelas();

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.setVisible(true);
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }

    private void zeraParcelas() {
	parcelas = new ArrayList<Parcela>();
	calculaTotal();
    }

    public void filtrarPor(String filtro) {
	// if ("ABERTAS".equals(filtro))
	filtraPorAbertas();
	// else if ("QUITADAS".equals(filtro))
	// filtraPorQuitadas();
	// else
	// filtraPorTodas();

	zeraParcelas();
    }

    private void filtraPorAbertas() {
	view.montaListagemParcelas(parcelaDao.buscaAbertas());
    }

    private void filtraPorQuitadas() {
	// view.montaListagemParcelas(dao.buscaQuitadas());
    }

    private void filtraPorTodas() {
	// view.montaListagemParcelas(dao.findAll());
    }

    @Override
    public void calculaTotal() {
	BigDecimal total = BigDecimal.ZERO;

	for (Parcela p : parcelas)
	    total = total.add(p.getValor().add(
		    p.getAcrescimo().subtract(p.getDesconto())));

	view.atualizaTotalAPagar(total);
    }

    @Override
    public void incluirParcela(Parcela parcela) {
	parcelas.add(parcela);
	calculaTotal();
    }

    @Override
    public void excluirParcela(Parcela parcela) {
	parcelas.remove(parcela);
	calculaTotal();
    }

    public void pagarParcelas() {
	if (parcelas == null || parcelas.isEmpty()) {
	    mensagem.aviso("Não existem parcelas selecionadas.");
	    return;
	}

	PagamentoView view = new PagamentoView(parcelas, contaDao,
		pagamentoDao, parcelaDao);
	view.setVisible(true);

	if (parcelas.isEmpty()) {
	    zeraParcelas();
	    filtraPorAbertas();
	    observer.atualizaSaldoContas();
	}

    }

}
