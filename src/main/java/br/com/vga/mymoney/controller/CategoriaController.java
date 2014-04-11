package br.com.vga.mymoney.controller;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.CategoriaDao;
import br.com.vga.mymoney.entity.Categoria;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.CategoriaView;

public class CategoriaController implements CrudController<Categoria> {

    private final CategoriaDao dao;
    private CategoriaView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public CategoriaController(EntityManager em, JPanel telas) {
	dao = new CategoriaDao(em);
	this.telas = telas;
    }

    public void exibeView() {
	view = new CategoriaView(this);
	view.atualizaCampos();
	mensagem = new Mensagem(view, "Cadastro de Categorias");

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.montaListagemCategorias(dao.findAll());
	view.setVisible(true);
    }

    @Override
    public void salvar(Categoria categoria) {
	if (dao.existeNome(categoria.getNome())) {
	    mensagem.aviso("Já existe Categoria cadastrado com esse nome.");
	    return;
	}

	dao.save(categoria);
	view.atualizaCampos();
	view.montaListagemCategorias(dao.findAll());
    }

    @Override
    public void alterar(Categoria categoria) {
	mensagem.info("Funcionalidade não implementada.");
    }

    @Override
    public void excluir(Categoria categoria) {
	if (categoria.getSubCategorias() != null
		&& !categoria.getSubCategorias().isEmpty()) {
	    mensagem.info("Existem SubCategorias vinculadas a essa categoria.");
	    return;
	}

	int confirma = mensagem.confirma("Deseja excluir a Categoria: "
		+ categoria.getNome());

	if (confirma == 0) {
	    dao.delete(categoria);
	    view.montaListagemCategorias(dao.findAll());
	}
    }

    @Override
    public void visualizar(Categoria categoria) {
	mensagem.info("Funcionalidade não implementada.");
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }

}
