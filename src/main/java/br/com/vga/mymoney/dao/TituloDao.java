package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Parcela;

public class TituloDao extends AbstractDao<Parcela> {

    public TituloDao(EntityManager em) {
	super(em);
    }

}
