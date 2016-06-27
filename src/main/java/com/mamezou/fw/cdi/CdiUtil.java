package com.mamezou.fw.cdi;

import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CdiUtil {
     /** JNDI名: BeanManager */
     private static final String JNDI_BEAN_MANAGER = "java:comp/BeanManager";

     /**
      * 型を指定してCDI Beanを取得する．
      * 例外が発生した場合は{@code null}を返す．
      * 
      * @param type 型
      * @return CDI Bean
      */
     public static final <T> T getBean(Class<T> type) {
          try {
               BeanManager bm = (BeanManager) new InitialContext().lookup(JNDI_BEAN_MANAGER);
               return getBean(bm, type);
          } catch (NamingException e) {
               return null;
          }
     }
     
     /**
      * {@linkplain BeanManager}に対して型を指定してCDI Beanを取得する．
      * 
      * @param bm {@linkplain BeanManager}
      * @param type 型
      * @return CDI Bean
      */
     @SuppressWarnings("unchecked")
     public static final <T> T getBean(BeanManager bm, Class<T> type) {
          Set<Bean<?>> beans = bm.getBeans(type);
          Bean<?> bean = bm.resolve(beans);
          return (T) bm.getReference(bean, type, bm.createCreationalContext(bean));
     }
}
