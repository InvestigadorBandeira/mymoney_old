package br.com.vga.mymoney.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.GrupoDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Grupo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ContaView;

public class ContaController implements CrudController<Conta> {

    private final ContaDao dao;
    private GrupoDao grupoDao;
    private ContaView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public ContaController(EntityManager em, JPanel telas) {
	dao = new ContaDao(em);
	grupoDao = new GrupoDao(em);
	this.telas = telas;
    }

    public void exibeView() {
	view = new ContaView(this);
	view.atualizaCampos();
	mensagem = new Mensagem(view, "Cadastro de Contas");
	montaComboGrupo();

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.montaListagemContas(dao.findAll());
	view.setVisible(true);
    }

    private void montaComboGrupo() {
	List<Grupo> grupos = grupoDao.findAll();

	if (grupos == null || grupos.isEmpty()) {
	    mensagem.aviso("É necessário cadastrar Grupos primeiro.");
	} else
	    view.montaComboGrupo(grupos);
    }

    @Override
    public void salvar(Conta contas) {
	dao.save(contas);
	view.atualizaCampos();
	view.montaListagemContas(dao.findAll());
    }

    @Override
    public void atualizar(Conta conta) {
	mensagem.info("Funcionalidade não implementada.");
    }

    @Override
    public void excluir(Conta conta) {

	int confirma = mensagem.confirma("Deseja excluir o grupo: "
		+ conta.getNome());

	if (confirma == 0) {
	    dao.delete(conta);
	    view.montaListagemContas(dao.findAll());
	}
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }

}
