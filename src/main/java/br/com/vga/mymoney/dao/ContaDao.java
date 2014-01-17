package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Conta;

public class ContaDao extends AbstractDao<Conta> {

    public ContaDao(EntityManager em) {
	super(em);
    }

}
