package com.mamezou.sample.domainService;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.eclipse.persistence.sessions.DatabaseLogin;

import com.mamezou.sample.jpa.dao.ItemDao;
import com.mamezou.sample.jpa.entity.Item;

public class ItemService {
	@Inject
	private ItemDao dao;
	
	@PersistenceContext
	private EntityManager mgr;
	
	public void setIsolationLevelWithConnection() throws SQLException {
		EntityTransaction tx = mgr.getTransaction();
		tx.begin();

		java.sql.Connection connection = mgr.unwrap(java.sql.Connection.class);
		connection.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);
		System.out.println("Connection: "+connection.getTransactionIsolation());
		//prints TRANSACTION_READ_COMMITED as expected

		org.eclipse.persistence.sessions.DatabaseLogin databaseLogin = new DatabaseLogin();
		System.out.println("DatabaseLoging: "+databaseLogin.getTransactionIsolation());
	}
	
	public void setIsolationLevelWithDbLogin() throws SQLException {
		EntityTransaction tx = mgr.getTransaction();
		tx.begin();

		org.eclipse.persistence.sessions.DatabaseLogin databaseLogin = new DatabaseLogin();
		databaseLogin.setTransactionIsolation(DatabaseLogin.TRANSACTION_READ_COMMITTED);
		System.out.println("DatabaseLoging: "+databaseLogin.getTransactionIsolation());
		//prints TRANSACTION_READ_COMMITED as expected

		java.sql.Connection connection = mgr.unwrap(java.sql.Connection.class);
		System.out.println("Connection: "+connection.getTransactionIsolation());
		//prints TRANSACTION_REPEATABLE_READ		
	}

	public Item find(Long pk) {
		return dao.findByPk(pk);
	}

	public List<Item> search(String value) {
		CriteriaBuilder cb = mgr.getCriteriaBuilder();
		System.out.println(cb);
		return dao.findAll();
	}
}
