package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(tags="分类相关接口")
@Slf4j

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /**
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")//无请求体
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(@ModelAttribute CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分类分页查询{}",categoryPageQueryDTO);
        PageResult pageResult=categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("根据id删除分类")
    public Result<String> delete(@RequestParam Long id){
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     *
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result<String> startOrStop(@PathVariable("status") Integer status,Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }
    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(Integer type){
        List<Category>list=categoryService.list(type);
        return Result.success(list);
    }

}
