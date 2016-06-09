package com.mamezou.sample.jpa.entity;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.mamezou.fw.jpa.entity.BaseEntity;

// TODO EntityListener
public class Item extends BaseEntity<Long> {

	@Id @SequenceGenerator(sequenceName="ITEM_SEQ", name = "ItemSeq")
	private Long pk;
	
	// TODO AttributeConverter

	@Override
	public Long getPk() {
		return pk;
	}

}
