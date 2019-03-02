package com.kiibos.micoservice.kiibos_3_resttemplate.controller.common;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
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
/**
 * @Author kiibos
 * @Description //TODO
 * @Date 下午12:01 2019/3/2
 * @param
 * @return
 **/
@Aspect
@Component
public class ControllerLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final int MAX_LOG_SIZE = 500;

	@Pointcut("execution(public * com.kiibos.micoservice.kiibos_3_resttemplate.controller.*.*(..))")
	public void webLog() {

	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURI();
		String typeName = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String argsStr = JSONObject.toJSONString(joinPoint.getArgs());
		if (StringUtils.isNotBlank(argsStr) && argsStr.length() > MAX_LOG_SIZE) {
			argsStr = argsStr.substring(0, MAX_LOG_SIZE);
		}
		logger.info("请求参数==>{}",new RequestLog(url,typeName+"."+methodName,argsStr));
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) {
		logger.info("放回结果:"+new ResponseLog<>(ret));
	}


	@AllArgsConstructor
	@Data
	public static class RequestLog {

		private String url;

		private String method;

		private String args;

		@Override
		public String toString() {
			return JSONObject.toJSONString(this);
		}
	}


	@Data
	@AllArgsConstructor
	public static class ResponseLog<T> {

		private T response;

		@Override
		public String toString() {
			return JSONObject.toJSONString(this);
		}

	}


}
