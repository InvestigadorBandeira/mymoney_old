package br.com.vga.mymoney.controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.TituloDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.TituloView;

public class TituloController {

    private final TituloDao dao;
    private final TituloView view;
    private final Mensagem mensagem;

    private final ContaDao contaDao;

    public TituloController(EntityManager em) {
	view = new TituloView(this);
	dao = new TituloDao(em);
	contaDao = new ContaDao(em);
	contaComboConta();
	view.setVisible(true);
	mensagem = new Mensagem(view, view.getTitle());
    }

    private void contaComboConta() {
	List<Conta> contas = contaDao.findAll();

	if (contas == null || contas.isEmpty()) {
	    mensagem.aviso("É necessário cadastrar Contas primeiro.");
	} else
	    view.montaComboConta(contas);
    }

    public void salvar(Titulo titulo) {
    }
}
