package br.com.vga.mymoney.controller;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.util.Mensagem;
import br.com.vga.mymoney.view.PrincipalView;

public class PrincipalController {

    private PrincipalView view;
    private Mensagem mensagem;

    public PrincipalController(EntityManager em) {
	view = new PrincipalView(this);

	mensagem = new Mensagem(view, view.getTitle());
	view.setVisible(true);
    }

}
