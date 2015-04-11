package com.felix.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceProxy  {
	private  final Log logger = LogFactory
			.getLog(ServiceProxy.class);

	private final String[] springConfigFiles = {"classpath:applicationContext.xml" };

	private ClassPathXmlApplicationContext applicationContext;

	private boolean started;

	private ServiceProxy() {
		start();
	}

	private static volatile ServiceProxy _instance = null;

	public static final ServiceProxy getInstance() {
		if (null == _instance) {
			_instance = new ServiceProxy();
		}
		return _instance;
	}

	private void init() throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("init " + Arrays.toString(springConfigFiles) + " ...");
		}
		try {
			this.applicationContext = new ClassPathXmlApplicationContext(
					springConfigFiles);
		} catch (Throwable e) {
			logger.error(
					"ServiceRegistry:Error to init Spring's bean.System exit.",
					e);
			System.exit(1);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("init " + springConfigFiles + " ... DONE: "
					+ getServicesCount());
		}
	}

	public  <T> T getService(String serviceName)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getService " + serviceName + " ...");
		}
		if (applicationContext == null) {
			if (logger.isErrorEnabled()) {
				logger.error("applicationContext is not init successfully.");
			}
			return null;
		}
		Object serviceBean = applicationContext.getBean(serviceName);
		
		return (T)serviceBean;
	}

	public int getServicesCount() {
		if (applicationContext == null) {
			if (logger.isErrorEnabled()) {
				logger.error("applicationContext is not init successfully.");
			}
			return 0;
		}
		return applicationContext.getBeanDefinitionCount();
	}

	public String[] getServiceNames() {
		if (applicationContext == null) {
			if (logger.isErrorEnabled()) {
				logger.error("applicationContext is not init successfully.");
			}
			return null;
		}
		return applicationContext.getBeanDefinitionNames();
	}

	/**
	 * NOTE: package access
	 */
	public synchronized void start() {
		if (started) {
			return;
		}
		logger.error("Starting ... ");
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ServiceRegistry start error.: ", e);
		}
		started = true;
		logger.error("Starting ... DONE");
	}

	/**
	 * NOTE: package access
	 */
	public void stop() {
		logger.debug("Stopping ... ");
		logger.debug("Stopping ... DONE");
	}

	public String[] getServiceNamesByType(Class<?> clazz) {
		if (applicationContext == null) {
			if (logger.isErrorEnabled()) {
				logger.error("applicationContext is not init successfully.");
			}
			return null;
		}
		return applicationContext.getBeanNamesForType(clazz);
	}

	public Map<String, Object> getServicesByType(Class<?> clazz)
			 {
		if (applicationContext == null) {
			if (logger.isErrorEnabled()) {
				logger.error("applicationContext is not init successfully.");
			}
			return null;
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> services = (Map<String, Object>) applicationContext.getBeansOfType(clazz);
		if ((null == services) || (services.isEmpty())) {
			
			logger.error("no Service find");
		}
		return services;
	}
}