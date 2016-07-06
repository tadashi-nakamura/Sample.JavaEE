package com.mamezou.sample.jpa.dao;

import java.util.Map;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.mamezou.fw.jpa.dao.impl.GenericDaoImpl;
import com.mamezou.sample.jpa.entity.Item;
import com.mamezou.sample.jpa.entity.Item_;

public class ItemDaoImpl extends GenericDaoImpl<Item, Item, Long, Boolean> implements ItemDao {
	@Override
	protected String getQueryClauses(Map<String, Object> params, Object object) {
		return null;
	}
	
    public void metamodel() {
	    Metamodel metamodel = em.getMetamodel();
	    EntityType<Item> type = metamodel.entity(this.entityType);
	    @SuppressWarnings("unchecked")
	    Attribute<Item, String> attr = (Attribute<Item, String>) type.getAttribute("value1");
	    System.out.println(attr.getName().equals(Item_.value1.getName()));
	}
}
