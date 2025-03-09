package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    /**
     *
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     *
     * @param id
     */
    void deleteById(Long id);

    /**
     *
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    void startOrStop(Integer status,Long id);

    List<Category> list(Integer type);
}
