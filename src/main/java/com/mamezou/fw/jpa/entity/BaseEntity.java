package com.mamezou.fw.jpa.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity<PK> implements LogicallyDeletableEntity<PK> {

	@Column(name = "DELETE_FLG", nullable = false)
	private int deleteFlg;

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public int getDeleteFlg() {
		return this.deleteFlg;
	}
}
