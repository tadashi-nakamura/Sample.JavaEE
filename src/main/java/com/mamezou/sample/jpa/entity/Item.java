package com.mamezou.sample.jpa.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.mamezou.fw.jpa.converter.NullConverter;
import com.mamezou.fw.jpa.entity.BaseAuditableEntity;

// TODO EntityListener
@Entity
@Table(name="M_ITEM")
public class Item extends BaseAuditableEntity<Long, Boolean, Timestamp, String> {

	@Id
	@SequenceGenerator(sequenceName="ITEM_SEQ", name = "ItemSeq")
	private Long pk;
	
	@Column(nullable = false)
	@Convert(converter = NullConverter.class, disableConversion = false)
	private String value1;

	@Override
	public Long getPk() {
		return pk;
	}

	@Override
	public void delete() {
		setDeleteFlg(Boolean.TRUE);
	}

}
