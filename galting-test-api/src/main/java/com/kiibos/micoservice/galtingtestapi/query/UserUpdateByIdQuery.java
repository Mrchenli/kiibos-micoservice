package com.kiibos.micoservice.galtingtestapi.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;

/**
 * @ClassName UserUpdateByIdQuery
 * @Description
 * @Author cl
 * @Date 2019/3/21 下午9:56
 **/
@Data
public class UserUpdateByIdQuery {

    /**
     *  用户id
     **/
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    @Max(value = 150,message = "年龄必须在0-150之间")
    private Integer age;

    /**
     * 账户余额
     */
    private Double money;

}
