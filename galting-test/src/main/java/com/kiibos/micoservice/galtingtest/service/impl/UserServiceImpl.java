package com.kiibos.micoservice.galtingtest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kiibos.micoservice.common.wrapper.Wrapper;
import com.kiibos.micoservice.galtingtest.mapper.UserMapper;
import com.kiibos.micoservice.galtingtest.service.UserService;
import com.kiibos.micoservice.galtingtest.domain.User;
import com.kiibos.micoservice.galtingtestapi.dto.UserDto;
import com.kiibos.micoservice.galtingtestapi.dto.UserListDto;
import com.kiibos.micoservice.galtingtestapi.query.UserSaveQuery;
import com.kiibos.micoservice.galtingtestapi.query.UserUpdateByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Wrapper<List<UserListDto>> listUser() {
        List<User> result = list();
        if(result==null){
            return Wrapper.newFailed("查询用户列表失败");
        }
        return Wrapper.newSuccess(
                result
                    .stream()
                    .map(i->{
                        UserListDto userListDtoDto = new UserListDto();
                        userListDtoDto.setId(i.getId());
                        userListDtoDto.setAge(i.getAge());
                        userListDtoDto.setMoney(i.getMoney());
                        userListDtoDto.setName(i.getName());
                        return userListDtoDto;
                    })
                    .collect(Collectors.toList())
        );
    }

    @Override
    public Wrapper<UserDto> getUserById(Long userId) {
        User user = getById(userId);
        if(user==null){
            return Wrapper.newFailed("查询用户信息失败");
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setMoney(user.getMoney());
        userDto.setName(user.getName());
        return Wrapper.newSuccess(userDto);
    }

    @Override
    public Wrapper<Long> saveUser(UserSaveQuery saveQuery) {
        User user = new User();
        user.setAge(saveQuery.getAge());
        user.setName(saveQuery.getName());
        user.setMoney(saveQuery.getMoney());
        if(save(user)){
            return Wrapper.newSuccess(user.getId());
        }
        return Wrapper.newFailed("新增用户操作失败");
    }

    @Override
    public Wrapper<Boolean> updateUserById(UserUpdateByIdQuery updateByIdQuery) {
        User user = new User();
        user.setId(updateByIdQuery.getUserId());
        user.setName(updateByIdQuery.getName());
        user.setMoney(updateByIdQuery.getMoney());
        user.setAge(updateByIdQuery.getAge());
        if(updateById(user)){
            return Wrapper.newSuccess(true);
        }
        return Wrapper.newFailed("更新用户信息失败");
    }

    @Override
    public Wrapper<Boolean> deleteUserById(Long userId) {
        if(removeById(userId)){
            return Wrapper.newSuccess(true);
        }
        return Wrapper.newFailed("删除用户信息失败");
    }


}
