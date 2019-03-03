package com.kiibos.micoservice.kiibos_3_resttemplate.model.query;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @ClassName UserQuery
 * @Description
 * @Author cl
 * @Date 2019/3/1 下午8:20
 **/
@Data
public class UserQuery {

    @Pattern(regexp="^[0-9]*$",message="年龄不正确")
    private Integer id;

    private String name;

    private String phone;

    private String password;

    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$",message="日期格式不正确")
    private Date birthDay;

}
