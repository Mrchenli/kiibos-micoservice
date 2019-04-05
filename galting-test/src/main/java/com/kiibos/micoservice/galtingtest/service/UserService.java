package com.kiibos.micoservice.galtingtest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kiibos.micoservice.common.wrapper.Wrapper;
import com.kiibos.micoservice.galtingtest.domain.User;
import com.kiibos.micoservice.galtingtestapi.dto.UserDto;
import com.kiibos.micoservice.galtingtestapi.dto.UserListDto;
import com.kiibos.micoservice.galtingtestapi.query.UserSaveQuery;
import com.kiibos.micoservice.galtingtestapi.query.UserUpdateByIdQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
public interface UserService extends IService<User> {

    Wrapper<List<UserListDto>> listUser();

    Wrapper<UserDto> getUserById(Long userId);

    Wrapper<Long> saveUser(UserSaveQuery saveQuery);

    Wrapper<Boolean> updateUserById(UserUpdateByIdQuery updateByIdQuery);

    Wrapper<Boolean> deleteUserById(Long userId);

}
