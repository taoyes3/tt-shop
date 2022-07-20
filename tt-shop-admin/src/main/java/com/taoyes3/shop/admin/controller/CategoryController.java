package com.taoyes3.shop.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taoyes3.shop.bean.model.Category;
import com.taoyes3.shop.common.exception.ShopBindException;
import com.taoyes3.shop.service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.le(Category::getGrade, 2);
        queryWrapper.orderByAsc(Category::getSeq);

        return ResponseEntity.ok(categoryService.list(queryWrapper));
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Category category) {
        //log.info(category.toString());
        category.setShopId(new Long("1"));
        category.setRecTime(new Date());
        category.setUpdateTime(new Date());

        //类目名称是否存在
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCategoryName, category.getCategoryName());
        queryWrapper.eq(Category::getShopId, category.getShopId());
        Category categoryName = categoryService.getOne(queryWrapper);
        if (categoryName != null) {
            throw new ShopBindException("类目名称已存在！");
        }

        categoryService.save(category);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> update(@RequestBody Category category) {
        category.setShopId(new Long("1"));
        category.setUpdateTime(new Date());

        //类目名称是否存在
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCategoryName, category.getCategoryName());
        queryWrapper.eq(Category::getShopId, category.getShopId());
        Category categoryName = categoryService.getOne(queryWrapper);
        if (categoryName != null) {
            throw new ShopBindException("类目名称已存在！");
        }

        return null;
    }

}
