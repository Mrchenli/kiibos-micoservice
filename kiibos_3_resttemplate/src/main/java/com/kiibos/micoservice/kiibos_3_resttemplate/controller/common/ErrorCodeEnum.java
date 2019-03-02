package com.kiibos.micoservice.kiibos_3_resttemplate.controller.common;


/**
 * @Author kiibos
 * @Description
 * @Date 下午12:00 2019/3/2
 * @param
 * @return
 **/
public enum ErrorCodeEnum {

	/**
	 * Gl 99990100 error code enum.
	 */
	GL_ILLEGAL_ARG(9999100, "参数异常"),
	/**
	 * Gl 000500 error code enum.
	 */
	GL_UN_KNOw(500, "未知异常"),
	/**
	 * Gl 99990401 error code enum.
	 */
	GL_NON_PERMISSION(99990401, "无访问权限"),
	/**
	 * Gl 99990002 error code enum.
	 */
	GL_TIME_OUT(99990002, "微服务不在线,或者网络超时");


	private int code;
	private String msg;

	/**
	 * Msg string.
	 *
	 * @return the string
	 */
	public String msg() {
		return msg;
	}

	/**
	 * Code int.
	 *
	 * @return the int
	 */
	public int code() {
		return code;
	}

	ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * Gets enum.
	 *
	 * @param code the code
	 *
	 * @return the enum
	 */
	public static ErrorCodeEnum getEnum(int code) {
		for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
			if (ele.code() == code) {
				return ele;
			}
		}
		return null;
	}
}
