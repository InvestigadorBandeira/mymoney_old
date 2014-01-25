package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Receita;

public class ReceitaDao extends AbstractDao<Receita> {

    public ReceitaDao(EntityManager em) {
	super(em);
    }

}
