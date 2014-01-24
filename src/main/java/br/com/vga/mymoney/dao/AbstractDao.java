package br.com.vga.mymoney.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class AbstractDao<T> {

    protected final EntityManager em;
    private final Class<T> persistent;

    public AbstractDao(EntityManager em) {

	ParameterizedType type = (ParameterizedType) getClass()
		.getGenericSuperclass();

	this.persistent = (Class<T>) type.getActualTypeArguments()[0];
	this.em = em;
    }

    public void begin() {
	em.getTransaction().begin();
    }

    public void commit() {
	em.getTransaction().commit();
    }

    public void save(T t) {
	begin();
	em.persist(t);
	commit();
    }

    public void delete(T t) {
	begin();
	em.remove(em.merge(t));
	commit();
    }

    public T update(T t) {
	begin();
	t = em.merge(t);
	commit();
	return t;
    }

    public T findById(Long id) {
	return em.find(persistent, id);
    }

    public List<T> findAll() {
	CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
	cq.select(cq.from(persistent));
	return em.createQuery(cq).getResultList();
    }
}
