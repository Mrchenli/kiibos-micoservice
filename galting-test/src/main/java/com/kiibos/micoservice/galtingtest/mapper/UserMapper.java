package com.kiibos.micoservice.galtingtest.mapper;

import com.kiibos.micoservice.galtingtest.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
