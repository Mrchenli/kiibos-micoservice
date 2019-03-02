package com.kiibos.micoservice.kiibos_3_resttemplate.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserDO
 * @Description TODO
 * @Author cl
 * @Date 2019/3/1 下午8:04
 **/
@Data
public class UserDO {

    private Integer id;

    private String name;

    private String phone;

    private String password;

    private Date birthDay;

}
