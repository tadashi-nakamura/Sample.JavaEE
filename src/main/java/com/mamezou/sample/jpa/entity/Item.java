package com.mamezou.sample.jpa.entity;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.mamezou.fw.jpa.entity.BaseEntity;

// TODO EntityListener
@SqlResultSetMapping(
		name = "value1_count",
		columns={
				@ColumnResult(name=""),
				@ColumnResult(name=""),
		}
		)
@NamedNativeQuery(
		name = "Item.findAll", 
		query = "SELECT e FROM Item e")
@Entity
@Table(name="M_ITEM")
public class Item extends BaseEntity<Long, Boolean> {

	@Id
	@SequenceGenerator(sequenceName="ITEM_SEQ", name = "ItemSeq")
	private Long pk;
	
	@Column(nullable = false)
//	@Convert(converter = NullConverter.class, disableConversion = false)
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
