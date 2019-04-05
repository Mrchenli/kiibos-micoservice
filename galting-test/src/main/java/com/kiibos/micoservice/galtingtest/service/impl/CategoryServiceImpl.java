package com.kiibos.micoservice.galtingtest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kiibos.micoservice.common.exception.BusinessException;
import com.kiibos.micoservice.common.support.TreeUtils;
import com.kiibos.micoservice.common.wrapper.Wrapper;
import com.kiibos.micoservice.galtingtest.mapper.CategoryMapper;
import com.kiibos.micoservice.galtingtest.mapper.CategoryTreeMapper;
import com.kiibos.micoservice.galtingtest.domain.Category;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryDto;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryListDto;
import com.kiibos.micoservice.galtingtestapi.dto.CategoryTreeDto;
import com.kiibos.micoservice.galtingtestapi.query.CategorySaveQuery;
import com.kiibos.micoservice.galtingtest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryTreeMapper categoryTreeMapper;

    /**
     * @Author kiibos
     * @Description 新增目录
     * @Date 下午3:13 2019/3/22
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Long> saveCategory(CategorySaveQuery categorySaveQuery) {
        //1.先存category信息
        Category category = new Category();
        category.setCreator("kiibos");
        category.setCreateTime(LocalDateTime.now());
        category.setCategoryName(categorySaveQuery.getCategoryName());
        Integer num = categoryMapper.insert(category);
        if(num==null||num<=0){
            throw new BusinessException("保存category操作失败");
        }
        //2.存父关系
        Integer pathSaveNum = categoryTreeMapper.insertPath(category.getCategoryId(),categorySaveQuery.getParentCategoryId());
        if(pathSaveNum==null||pathSaveNum<=0){
            throw new BusinessException("insert path 操作失败");
        }
        //3.存自己关系
        Integer nodeSum = categoryTreeMapper.insertNode(category.getCategoryId());
        if(nodeSum==null||nodeSum<=0){
            throw new BusinessException("insert node 操作失败");
        }
        return Wrapper.newSuccess(category.getCategoryId());
    }

    @Override
    public Wrapper<List<CategoryTreeDto>> getChirldrenCatetoryTree(Long categoryId) {
        List<CategoryTreeDto> result = categoryMapper.getChirldrenCatetoryTree(categoryId);
        if(result==null){
            throw new BusinessException("查询目录树失败");
        }
        TreeUtils<CategoryTreeDto,Long> treeUtils = new TreeUtils();
        result = treeUtils.getChildTreeObjects(result,categoryId);
        return Wrapper.newSuccess(result);
    }

    @Override
    public Wrapper<List<CategoryListDto>> listCategory() {
        List<Category> result = categoryMapper.selectList(Wrappers.emptyWrapper());
        if(result!=null){
            return Wrapper.newSuccess(result.stream().map(category->{
                CategoryListDto listDto = new CategoryListDto();
                listDto.setCategoryId(category.getCategoryId());
                listDto.setCategoryName(category.getCategoryName());
                listDto.setCreateTime(category.getCreateTime());
                listDto.setCreator(category.getCreator());
                listDto.setUpdater(category.getUpdater());
                listDto.setUpdateTime(category.getUpdateTime());
                return listDto;
            }).collect(Collectors.toList()));
        }
        return Wrapper.newFailed("查询品类列表信息失败");
    }

    @Override
    public Wrapper<CategoryDto> getCategory(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        if(category!=null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(category.getCategoryId());
            categoryDto.setCategoryName(category.getCategoryName());
            categoryDto.setCreateTime(category.getCreateTime());
            categoryDto.setCreator(category.getCreator());
            categoryDto.setUpdater(category.getUpdater());
            categoryDto.setUpdateTime(category.getUpdateTime());
            return Wrapper.newSuccess(categoryDto);
        }
        return Wrapper.newFailed("查询目录信息失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Wrapper<Boolean> deleteCategory(Long categoryId) {
        //1.delete category
        int cnum = categoryMapper.deleteNode(categoryId);
        if(cnum<=0){
            throw new BusinessException("目录删除操作失败");
        }
        //2.delete relationship
        int tnum = categoryTreeMapper.deletePathNode(categoryId);
        if(tnum<=0){
            throw new BusinessException("目录关系删除操作失败");
        }
        return Wrapper.newSuccess(true);
    }


}
