package com.kiibos.micoservice.kiibos_3_resttemplate.controller.common;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Wrapper
 * @Description TODO
 * @Author cl
 * @Date 2019/3/1 下午8:23
 **/
@Data
@ToString
public class Wrapper<T> {

    private static final Integer SUCCESS_CODE = 1;
    private static final Integer FAILED_CODE = 2;

    private Integer code;
    private String msg;
    private T data;

    private Wrapper(){

    }

    public static <T> Wrapper<T> newSuccess(T data){
        Wrapper<T> wrapper =new Wrapper<>();
        wrapper.code = SUCCESS_CODE;
        wrapper.msg = "success";
        wrapper.data = data;
        return wrapper;
    }

    public static <T> Wrapper<T> newSuccess(){
        Wrapper<T> wrapper =new Wrapper<>();
        wrapper.code = SUCCESS_CODE;
        wrapper.msg = "success";
        wrapper.data = null;
        return wrapper;
    }

    public static <T> Wrapper<T> newFailed(String msg){
        Wrapper<T> wrapper =new Wrapper<>();
        wrapper.code = FAILED_CODE;
        wrapper.msg = msg;
        wrapper.data = null;
        return wrapper;
    }

}
