package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Categoria;

public class CategoriaDao extends AbstractDao<Categoria> {

    public CategoriaDao(EntityManager em) {
	super(em);
    }

}
