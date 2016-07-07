package com.mamezou.fw.jpa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.mamezou.fw.jpa.dao.ReadOnlyGenericDao;
import com.mamezou.fw.jpa.entity.Entity;

/**
 * 読み込み専用汎用データアクセスオブジェクトベース実装クラス．
 */
public abstract class ReadOnlyGenericDaoImpl<E extends Entity<PK>, PK> implements ReadOnlyGenericDao<E, PK> {
    /** entity manager */
    @Inject
    protected EntityManager em;

    /** entity type */
    protected final Class<E> entityType;

    @SuppressWarnings("unchecked")
	public ReadOnlyGenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.entityType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    public long countAll(Map<SingularAttribute<E, ?>, ?> params) {
        final StringBuilder queryString = new StringBuilder( "SELECT count(e) from ");
        queryString.append(this.entityType.getSimpleName()).append(" e ");
        StringBuilder condition = new StringBuilder("where ");
        params.forEach((attribute, value) -> {
            condition.append(attribute.getName()).append(" = :").append(attribute.getName()).append(" and ");
        });
        
        queryString.append(condition);

        TypedQuery<Long> query = this.em.createQuery(queryString.toString(), Long.class);
        params.forEach((name, value) -> {
            query.setParameter(name.getName(), value);
        });

        return query.getSingleResult();
    }

    protected abstract CharSequence getConditionClauses(Map<SingularAttribute<E, ?>, ?> params);

    public long countAllCriteria(Map<SingularAttribute<E, ?>, ?> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<E> root = query.from(entityType);
        query.select(cb.count(root));
        query = appendCriteriaQueryCondition(cb, root, query, params);
        return em.createQuery(query).getSingleResult();
    }

    private <T> CriteriaQuery<T> appendCriteriaQueryCondition(CriteriaBuilder cb, Root<E> root,
            CriteriaQuery<T> query, Map<SingularAttribute<E, ?>, ?> params) {
        params.forEach((name, value) -> {
            query.where(cb.equal(root.get(name), value));
        });
        return query;
    }

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

    public E findByPkWithLock(PK pk, Map<String,Object> options) {
		// options.put("javax.persistence.lock.timeout", 0);
		return em.find(this.entityType, pk, LockModeType.PESSIMISTIC_WRITE, options);
    }
}