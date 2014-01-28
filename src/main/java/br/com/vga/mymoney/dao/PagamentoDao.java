package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Pagamento;

public class PagamentoDao extends AbstractDao<Pagamento> {

    public PagamentoDao(EntityManager em) {
	super(em);
    }

}
