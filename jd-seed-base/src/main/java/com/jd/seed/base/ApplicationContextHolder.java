package com.jd.seed.base;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * spring容器上下文
 * 
 * </pre>
 * 
 * @author mecarlen.wang 2018年4月24日 下午2:57:34
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;

	}

	/**
	 * <pre>
	 * 通过类型取实例
	 * 
	 * </pre>
	 * 
	 * @param clazz
	 *            Class<T>
	 * @return T <T>
	 */
	public static <T> T getBean(Class<T> clazz) {
		assertContextInjected();
		return applicationContext.getBean(clazz);
	}

	/**
	 * <pre>
	 * 根据bean名称取实例
	 * 
	 * </pre>
	 * 
	 * @param beanName
	 *            String
	 * @return T <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * <pre>
	 * 校验applicationContext不能为空
	 * 
	 * </pre>
	 */
	public static void assertContextInjected() {
		Validate.validState(null != applicationContext, "ApplicationContextHolder.applicationContext is null");
	}

}
