package br.com.vga.mymoney.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.vga.mymoney.entity.Conta;
import br.com.vga.mymoney.entity.Pagamento;

public class PagamentoDao extends AbstractDao<Pagamento> {

    public PagamentoDao(EntityManager em) {
	super(em);
    }

    public BigDecimal totalPgtoPorConta(Conta conta) {

	String jpql = "SELECT SUM(p.valorTotal) FROM Pagamento p WHERE"
		+ " p.conta = :conta AND p.data <= CURRENT_DATE";

	TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
	query.setParameter("conta", conta);

	BigDecimal total = query.getSingleResult();

	return total != null ? total : BigDecimal.ZERO;

    }
}
