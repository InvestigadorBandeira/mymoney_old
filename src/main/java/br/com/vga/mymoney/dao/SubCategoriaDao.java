package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.SubCategoria;

public class SubCategoriaDao extends AbstractDao<SubCategoria> {

    public SubCategoriaDao(EntityManager em) {
	super(em);
    }

}
