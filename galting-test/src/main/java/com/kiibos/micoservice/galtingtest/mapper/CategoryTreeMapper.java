package com.kiibos.micoservice.galtingtest.mapper;

import com.kiibos.micoservice.galtingtest.domain.CategoryTree;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kiibos
 * @since 2019-03-21
 */
@Mapper
public interface CategoryTreeMapper extends BaseMapper<CategoryTree> {


    /**
     * 复制父节点的路径结构,并修改descendant和distance
     *
     * @param id 节点id
     * @param parent 父节点id
     */
    Integer insertPath(@Param("id") Long id, @Param("parent") Long parent);
    Integer insertNode(@Param("id") Long id);


    /**
     * 从树中删除某节点的路径。注意指定的节点可能存在子树，而子树的节点在该节点之上的路径并没有改变，
     * 所以使用该方法后还必须手动修改子节点的路径以确保树的正确性
     *
     * @param id 节点id
     */
    Integer deletePathNode(Long id);

}
