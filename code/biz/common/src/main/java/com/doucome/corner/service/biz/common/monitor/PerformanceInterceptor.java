package com.doucome.corner.service.biz.common.monitor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;



/**
 * Dragoon监控，基于对接口调用时间的拦截
 * @author wei.gew
 * @version 1.0
 * 
 */
public class PerformanceInterceptor implements MethodInterceptor, InitializingBean {

	private static final Log log = LogFactory.getLog(PerformanceInterceptor.class);
	private int infoValve = 5;
	private int warnValve = 15;
	private int errorValve = 50;
	private int fatalValve = 200;

	public void setInfoValve(int debugValve) {
		this.infoValve = debugValve;
	}

	public void setErrorValve(int errorValve) {
		this.errorValve = errorValve;
	}

	public void setFatalValve(int fatalValve) {
		this.fatalValve = fatalValve;
	}

	public void setWarnValve(int warningValve) {
		this.warnValve = warningValve;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		 long startTime = System.currentTimeMillis();

	        try {
	            return methodInvocation.proceed();
	        } finally {
	            long time = (System.currentTimeMillis() - startTime);
	            String msg = "Call " + methodInvocation.getMethod().getDeclaringClass() + "."
                + methodInvocation.getMethod().getName() + "() take " + time + " ms.";
	            if (time >= fatalValve) {
	                log.fatal(msg);
	            } else if (time >= errorValve) {
	            	log.error(msg);
	            } else if (time >= warnValve) {
	            	log.warn(msg);
	            } else if (time >= infoValve) {
	            	log.info(msg);
	            } else {
	            	log.debug(msg);
	             }
	        }
	}

	@Override
	public void afterPropertiesSet() {
		 if ((fatalValve > errorValve) && (errorValve > warnValve) && (warnValve > infoValve) && (infoValve >= 0)) {
	            log.debug("PerformanceInterceptor setting is ok");
	        } else {
	            throw new IllegalArgumentException("DragoonInterceptor valve condition is "
	                                        + "fatalValve > errorValve && errorValve > warnValve && warnValve > infoValve && infoValve > 0");
	        }

	}

}
