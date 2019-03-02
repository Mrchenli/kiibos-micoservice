package com.kiibos.micoservice.kiibos_3_resttemplate.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserVO
 * @Description userVO
 * @Author cl
 * @Date 2019/3/1 下午8:10
 **/
@Data
public class UserVO {
    private Integer id;
    private String name;
    private String password;
    private String phone;
    private Date birthDate;
}
