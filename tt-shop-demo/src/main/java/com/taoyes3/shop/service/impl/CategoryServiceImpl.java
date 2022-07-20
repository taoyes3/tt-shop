package com.taoyes3.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taoyes3.shop.model.Category;
import com.taoyes3.shop.service.CategoryService;
import com.taoyes3.shop.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author tt
* @description 针对表【tz_category(产品类目)】的数据库操作Service实现
* @createDate 2022-07-16 20:23:21
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




