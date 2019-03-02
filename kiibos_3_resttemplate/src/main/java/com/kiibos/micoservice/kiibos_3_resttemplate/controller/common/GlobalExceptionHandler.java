package com.kiibos.micoservice.kiibos_3_resttemplate.controller.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author kiibos
 * @Description //TODO
 * @Date 下午12:00 2019/3/2
 * @param
 * @return
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 参数非法异常.
	 * 参数校验的时候抛出的异常
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Wrapper illegalArgumentException(IllegalArgumentException e) {
		log.error("参数非法异常={}", e.getMessage(), e);
		return Wrapper.newFailed(ErrorCodeEnum.GL_ILLEGAL_ARG.code(),e.getMessage());
	}

	/**
	 * 业务异常.
	 * 这个是service层抛出的异常
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Wrapper businessException(BusinessException e) {
		log.error("业务异常={}", e.getMessage(), e);
		return Wrapper.newFailed(e.getCode()==0?ErrorCodeEnum.GL_UN_KNOw.code():e.getCode(),e.getMessage());
	}



	/**
	 * 全局异常.
	 * 这个程序里面抛出的异常(上面未处理的)
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Wrapper exception(Exception e) {
		log.info("保存全局异常信息 ex={}", e.getMessage(), e);
		return Wrapper.newFailed(ErrorCodeEnum.GL_UN_KNOw.code(),ErrorCodeEnum.GL_UN_KNOw.msg());
	}
}
