package com.kiibos.micoservice.galtingtestapi.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserDto
 * @Description TODO
 * @Author cl
 * @Date 2019/3/22 上午9:33
 **/
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 账户余额
     */
    private Double money;


}
