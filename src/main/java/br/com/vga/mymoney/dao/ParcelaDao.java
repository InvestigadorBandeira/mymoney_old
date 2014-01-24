package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Parcela;

public class ParcelaDao extends AbstractDao<Parcela> {

    public ParcelaDao(EntityManager em) {
	super(em);
    }

}
