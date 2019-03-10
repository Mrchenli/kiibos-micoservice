package com.kiibos.micoservice.mysqlinterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlInterviewApplication {

    /**
     * @Author kiibos
     * @Description
     *
     * 用户表：account
     * 用户ID  用户名  用户状态
     * 8      kiibos   1
     * 9       lxl      1
     * 10       lithos      1
     * 11      zz      1
     * 12       zz      1
     * 13       lxl      0
     * 14      lithos      1
     *
     * 用户收入支出表 record
     * id userId type  amount
     * 1   8     CREDIT  100
     * 2   8     DEBIT  200
     * 3   9     DEBIT  10
     * 4   8     CREDIT  20
     *
     * QUESTION:
     *  1.查询出 所有用户的收入 和支出 没有用0补齐
     *   格式
     *      userId  userName  credit debit
     *  2.查询出所有无效用户(status=0) 和非活跃用户(有效可是没有支出搜入)
     *
     * 3.查出所有的重复用户
     *
     * 4.对于status 建立索引是否有意义  假设10万条数据有10条是status=0;
     *    请写出创建索引的语句
     *
     * 5.无限递归树结构怎么设计？
     *
     * @Date 上午11:56 2019/3/10
     * @param
     * @return
     **/


    public static void main(String[] args) {
        SpringApplication.run(MysqlInterviewApplication.class, args);
    }

}
