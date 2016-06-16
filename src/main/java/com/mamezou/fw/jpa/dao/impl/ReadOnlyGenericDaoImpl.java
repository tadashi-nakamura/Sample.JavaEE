package com.mamezou.fw.jpa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.mamezou.fw.jpa.dao.ReadOnlyGenericDao;
import com.mamezou.fw.jpa.entity.Entity;

/**
 * 読み込み専用汎用データアクセスオブジェクトベース実装クラス．
 */
public abstract class ReadOnlyGenericDaoImpl<E extends Entity<PK>, PK> implements ReadOnlyGenericDao<E, PK> {
    /** entity manager */
    @PersistenceContext
    protected EntityManager em;

    /** entity type */
    protected final Class<E> entityType;

    @SuppressWarnings("unchecked")
	public ReadOnlyGenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.entityType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    public long countAll(Map<String, Object> params) {
        final StringBuilder queryString = new StringBuilder( "SELECT count(o) from ");

        queryString.append(this.entityType.getSimpleName()).append(" o ");
        queryString.append(this.getQueryClauses(params, null));

        final Query query = this.em.createQuery(queryString.toString());

        return (Long) query.getSingleResult();
    }

    protected abstract String getQueryClauses(Map<String, Object> params, Object object);

	public List<E> findAll() {
        CriteriaQuery<E> criteria = em.getCriteriaBuilder().createQuery(this.entityType);
        return em.createQuery(criteria.select(criteria.from(this.entityType))).getResultList();
    }

    public List<E> findAll(Order order, String... propertiesOrder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(this.entityType);
        Root<E> root = cq.from(this.entityType);

        List<javax.persistence.criteria.Order> orders = new ArrayList<>();
        for (String propertyOrder : propertiesOrder) {
            if (order.isAscending()) {
                orders.add(cb.asc(root.get(propertyOrder)));
            } else {
                orders.add(cb.desc(root.get(propertyOrder)));
            }
        }
        cq.orderBy(orders);

        return em.createQuery(cq).getResultList();
    }

    public E findByPk(PK pk) {
        return em.find(this.entityType, pk);
    }

    public E findByPkWithLock(PK pk) {
        return em.find(this.entityType, pk, LockModeType.PESSIMISTIC_WRITE);
    }
}