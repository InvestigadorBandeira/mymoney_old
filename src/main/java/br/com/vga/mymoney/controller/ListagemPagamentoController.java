package br.com.vga.mymoney.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.entity.Pagamento;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.pattern.SaldoObserver;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ListagemPagamentoView;

public class ListagemPagamentoController implements CrudController<Pagamento> {

    private final PagamentoDao dao;
    private ParcelaDao parcelaDao;
    private ListagemPagamentoView view;
    private Mensagem mensagem;

    private final JPanel telas;
    private SaldoObserver observer;

    public ListagemPagamentoController(EntityManager em,
	    SaldoObserver observer, JPanel telas) {
	dao = new PagamentoDao(em);
	parcelaDao = new ParcelaDao(em);

	this.observer = observer;
	this.telas = telas;
    }

    public void exibeView() {
	view = new ListagemPagamentoView(this);
	filtraPorTodas();
	mensagem = new Mensagem(view, "Listagem de Pagamentos");

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
	// if ("ABERTAS".equals(filtro))
	// filtraPorAbertas();
	// else if ("QUITADAS".equals(filtro))
	// filtraPorQuitadas();
	// else
	filtraPorTodas();
    }

    private void filtraPorAbertas() {
    }

    private void filtraPorQuitadas() {
    }

    private void filtraPorTodas() {
	view.montaPnPagamentos(dao.findAll());
    }

    @Override
    public void salvar(Pagamento pagamento) {
    }

    @Override
    public void alterar(Pagamento pagamento) {
    }

    @Override
    public void excluir(Pagamento pagamento) {
	int confirma = mensagem.confirma(String.format("Deseja excluir o "
		+ "Pagamento:\nConta: %s\nData: %s\nValor Total: %s",
		pagamento.getConta(),
		Formatador.dataTexto(pagamento.getData()),
		Formatador.valorTexto(pagamento.getValorTotal())));

	if (confirma == 0) {
	    for (Parcela parcela : pagamento.getParcelas()) {
		parcela.setDesconto(BigDecimal.ZERO);
		parcela.setAcrescimo(BigDecimal.ZERO);
		parcela.setPagamento(null);
		parcela.setPaga(false);
		parcelaDao.update(parcela);
	    }
	    dao.delete(pagamento);
	    filtraPorTodas();
	    observer.atualizaSaldoContas();
	}
    }

    @Override
    public void visualizar(Pagamento pagamento) {
	mensagem.info("Funcionalidade não implementada.");
    }

}
