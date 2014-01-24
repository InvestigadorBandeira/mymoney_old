package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Titulo;

public class TituloDao extends AbstractDao<Titulo> {

    public TituloDao(EntityManager em) {
	super(em);
    }

}
