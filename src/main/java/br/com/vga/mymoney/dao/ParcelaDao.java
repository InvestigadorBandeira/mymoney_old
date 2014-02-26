package br.com.vga.mymoney.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.vga.mymoney.entity.Parcela;

public class ParcelaDao extends AbstractDao<Parcela> {

    public ParcelaDao(EntityManager em) {
	super(em);
    }

    public List<Parcela> buscaAbertas() {
	String jpql = "SELECT p FROM Parcela p WHERE p.paga = false ORDER BY dataVencimento";
	Query query = em.createQuery(jpql);
	return query.getResultList();
    }

    public List<Parcela> buscaQuitadas() {
	String jpql = "SELECT p FROM Parcela p WHERE p.paga = true ORDER BY dataVencimento";
	Query query = em.createQuery(jpql);
	return query.getResultList();
    }
}
