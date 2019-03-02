package com.kiibos.micoservice.kiibos_3_resttemplate.model.query;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserQuery
 * @Description
 * @Author cl
 * @Date 2019/3/1 下午8:20
 **/
@Data
public class UserQuery {

    private Integer id;

    private String name;

    private String phone;

    private String password;

    private Date birthDay;

}
