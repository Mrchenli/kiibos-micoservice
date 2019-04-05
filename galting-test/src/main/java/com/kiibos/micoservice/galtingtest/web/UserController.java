package com.kiibos.micoservice.galtingtest.web;


import com.kiibos.micoservice.common.wrapper.Wrapper;
import com.kiibos.micoservice.galtingtest.service.UserService;
import com.kiibos.micoservice.galtingtestapi.dto.UserDto;
import com.kiibos.micoservice.galtingtestapi.dto.UserListDto;
import com.kiibos.micoservice.galtingtestapi.query.UserSaveQuery;
import com.kiibos.micoservice.galtingtestapi.query.UserUpdateByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Author kiibos
     * @Description //获取所有的用户列表
     * @Date 下午10:00 2019/3/21
     **/
    @GetMapping
    public Wrapper<List<UserListDto>> listUser(){
        return userService.listUser();
    }

    /**
     * @Author kiibos
     * @Description //根据用户id获取用户信息
     * @Date 下午10:00 2019/3/21
     **/
    @GetMapping("/{userId}")
    public Wrapper<UserDto> getUserById(@NotNull(message = "userId不可以为空") @PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    /**
     * @Author kiibos
     * @Description //新增用户
     * @Date 下午10:00 2019/3/21
     **/
    @PutMapping
    public Wrapper<Long> saveUser(@Valid @RequestBody UserSaveQuery saveQuery){
        return userService.saveUser(saveQuery);
    }

    /**
     * @Author kiibos
     * @Description //根据用户id更新用户信息
     * @Date 下午10:01 2019/3/21
     **/
    @PostMapping("/{userId}")
    public Wrapper<Boolean> updateUserById(@NotNull(message = "userId不可以为空")@PathVariable("userId") Long userId,
                                           @Valid@RequestBody UserUpdateByIdQuery updateByIdQuery){
        updateByIdQuery.setUserId(userId);
        return userService.updateUserById(updateByIdQuery);
    }

    /**
     * @Author kiibos
     * @Description //根据用户id删除用户
     * @Date 下午10:01 2019/3/21
     **/
    @DeleteMapping("/{userId}")
    public Wrapper<Boolean> deleteUserById(@NotNull(message = "userId不可以为空")@PathVariable("userId") Long userId){
        return userService.deleteUserById(userId);
    }


}
