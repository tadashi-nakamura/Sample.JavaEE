package com.mamezou.fw.jpa.customizer;

import org.eclipse.persistence.config.SessionCustomizer;
//import org.eclipse.persistence.dynamic.DynamicHelper.SessionCustomizer;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

import com.mamezou.fw.logging.CustomJpaLogger;

public class SampleSessionCustomizer implements SessionCustomizer {
	public void customize(Session session) {
		DatabaseLogin login = (DatabaseLogin) session.getDatasourceLogin();
		// Isolation Level
		// login.setTransactionIsolation(DatabaseLogin.TRANSACTION_NONE);
		login.setTransactionIsolation(DatabaseLogin.TRANSACTION_READ_UNCOMMITTED);
		// login.setTransactionIsolation(DatabaseLogin.TRANSACTION_READ_COMMITTED);
		// login.setTransactionIsolation(DatabaseLogin.TRANSACTION_REPEATABLE_READ);
		// login.setTransactionIsolation(DatabaseLogin.TRANSACTION_SERIALIZABLE);
		
		// create a custom logger
		session.setSessionLog(new CustomJpaLogger());
		session.getSessionLog().setLevel(1); // Logging level finest
		
		// set schema
		session.getLogin().setTableQualifier("SCOTT");
	}
}
