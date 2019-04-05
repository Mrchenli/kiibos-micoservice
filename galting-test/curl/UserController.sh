#!/usr/bin/env bash

#domain="127.0.0.1"
#port=8080

##获取所有的用户列表
curl -v http://localhost:8080/user

##根据用户id获取用户信息
curl -v http://localhost:8080/user/5

##添加用户 put
curl -v -H "Content-type: application/json" -X PUT -d '{"name":"kiibos","age":26,"money":100.00}' http://localhost:8080/user

##修改用户
curl -v -H "Content-type: application/json" -X POST -d '{"name":"lithos"}' http://localhost:8080/user/9

##删除用户
curl -v -X DELETE http://localhost:8080/user/8