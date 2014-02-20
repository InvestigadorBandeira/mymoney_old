package br.com.vga.mymoney.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.TransferenciaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.TransferenciaView;

public class TransferenciaController {

    private final TransferenciaDao dao;
    private TransferenciaView view;
    private Mensagem mensagem;

    private final ContaDao contaDao;

    private final JPanel telas;

    public TransferenciaController(EntityManager em, JPanel telas) {
	dao = new TransferenciaDao(em);
	contaDao = new ContaDao(em);

	this.telas = telas;
    }

    public void exibeView() {
	view = new TransferenciaView(this);
	montaCombosConta();
	view.atualizaCampos();
	mensagem = new Mensagem(view, "Cadastro de Títulos");

	telas.removeAll();
	telas.add(view);
	telas.updateUI();
	view.setVisible(true);
    }

    private void montaCombosConta() {
	List<Conta> contas = contaDao.findAll();

	if (contas == null || contas.isEmpty()) {
	    mensagem.aviso("É necessário cadastrar Contas primeiro.");
	} else
	    view.montaCombosConta(contas);
    }

    public void salvar(Transferencia transferencia) {
	dao.save(transferencia);
	view.atualizaCampos();
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }
}
