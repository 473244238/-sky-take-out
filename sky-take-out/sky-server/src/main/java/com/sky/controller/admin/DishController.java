package com.sky.controller.admin;


import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags="菜品相关接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();

    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询:{}",dishPageQueryDTO);
        PageResult pageResult=dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);

    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */

    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result Delete(@RequestParam List<Long> ids){
        log.info("菜品批量删除：{}",ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     *根据id查询菜名
     * @param id
     * @return
     */
   @GetMapping("/{id}")
   @ApiOperation("根据Id查询菜名")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据id查询菜名：{}",id);
        DishVO dishVO=dishService.getByIdwithFlavor(id);
        return Result.success(dishVO);
    }
    @PutMapping  //PutMapping :修改菜品   PostMapping:新增菜品  GetMapping:查询菜品 DeleteMapping:删除菜品
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO){
       log.info("修改菜品：{}",dishDTO);
       dishService.updateWithFlavor(dishDTO);
       return Result.success();

    }

    /**
     * 根据分类（category)的id来查询dish中的菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId){
       List<Dish> list=dishService.list(categoryId);
       return Result.success(list);
    }

}
