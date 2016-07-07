package com.mamezou.fw.jpa.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import com.mamezou.fw.jpa.entity.Entity;

/**
 * 読み込み専用汎用データアクセスオブジェクトインタフェース．
  */
public interface ReadOnlyGenericDao<E extends Entity<PK>, PK> {
    long countAll(Map<SingularAttribute<E, ?>, ?> params);
    List<E> findAll();
    E findByPk(PK pk);
}
