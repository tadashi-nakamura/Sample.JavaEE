package com.mamezou.fw.jpa.entity;

public interface LogicallyDeletableEntity<PK> extends Entity<PK> {
	void setDeleteFlg(int deleteFlg);
	int getDeleteFlg();
}
