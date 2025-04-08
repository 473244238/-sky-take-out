package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

public interface SetmealService {


    /**
     * 新增套餐
     * @param setmealDTO
     */
    void savewithDish(SetmealDTO setmealDTO);

    /**
     * 实现分页查询
     * @param setmealPageQurtyDto
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQurtyDto);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和关联的菜品数据
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 对套餐进行起售或停售
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
