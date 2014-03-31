package br.com.vga.mymoney.dao;

import javax.persistence.EntityManager;

import br.com.vga.mymoney.entity.Categoria;

public class CategoriaDao extends AbstractDao<Categoria> {

    public CategoriaDao(EntityManager em) {
	super(em);
    }

    public boolean existeNome(String nome) {
	// sem op��o de ignorecase
	for (Categoria c : findAll())
	    if (c.getNome().equalsIgnoreCase(nome))
		return true;

	return false;
    }

}
