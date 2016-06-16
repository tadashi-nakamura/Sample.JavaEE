package com.mamezou.sample.jpa.dao;

import com.mamezou.fw.jpa.dao.GenericDao;
import com.mamezou.sample.jpa.entity.Item;

public interface ItemDao extends GenericDao<Item, Item, Long, Boolean> {
}
