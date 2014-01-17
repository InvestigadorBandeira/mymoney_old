package br.com.vga.mymoney.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

    private static EntityManager em = null;

    static {
	EntityManagerFactory emf = Persistence
		.createEntityManagerFactory("mymoney");

	em = emf.createEntityManager();
    }

    public static EntityManager getInstance() {
	return em;
    }
}
