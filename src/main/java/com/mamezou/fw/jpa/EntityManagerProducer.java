package com.mamezou.fw.jpa;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Dependent
public class EntityManagerProducer {
    /** entity manager */
    @PersistenceContext
    private EntityManager em;

}
