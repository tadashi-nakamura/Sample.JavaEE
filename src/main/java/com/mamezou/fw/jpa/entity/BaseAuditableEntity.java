package com.mamezou.fw.jpa.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.mamezou.fw.jpa.listener.AuditListener;

/**
 * オーディット可能エンティティ型のベースクラス．
 *
 * @param <PK> 主キー型
 * @param <U> ユーザ型
 * @param <TS> タイムスタンプ型
 */
@MappedSuperclass
@EntityListeners({AuditListener.class})
public abstract class BaseAuditableEntity<PK, DF, U, TS> extends BaseEntity<PK, DF> {
	
	@Column(nullable=false)
	private TS createDatetime;
	
	@Column(nullable=false)
	private U createUser;
	
	@Column(nullable=false)
	private TS lastUpdateDatetime;
	
	@Column(nullable=false)
	private U lastUpdateUser;

}
