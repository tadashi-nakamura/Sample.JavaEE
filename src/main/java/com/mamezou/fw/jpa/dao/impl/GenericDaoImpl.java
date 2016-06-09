package com.mamezou.fw.jpa.dao.impl;

import com.mamezou.fw.jpa.dao.GenericDao;
import com.mamezou.fw.jpa.entity.Entity;
import com.mamezou.fw.jpa.entity.LogicallyDeletableEntity;

/**
 * 汎用データアクセスオブジェクトベース実装クラス．
 */
public abstract class GenericDaoImpl<E extends Entity<PK>, E2 extends LogicallyDeletableEntity<PK>, PK> 
extends ReadOnlyGenericDaoImpl<E, PK> implements GenericDao<E, E2, PK> {
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
    	entity.setDeleteFlg(1);
        update((E)entity);
    }

    @SuppressWarnings("unchecked")
	public void deleteLogically(PK pk) {
        deleteLogically((E2)em.getReference(this.entityType, pk));
    }
}