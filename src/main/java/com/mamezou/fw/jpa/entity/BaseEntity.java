package com.mamezou.fw.jpa.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author nakamura
 *
 * @param <PK>
 */
@MappedSuperclass
public abstract class BaseEntity<PK, DF> implements LogicallyDeletableEntity<PK, DF> {

	@Column(name = "DELETE_FLG", nullable = false)
	private DF deleteFlg;

	public void setDeleteFlg(DF deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public DF getDeleteFlg() {
		return this.deleteFlg;
	}
}
