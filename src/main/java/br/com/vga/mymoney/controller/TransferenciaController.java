package br.com.vga.mymoney.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.dao.ContaDao;
import br.com.vga.mymoney.dao.TransferenciaDao;
import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Transferencia;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.TransferenciaView;
import br.com.vga.mymoney.view.components.PanelTransferencia;
import br.com.vga.mymoney.view.tables.PanelHearder;

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
	filtraPorTodas();
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

    // tem que atualizar saldos no global
    public void salvar(Transferencia transferencia) {
	dao.save(transferencia);
	view.atualizaCampos();
	filtraPorTodas();
    }

    // implementar filtro data
    public void filtraPorTodas() {
	montaPnListTransferencias(dao.findAll());
    }

    private void montaPnListTransferencias(List<Transferencia> transferencias) {
	if (transferencias == null || transferencias.isEmpty())
	    return;

	StringBuilder layout = new StringBuilder("[25px]");

	List<PanelTransferencia> panelTransferencias = new ArrayList<>();

	for (Transferencia transferencia : transferencias)
	    panelTransferencias.add(new PanelTransferencia(transferencia));

	for (int i = 0; i < panelTransferencias.size(); i++)
	    layout.append("[25px]");

	view.getPnListTransferencias().removeAll();

	// Define layout
	view.getPnListTransferencias().setLayout(
		new MigLayout("", "[835px]", layout.toString()));

	view.getPnListTransferencias().add(
		new PanelHearder(panelTransferencias.get(0)), "cell 0 0,grow");

	for (int i = 0; i < panelTransferencias.size(); i++)
	    view.getPnListTransferencias().add(panelTransferencias.get(i),
		    "cell 0 " + (i + 1) + ",grow");

	view.getPnListTransferencias().updateUI();
    }

    public void sair() {
	telas.removeAll();
	telas.updateUI();
    }
}
