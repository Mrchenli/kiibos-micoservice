package com.kiibos.micoservice.galtingtest.web.aspect;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	//ThreadLocal<Long> startTime = new ThreadLocal<>();

	private static final int MAX_LOG_SIZE = 500;

	@Pointcut("execution(public * com.kiibos.micoservice.galtingtest.web.*.*(..))")
	public void webLog() {

	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (null == attributes) return;
		HttpServletRequest request = attributes.getRequest();
		logger.info("请求地址:" + request.getRequestURI());
		logger.info("请求方法:" + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		String argsStr = JSONObject.toJSONString(joinPoint.getArgs());
		if (StringUtils.isNotEmpty(argsStr) && argsStr.length() > MAX_LOG_SIZE) {
			argsStr = argsStr.substring(0, MAX_LOG_SIZE);
		}
		logger.info("请求参数:" + argsStr);
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) {
		logger.info("返回结果:" + JSONObject.toJSONString(ret));
	}

}
