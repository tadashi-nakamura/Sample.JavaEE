package com.mamezou.fw.jpa.eventAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;

public class CustomSessionEventAdapter extends SessionEventAdapter {
	private static final Logger logger = Logger.getLogger(CustomSessionEventAdapter.class.getName());

	@Override
	public void postAcquireConnection(SessionEvent event) {
		logger.fine("postAcquireConnection start");
		try {
			Connection con = ((DatabaseAccessor) event.getResult()).getConnection();
			// con.setClientInfo("ApplicationName", (String)
			// sessionMap.get("ApplicationName"));
			// con.setClientInfo("ClientUser", request.getRemoteUser());
			// con.setClientInfo("ClientHostname", request.getRemoteAddr());
			con.setClientInfo("", "");
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		logger.fine("postAcquireConnection end");
	}

	@Override
	public void preReleaseConnection(SessionEvent event) {
		Logger logger = Logger.getLogger(this.getClass().getName());
		logger.fine("preReleaseConnection start");
		try {
			java.sql.Connection con = ((DatabaseAccessor) event.getResult()).getConnection();
			// con.setClientInfo("ApplicationName", null);
			// con.setClientInfo("ClientUser", null);
			// con.setClientInfo("ClientHostname", null);
			con.setClientInfo("", "");
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		logger.fine("preReleaseConnection end");
	}
}
