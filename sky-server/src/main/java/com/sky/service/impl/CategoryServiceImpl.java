package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.ContentHandler;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *
     * @param categoryDTO
     */
   public void save(CategoryDTO categoryDTO){
       Category category = new Category();
       BeanUtils.copyProperties(categoryDTO,category);
       category.setStatus(StatusConstant.ENABLE);
       category.setCreateTime(LocalDateTime.now());
       category.setUpdateTime(LocalDateTime.now());
       category.setCreateUser(BaseContext.getCurrentId());
       category.setUpdateUser(BaseContext.getCurrentId());//重复代码
        categoryMapper.insert(category);
    }

    /**
     *
     * @param categoryPageQueryDTO
     * @return
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        Page<Category>page=categoryMapper.page(categoryPageQueryDTO);
        List<Category> pageResult=page.getResult();
        long total = page.getTotal();
        return new PageResult(total,pageResult);


    }

    /**
     *
     * @param id
     */
    public void deleteById(Long id){
        categoryMapper.deleteById(id);

    }

    /**
     *
     * @param categoryDTO
     */
    public void update(CategoryDTO categoryDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    /**
     *
     * @param id
     */
    public void startOrStop(Integer status,Long id){
     Category category =Category.builder().id(id).status(status).build();
       categoryMapper.update(category);
    }

    /**
     *
     * @param type
     */
    public  List<Category> list(Integer type){
        Category category=Category.builder().type(type).build();
         return categoryMapper.list(category);
    }


}
