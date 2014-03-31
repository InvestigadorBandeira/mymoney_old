package br.com.vga.mymoney.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.CategoriaDao;
import br.com.vga.mymoney.dao.SubCategoriaDao;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.entity.SubCategoria;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.SubCategoriaView;

public class SubCategoriaController implements CrudController<SubCategoria> {

    private final SubCategoriaDao dao;
    private CategoriaDao categoriaDao;
    private SubCategoriaView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public SubCategoriaController(EntityManager em, JPanel telas) {
	dao = new SubCategoriaDao(em);
	categoriaDao = new CategoriaDao(em);
	this.telas = telas;
    }

    public void exibeView() {
	view = new SubCategoriaView(this);
	view.atualizaCampos();
	mensagem = new Mensagem(view, "Cadastro de SubCategorias");
	montaComboCategoria();

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.montaListagemSubCategorias(dao.findAll());
	view.setVisible(true);
    }

    private void montaComboCategoria() {
	List<Categoria> categorias = categoriaDao.findAll();

	if (categorias == null || categorias.isEmpty()) {
	    mensagem.aviso("É necessário cadastrar Categorias primeiro.");
	} else
	    view.montaComboCategoria(categorias);
    }

    @Override
    public void salvar(SubCategoria subCategoria) {
	dao.save(subCategoria);
	view.atualizaCampos();
	view.montaListagemSubCategorias(dao.findAll());
    }

    @Override
    public void atualizar(SubCategoria subCategoria) {
	mensagem.info("Funcionalidade não implementada.");
    }

    @Override
    public void excluir(SubCategoria subCategoria) {

	int confirma = mensagem.confirma("Deseja excluir a SubCategoria: "
		+ subCategoria.getNome());

	if (confirma == 0) {
	    dao.delete(subCategoria);
	    view.montaListagemSubCategorias(dao.findAll());
	}
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }

}
