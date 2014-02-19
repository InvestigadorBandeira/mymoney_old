package br.com.vga.mymoney.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.dao.ParcelaDao;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ListagemParcelaView;
import br.com.vga.mymoney.view.components.PanelParcela;

public class ListagemParcelaController {

    private final ParcelaDao dao;
    private ListagemParcelaView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public ListagemParcelaController(EntityManager em, JPanel telas) {
	dao = new ParcelaDao(em);

	this.telas = telas;
    }

    public void exibeView() {
	view = new ListagemParcelaView(this);
	filtraPorAbertas();
	mensagem = new Mensagem(view, "Listagem de T�tulos");

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
	List<Parcela> parcelas = new ArrayList<>();

	montaPnParcelas(parcelas);
    }

    private void filtraPorQuitadas() {
	List<Parcela> parcelas = new ArrayList<>();

	montaPnParcelas(parcelas);
    }

    private void filtraPorTodas() {
	montaPnParcelas(dao.findAll());
    }

    private void montaPnParcelas(List<Parcela> parcelas) {
	StringBuilder layout = new StringBuilder("");

	List<PanelParcela> panelParcelas = new ArrayList<>();

	for (Parcela parcela : parcelas)
	    panelParcelas.add(new PanelParcela(parcela));

	for (int i = 0; i < panelParcelas.size(); i++)
	    layout.append("[25px]");

	view.getPnParcelas().removeAll();

	// Define layout
	view.getPnParcelas().setLayout(
		new MigLayout("", "[600px]", layout.toString()));

	for (int i = 0; i < panelParcelas.size(); i++)
	    view.getPnParcelas().add(panelParcelas.get(i),
		    "cell 0 " + i + ",grow");

	view.getPnParcelas().updateUI();
    }
}
