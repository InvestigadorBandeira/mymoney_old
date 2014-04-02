package br.com.vga.mymoney.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.pattern.PagamentoObserver;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ParcelaPagamentoView;

public class ParcelaPagamentoController implements PagamentoObserver {

    private final ParcelaDao dao;
    private ParcelaPagamentoView view;
    private Mensagem mensagem;
    List<Parcela> parcelas;

    private final JPanel telas;

    public ParcelaPagamentoController(EntityManager em, JPanel telas) {
	dao = new ParcelaDao(em);

	this.telas = telas;
    }

    public void exibeView() {
	view = new ParcelaPagamentoView(this);
	filtraPorAbertas();
	mensagem = new Mensagem(view, "Listagem de Títulos");
	parcelas = new ArrayList<Parcela>();
	calculaTotal();

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.setVisible(true);
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }

    public void filtrarPor(String filtro) {
	if ("ABERTAS".equals(filtro))
	    filtraPorAbertas();
	else if ("QUITADAS".equals(filtro))
	    filtraPorQuitadas();
	else
	    filtraPorTodas();

    }

    private void filtraPorAbertas() {
	view.montaListagemParcelas(dao.buscaAbertas());
    }

    private void filtraPorQuitadas() {
	view.montaListagemParcelas(dao.buscaQuitadas());
    }

    private void filtraPorTodas() {
	view.montaListagemParcelas(dao.findAll());
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

    }

}
