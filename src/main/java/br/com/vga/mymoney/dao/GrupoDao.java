package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Grupo;

public class GrupoDao extends AbstractDao<Grupo> {

    public GrupoDao(EntityManager em) {
	super(em);
    }

}
