package com.kiibos.micoservice.galtingtestapi.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName UserSaveQuery
 * @Description TODO
 * @Author cl
 * @Date 2019/3/21 下午9:49
 **/
@Data
public class UserSaveQuery {

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不可以为null")
    @Max(value = 150,message = "年龄最大为150")
    private Integer age;

    /**
     * 账户余额
     */
    @NotNull(message = "账户余额不能为空")
    private Double money;


}
