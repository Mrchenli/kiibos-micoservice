package com.kiibos.micoservice.galtingtestapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName CategoryDto
 * @Description TODO
 * @Author cl
 * @Date 2019/3/22 上午9:42
 **/
@Data
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String creator;

    private String updater;

    private String categoryName;

    private LocalDateTime updateTime;

}
