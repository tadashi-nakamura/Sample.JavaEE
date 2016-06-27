package com.mamezou.fw.jpa;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class MultipleEntityManagerProducer {
	@PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
	
	
	@Produces
    protected EntityManager createEntityManager(InjectionPoint injectionPoint) {
//        CustomQualifier customQualifier = injectionPoint.getAnnotated().getAnnotation(CustomQualifier.class);
//        return selectEntityManager(customQualifier); //selects firstEntityManager or secondEntityManager based on the details provided by CustomQualifier
		return null;
    }
}
