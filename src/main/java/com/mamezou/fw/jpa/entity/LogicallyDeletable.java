package com.mamezou.fw.jpa.entity;

public interface LogicallyDeletable<PK, DF> {
	void setDeleteFlg(DF deleteFlg);
	DF getDeleteFlg();
	void delete();
}
