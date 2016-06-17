package com.mamezou.fw.logging;

import java.util.logging.Logger;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

public class CustomJpaLogger extends AbstractSessionLog implements SessionLog {
	
	/** logger */
    private static final Logger logger = Logger.getLogger(CustomJpaLogger.class.getName());

	@Override
	public void log(SessionLogEntry sessionLogEntry) {
		logger.info(sessionLogEntry.getMessage());
	}

}
