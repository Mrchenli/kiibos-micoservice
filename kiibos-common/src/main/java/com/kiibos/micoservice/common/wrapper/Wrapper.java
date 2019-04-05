package com.kiibos.micoservice.common.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName Wrapper
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 上午10:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wrapper<T> {

    private static final int SUCCESS_CODE=1;
    private static final int FAILED_CODE=0;
    private static final String SUCCESS_DESC="操作成功";


    private static final Wrapper SUCCESS = new Wrapper(SUCCESS_CODE,SUCCESS_DESC,null);


    private int code;

    private String msg;

    private T result;

    public static <T> Wrapper<T> newSuccess(T body){
        return new Wrapper<>(SUCCESS_CODE,SUCCESS_DESC,body);
    }

    public static <T> Wrapper<T> newFailed(String msg){
        return new Wrapper<>(FAILED_CODE,msg,null);
    }
}
