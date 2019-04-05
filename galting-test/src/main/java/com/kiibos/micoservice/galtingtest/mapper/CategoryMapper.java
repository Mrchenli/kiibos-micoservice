package com.kiibos.micoservice.galtingtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kiibos.micoservice.galtingtest.domain.Category;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryTreeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    int deleteNode(Long categoryId);

    List<CategoryTreeDto> getChirldrenCatetoryTree(Long categoryId);

}
