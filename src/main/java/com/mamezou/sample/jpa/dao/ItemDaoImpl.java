package com.mamezou.sample.jpa.dao;

import java.util.Map;

import com.mamezou.fw.jpa.dao.impl.GenericDaoImpl;
import com.mamezou.sample.jpa.entity.Item;

public class ItemDaoImpl extends GenericDaoImpl<Item, Item, Long> implements ItemDao {
	@Override
	protected String getQueryClauses(Map<String, Object> params, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
