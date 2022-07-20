package com.taoyes3.shop.service.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taoyes3.shop.bean.model.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> listByParentId(Long parentId);

}




