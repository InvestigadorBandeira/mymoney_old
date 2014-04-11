package br.com.vga.mymoney.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.TransferenciaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.pattern.SaldoObserver;
import br.com.vga.mymoney.util.Formatador;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.TransferenciaView;

public class TransferenciaController implements CrudController<Transferencia> {

    private final TransferenciaDao dao;
    private TransferenciaView view;
    private Mensagem mensagem;

    private final ContaDao contaDao;

    private final SaldoObserver observer;
    private final JPanel telas;

    public TransferenciaController(EntityManager em, SaldoObserver observer,
	    JPanel telas) {
	dao = new TransferenciaDao(em);
	contaDao = new ContaDao(em);

	this.observer = observer;
	this.telas = telas;
    }

    public void exibeView() {
	view = new TransferenciaView(this);
	montaCombosConta();
	view.atualizaCampos();
	filtraPorTodas();
	mensagem = new Mensagem(view, "Cadastro de Transferências");

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

    @Override
    public void salvar(Transferencia transferencia) {
	dao.save(transferencia);
	view.atualizaCampos();
	filtraPorTodas();
	observer.atualizaSaldoContas();
    }

    @Override
    public void alterar(Transferencia transferencia) {
	mensagem.info("Funcionalidade não implementada.");
	// observer.atualizaSaldoContas();
    }

    @Override
    public void excluir(Transferencia transferencia) {

	int confirma = mensagem.confirma(String.format(
		"Deseja excluir a Transferência:\nDe: %s\nPara: %s\nData: "
			+ "%s\nDescrição: %s\nValor: %s\nObservação: %s",
		transferencia.getContaOrigem(),
		transferencia.getContaDestino(),
		Formatador.dataTexto(transferencia.getData()),
		transferencia.getDescricao(),
		Formatador.valorTexto(transferencia.getValor()),
		transferencia.getObservacao()));

	if (confirma == 0) {
	    dao.delete(transferencia);
	    view.montaListagemTransferencias(dao.findAll());
	    observer.atualizaSaldoContas();
	}
    }

    @Override
    public void visualizar(Transferencia transferencia) {
	mensagem.info("Funcionalidade não implementada.");
    }

    // implementar filtro data
    public void filtraPorTodas() {
	view.montaListagemTransferencias(dao.findAll());
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }
}
