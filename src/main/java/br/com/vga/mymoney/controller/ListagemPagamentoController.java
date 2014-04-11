package br.com.vga.mymoney.controller;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.PagamentoDao;
import br.com.vga.mymoney.entity.Pagamento;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ListagemPagamentoView;

public class ListagemPagamentoController implements CrudController<Pagamento> {

    private final PagamentoDao dao;
    private ListagemPagamentoView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public ListagemPagamentoController(EntityManager em, JPanel telas) {
	dao = new PagamentoDao(em);

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
	// atualizar saldos Observer
	mensagem.info("Funcionalidade não implementada");
    }

    @Override
    public void visualizar(Pagamento pagamento) {
	mensagem.info("Funcionalidade não implementada.");
    }

}
