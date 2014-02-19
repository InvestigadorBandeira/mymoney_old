package br.com.vga.mymoney.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import br.com.vga.mymoney.dao.TituloDao;
import br.com.vga.mymoney.entity.Parcela;
import br.com.vga.mymoney.entity.Titulo;
import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.ListagemTituloView;
import br.com.vga.mymoney.view.components.PanelTitulo;

public class ListagemTituloController {

    private final TituloDao dao;
    private ListagemTituloView view;
    private Mensagem mensagem;

    private final JPanel telas;

    public ListagemTituloController(EntityManager em, JPanel telas) {
	dao = new TituloDao(em);

	this.telas = telas;
    }

    public void exibeView() {
	view = new ListagemTituloView(this);
	filtraPorAbertos();
	mensagem = new Mensagem(view, "Listagem de Títulos");

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
	if ("ABERTOS".equals(filtro))
	    filtraPorAbertos();
	else if ("QUITADOS".equals(filtro))
	    filtraPorQuitados();
	else
	    filtraPorTodos();

    }

    private void filtraPorAbertos() {
	List<Titulo> titulos = new ArrayList<>();

	for (Titulo t : dao.findAll()) {
	    boolean paga = true;

	    for (Parcela p : t.getParcelas())
		if (p.getPaga() != null && !p.getPaga())
		    paga = false;

	    if (!paga)
		titulos.add(t);
	}

	montaPnTitulos(titulos);
    }

    private void filtraPorQuitados() {
	List<Titulo> titulos = new ArrayList<>();

	for (Titulo t : dao.findAll()) {
	    boolean paga = true;

	    for (Parcela p : t.getParcelas())
		if (p.getPaga() != null && !p.getPaga())
		    paga = false;

	    if (paga)
		titulos.add(t);
	}

	montaPnTitulos(titulos);
    }

    private void filtraPorTodos() {
	montaPnTitulos(dao.findAll());
    }

    private void montaPnTitulos(List<Titulo> titulos) {
	StringBuilder layout = new StringBuilder("");

	List<PanelTitulo> panelTitulos = new ArrayList<>();

	for (Titulo titulo : titulos)
	    panelTitulos.add(new PanelTitulo(titulo));

	for (int i = 0; i < panelTitulos.size(); i++)
	    layout.append("[25px]");

	view.getPnTitulos().removeAll();

	// Define layout
	view.getPnTitulos().setLayout(
		new MigLayout("", "[600px]", layout.toString()));

	for (int i = 0; i < panelTitulos.size(); i++)
	    view.getPnTitulos().add(panelTitulos.get(i),
		    "cell 0 " + i + ",grow");

	view.getPnTitulos().updateUI();
    }
}
