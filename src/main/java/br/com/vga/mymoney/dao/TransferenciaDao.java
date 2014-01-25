package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Transferencia;

public class TransferenciaDao extends AbstractDao<Transferencia> {

    public TransferenciaDao(EntityManager em) {
	super(em);
    }

}
