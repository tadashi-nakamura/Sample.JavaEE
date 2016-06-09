package com.mamezou.sample.jpa.customizer;

import org.eclipse.persistence.config.SessionCustomizer;
//import org.eclipse.persistence.dynamic.DynamicHelper.SessionCustomizer;
import org.eclipse.persistence.sessions.DatabaseLogin;
import org.eclipse.persistence.sessions.Session;

public class IsolationLevelCustomizer implements SessionCustomizer {
    public void customize(Session session) {
        DatabaseLogin login = (DatabaseLogin) session.getDatasourceLogin();
        // Isolation Level
        login.setTransactionIsolation(DatabaseLogin.TRANSACTION_NONE);
        login.setTransactionIsolation(DatabaseLogin.TRANSACTION_READ_COMMITTED);
        login.setTransactionIsolation(DatabaseLogin.TRANSACTION_READ_UNCOMMITTED);
        login.setTransactionIsolation(DatabaseLogin.TRANSACTION_REPEATABLE_READ);
        login.setTransactionIsolation(DatabaseLogin.TRANSACTION_SERIALIZABLE);
    }
}
