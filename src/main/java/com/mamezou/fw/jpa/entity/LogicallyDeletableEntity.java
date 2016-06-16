package com.mamezou.fw.jpa.entity;

public interface LogicallyDeletableEntity<PK, DF> extends Entity<PK> {
	void setDeleteFlg(DF deleteFlg);
	DF getDeleteFlg();
	void delete();
}
