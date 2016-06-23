package com.mamezou.fw.cdi;

import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CdiUtil {
	@SuppressWarnings("unchecked")
	public static final <T> T getBean(Class<T> type) {
		try {
			InitialContext ic = new InitialContext();
			BeanManager bm = (BeanManager) ic.lookup("java:comp/BeanManager");

			Set<Bean<?>> beans = bm.getBeans(type);
			Bean<?> bean = bm.resolve(beans);
			return (T) bm.getReference(bean, type, bm.createCreationalContext(bean));
		} catch (NamingException e) {
			return null;
		}

	}
}
