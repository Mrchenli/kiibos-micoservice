package com.kiibos.micoservice.galtingtest.web;


import com.kiibos.micoservice.common.wrapper.Wrapper;
import com.kiibos.micoservice.galtingtest.service.CategoryService;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryDto;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryListDto;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryTreeDto;
import com.kiibos.micoservice.galtingtestapi.query.CategorySaveQuery;
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
@RequestMapping(value = "/category",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @Author kiibos
     * @Description //新增类别
     * @Date 上午9:41 2019/3/22
     **/
    @PutMapping
    public Wrapper<Long> saveCategory(@Valid @RequestBody CategorySaveQuery categorySaveQuery){
        return categoryService.saveCategory(categorySaveQuery);
    }

    /**
     * @Author kiibos
     * @Description //获取节点的子类别树
     * @Date 下午8:47 2019/3/22
     * @param [categoryId]
     * @return com.kiibos.micoservice.common.wrapper.Wrapper<java.util.List<com.kiibos.micoservice.galtingtestapi.dto.CategoryTreeDto>>
     **/
    @GetMapping("/{categoryId}/tree")
    public Wrapper<List<CategoryTreeDto>> getChildCategoryTree(
            @NotNull(message = "categoryId不可以为空") @PathVariable("categoryId") Long categoryId){
        return categoryService.getChirldrenCatetoryTree(categoryId);
    }

    /**
     * @Author kiibos
     * @Description //获取所有的目录列表
     * @Date 下午8:48 2019/3/22
     * @param []
     * @return com.kiibos.micoservice.common.wrapper.Wrapper<java.util.List<com.kiibos.micoservice.galtingtestapi.dto.CategoryListDto>>
     **/
    @GetMapping
    public Wrapper<List<CategoryListDto>> listCategory(){
        return categoryService.listCategory();
    }

    /**
     * @Author kiibos
     * @Description //更加id获取类别信息
     * @Date 下午8:48 2019/3/22
     * @param [categoryId]
     * @return com.kiibos.micoservice.common.wrapper.Wrapper<com.kiibos.micoservice.galtingtestapi.dto.CategoryDto>
     **/
    @GetMapping("/{categoryId}")
    public Wrapper<CategoryDto> getCategory(@NotNull(message = "categoryId不能为空")@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategory(categoryId);
    }
    /**
     * @Author kiibos
     * @Description //删除类别及其子类别
     * @Date 下午8:48 2019/3/22
     * @param [categoryId]
     * @return com.kiibos.micoservice.common.wrapper.Wrapper<java.lang.Boolean>
     **/
    @DeleteMapping("/{categoryId}")
    public Wrapper<Boolean> deleteCategory(@NotNull(message = "categoryId不能为空")@PathVariable("categoryId") Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }


}
