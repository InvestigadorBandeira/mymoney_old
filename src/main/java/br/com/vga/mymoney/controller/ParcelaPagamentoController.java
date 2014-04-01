package br.com.vga.mymoney.controller;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ParcelaPagamentoView;

public class ParcelaPagamentoController {

    private final ParcelaDao dao;
    private ParcelaPagamentoView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public ParcelaPagamentoController(EntityManager em, JPanel telas) {
	dao = new ParcelaDao(em);

	this.telas = telas;
    }

    public void exibeView() {
	view = new ParcelaPagamentoView(this);
	filtraPorAbertas();
	mensagem = new Mensagem(view, "Listagem de Títulos");

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

}
