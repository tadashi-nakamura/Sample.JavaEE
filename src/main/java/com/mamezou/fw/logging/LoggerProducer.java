package com.mamezou.fw.logging;

import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@Dependent
public class LoggerProducer {
    @Produces
	@Dependent
    public Logger getLogger(InjectionPoint point) {
        return Logger.getLogger(point.getMember().getDeclaringClass().getName());
    }
}
