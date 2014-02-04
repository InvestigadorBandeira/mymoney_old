package br.com.vga.mymoney.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.dao.TituloDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.TituloView;

public class TituloController {

    private final TituloDao dao;
    private final TituloView view;
    private final Mensagem mensagem;

    private final ContaDao contaDao;
    private final ParcelaDao parcelaDao;

    public TituloController(EntityManager em) {
	view = new TituloView(this);
	dao = new TituloDao(em);
	contaDao = new ContaDao(em);
	parcelaDao = new ParcelaDao(em);
	montaComboConta();
	view.atualizaCampos();
	mensagem = new Mensagem(view, view.getTitle());
	view.setVisible(true);
    }

    private void montaComboConta() {
	List<Conta> contas = contaDao.findAll();

	if (contas == null || contas.isEmpty()) {
	    mensagem.aviso("É necessário cadastrar Contas primeiro.");
	} else
	    view.montaComboConta(contas);
    }

    public void salvar(Titulo titulo) {
	List<Parcela> parcelas = titulo.getParcelas();

	BigDecimal totalParcelas = new BigDecimal("0.0");

	for (Parcela parcela : parcelas)
	    totalParcelas = totalParcelas.add(parcela.getValor());

	if (!titulo.getValor().equals(totalParcelas)) {
	    mensagem.aviso("Valor do título diferente do total das parcelas.");
	    return;
	}

	for (Parcela parcela : parcelas) {
	    parcela.setTitulo(titulo);
	}
	dao.save(titulo);

	view.atualizaCampos();
    }
}
