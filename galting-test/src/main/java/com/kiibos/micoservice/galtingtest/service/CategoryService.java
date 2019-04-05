package com.kiibos.micoservice.galtingtest.service;

import com.kiibos.micoservice.common.wrapper.Wrapper;
import com.kiibos.micoservice.galtingtest.domain.Category;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryDto;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryListDto;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryTreeDto;
import com.kiibos.micoservice.galtingtestapi.query.CategorySaveQuery;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
public interface CategoryService {

    Wrapper<Long> saveCategory(CategorySaveQuery categorySaveQuery);

    Wrapper<List<CategoryTreeDto>> getChirldrenCatetoryTree(Long categoryId);

    Wrapper<List<CategoryListDto>> listCategory();

    Wrapper<CategoryDto> getCategory(Long categoryId);

    Wrapper<Boolean> deleteCategory(Long categoryId);

}
