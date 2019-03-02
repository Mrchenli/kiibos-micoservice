package com.kiibos.micoservice.kiibos_3_resttemplate.controller;

import com.kiibos.micoservice.kiibos_3_resttemplate.controller.common.Wrapper;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.query.UserQuery;
import com.kiibos.micoservice.kiibos_3_resttemplate.model.vo.UserVO;
import com.kiibos.micoservice.kiibos_3_resttemplate.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName HelloWorldController
 * @Description
 * @Author cl
 * @Date 2019/3/1 下午6:05
 **/
@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Web - UserController",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(httpMethod = "GET",value = "根据用户id获取用户信息")
    public Wrapper<UserVO> get(@ApiParam(name = "id",value = "用户id")@PathVariable("id") String id){
        return userService.get(Integer.parseInt(id));
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", value = "查询用户列表")
    public Wrapper<List<UserVO>> list(@ApiParam(name = "userQuery", value = "查询用户信息列表") @RequestBody UserQuery userQuery){
        return userService.list(userQuery);
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT", value = "添加用户")
    public Wrapper<Integer> insert(@ApiParam(name = "userQuery",value = "查询用户信息列表")@RequestBody UserQuery userQuery){
        return userService.insert(userQuery);
    }

    @PostMapping("/{id}")
    @ApiOperation(httpMethod = "POST", value = "修改用户信息")
    public Wrapper update(@ApiParam(name = "userQuery",value = "修改用户信息")@RequestBody UserQuery userQuery){
        return userService.update(userQuery);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(httpMethod = "DELETE",value = "删除用户信息")
    public Wrapper delete(@ApiParam(name = "userQuery",value = "修改用户信息")@RequestBody UserQuery userQuery){
        return userService.delete(userQuery);
    }

}
