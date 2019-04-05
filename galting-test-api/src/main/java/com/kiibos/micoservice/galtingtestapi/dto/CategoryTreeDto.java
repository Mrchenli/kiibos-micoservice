package com.kiibos.micoservice.galtingtestapi.dto;

import com.kiibos.micoservice.common.wrapper.BaseTree;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CategoryTreeDto
 * @Description TODO
 * @Author cl
 * @Date 2019/3/20 上午10:35
 **/
@Data
public class CategoryTreeDto extends BaseTree<CategoryTreeDto,Long> implements Serializable {

   private String categoryName;

}
