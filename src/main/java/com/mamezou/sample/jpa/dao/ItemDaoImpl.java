package com.mamezou.sample.jpa.dao;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import com.mamezou.fw.jpa.dao.impl.GenericDaoImpl;
import com.mamezou.sample.jpa.entity.Item;
import com.mamezou.sample.jpa.entity.Item_;

public class ItemDaoImpl extends GenericDaoImpl<Item, Item, Long, Boolean> implements ItemDao {
	@Override
	protected CharSequence getConditionClauses(Map<SingularAttribute<Item, ?>, ?> params) {
	    StringBuilder condition = new StringBuilder("where ");
		params.forEach((attribute, value) -> {
		    condition.append(attribute.getName()).append(" = :").append(attribute.getName()).append(" and ");
		});
		return condition;
	}
	
    public void metamodel() {
	    Metamodel metamodel = em.getMetamodel();
	    EntityType<Item> type = metamodel.entity(this.entityType);
	    @SuppressWarnings("unchecked")
	    Attribute<Item, String> attr = (Attribute<Item, String>) type.getAttribute("value1");
	    System.out.println(attr.getName().equals(Item_.value1.getName()));
	}
}
