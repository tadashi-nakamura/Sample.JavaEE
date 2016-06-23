package com.mamezou.fw.jpa.dao.impl;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

import com.mamezou.fw.jpa.dao.GenericDao;
import com.mamezou.fw.jpa.entity.Entity;
import com.mamezou.fw.jpa.entity.LogicallyDeletable;

/**
 * 汎用データアクセスオブジェクトベース実装クラス．
 */
public abstract class GenericDaoImpl<E extends Entity<PK>, 
		E2 extends LogicallyDeletable<PK, DF>, PK, DF> 
	extends ReadOnlyGenericDaoImpl<E, PK> implements GenericDao<E, E2, PK, DF> {
	
	private static final String JNDI_ENV = "java:app/entitymanager/";

    @Resource
    private SessionContext ctx;	
	
    public void persist(E entity) {
        em.persist(entity);
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public void remove(E entity) {
        remove(entity.getPk());
    }

    public void remove(PK pk) {
        em.remove(em.getReference(this.entityType, pk));
    }

    @SuppressWarnings("unchecked")
	public void deleteLogically(E2 entity) {
    	entity.delete();
        update((E)entity);
    }

    @SuppressWarnings("unchecked")
	public void deleteLogically(PK pk) {
        deleteLogically((E2)em.getReference(this.entityType, pk));
    }
    
    private EntityManager entityManager(String dataSourceName) {

        final EntityManager entityManager = (EntityManager) ctx.lookup(JNDI_ENV + dataSourceName);

        if (entityManager == null) {
            throw new RuntimeException("Unknown data source name '" + dataSourceName + "'.");
        }

        return entityManager;

    }

}