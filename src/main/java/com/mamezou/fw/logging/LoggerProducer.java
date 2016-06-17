package com.mamezou.fw.logging;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

@Dependent
public class LoggerProducer {
	@Inject
    private InjectionPoint point;

    @Produces
	@Dependent
    public Logger getLogger() {
        String loggerName = point.getMember().getDeclaringClass().getName();
        Logger logger = Logger.getLogger(loggerName);
        return logger;
    }
}
