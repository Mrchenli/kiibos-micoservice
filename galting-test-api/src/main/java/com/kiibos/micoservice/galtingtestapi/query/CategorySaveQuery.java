package com.kiibos.micoservice.galtingtestapi.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName CategorySaveQuery
 * @Description TODO
 * @Author cl
 * @Date 2019/3/21 上午10:22
 **/
@Data
public class CategorySaveQuery {


    @NotNull(message = "父品类id不能为空")
    private Long parentCategoryId;

    @NotBlank(message = "品类名称不能为空")
    private String categoryName;

}
