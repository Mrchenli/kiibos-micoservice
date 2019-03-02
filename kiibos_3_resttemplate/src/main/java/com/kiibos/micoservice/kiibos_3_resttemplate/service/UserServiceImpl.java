package com.kiibos.micoservice.kiibos_3_resttemplate.service;

import com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.query.UserQuery;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author cl
 * @Date 2019/3/1 下午8:16
 **/
@Service
public class UserServiceImpl implements UserService {


    @Override
    public Wrapper<UserVO> get(Integer userId) {
        UserVO userVO = new UserVO();
        userVO.setBirthDate(new Date());
        userVO.setId(userId);
        userVO.setName("kiibos");
        userVO.setPassword("kiibos");
        userVO.setPhone("110");
        return Wrapper.newSuccess(userVO);
    }

    @Override
    public Wrapper<List<UserVO>> list(UserQuery userQuery) {
        return Wrapper.newSuccess();
    }

    @Override
    public Wrapper<Integer> insert(UserQuery userQuery) {
        return Wrapper.newSuccess();
    }

    @Override
    public Wrapper update(UserQuery userQuery) {
        return Wrapper.newSuccess();
    }

    @Override
    public Wrapper delete(UserQuery userQuery) {
        return Wrapper.newSuccess();
    }
}
