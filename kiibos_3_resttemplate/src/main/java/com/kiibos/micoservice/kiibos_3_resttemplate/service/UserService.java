package com.kiibos.micoservice.kiibos_3_resttemplate.service;

import com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.query.UserQuery;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.vo.UserVO;

import java.util.List;

public interface UserService {


    /**
     * @Author kiibos
     * @Description //TODO
     * @Date 下午8:41 2019/3/1
     * @param userId
     * @return com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper<com.kiibos.micoservice.kiibos_3_resttemplate.model.vo.UserVO>
     **/
    Wrapper<UserVO> get(Integer userId);

    /**
     * @Author kiibos
     * @Description //TODO
     * @Date 下午8:41 2019/3/1
     * @param userQuery
     * @return com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper<java.util.List<com.kiibos.micoservice.kiibos_3_resttemplate.model.vo.UserVO>>
     **/
    Wrapper<List<UserVO>> list(UserQuery userQuery);

    /**
     * @Author kiibos
     * @Description //TODO
     * @Date 下午8:41 2019/3/1
     * @param userQuery
     * @return com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper<java.lang.Integer>
     **/
    Wrapper<Integer> insert(UserQuery userQuery);

    /**
     * @Author kiibos
     * @Description //TODO
     * @Date 下午8:41 2019/3/1
     * @param userQuery
     * @return com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper
     **/
    Wrapper update(UserQuery userQuery);

    /**
     * @Author kiibos
     * @Description //TODO
     * @Date 下午8:42 2019/3/1
     * @param userQuery
     * @return com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper
     **/
    Wrapper delete(UserQuery userQuery);

}
