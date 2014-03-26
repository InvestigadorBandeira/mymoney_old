package br.com.vga.mymoney.controller;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.GrupoDao;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.GrupoView;

public class GrupoController implements CrudController<Grupo> {

    private final GrupoDao dao;
    private GrupoView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public GrupoController(EntityManager em, JPanel telas) {
	dao = new GrupoDao(em);
	this.telas = telas;
    }

    public void exibeView() {
	view = new GrupoView(this);
	view.atualizaCampos();
	mensagem = new Mensagem(view, "Cadastro de Grupos");

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.montaListagemGrupos(dao.findAll());
	view.setVisible(true);
    }

    @Override
    public void salvar(Grupo grupo) {
	if (dao.existeNome(grupo.getNome())) {
	    mensagem.aviso("Já existe Grupo cadastrado com esse nome.");
	    return;
	}

	dao.save(grupo);
	view.atualizaCampos();
	view.montaListagemGrupos(dao.findAll());
    }

    @Override
    public void atualizar(Grupo grupo) {
	mensagem.info("Funcionalidade não implementada.");
    }

    @Override
    public void excluir(Grupo grupo) {
	mensagem.info("Funcionalidade não implementada.");
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }

}
