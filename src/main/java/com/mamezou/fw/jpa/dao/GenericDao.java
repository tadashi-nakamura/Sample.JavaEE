package com.mamezou.fw.jpa.dao;

import com.mamezou.fw.jpa.entity.Entity;
import com.mamezou.fw.jpa.entity.LogicallyDeletableEntity;

/**
 * 汎用データアクセスオブジェクトインタフェース．
 */
public interface GenericDao<E extends Entity<PK>, E2 extends LogicallyDeletableEntity<PK, DF>, PK, DF>
		extends ReadOnlyGenericDao<E, PK> {
	void persist(E entity);

	void update(E entity);

	void remove(E entity);

	void remove(PK pk);

	void deleteLogically(E2 entity);

	void deleteLogically(PK pk);
}