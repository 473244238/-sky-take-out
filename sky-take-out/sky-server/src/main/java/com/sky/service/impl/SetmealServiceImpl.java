package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper; //套餐管理

    @Autowired
    private SetmealDishMapper setmealDishMapper; //套餐与菜品的关联管理

    @Autowired
    private DishMapper dishMapper; //菜品管理


    /**
     * 新增套餐 同时这里需要用到套餐和菜品之间的关联关系 用到两个库的处理需要开启事务
     * @param setmealDTO
     */
    @Transactional
    public void savewithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal=new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);

        //向套餐表插入数据
        setmealMapper.insert(setmeal);

        //获取生成的套餐id
        Long setmealId=setmeal.getId();

        List<SetmealDish> setmealDishes=setmealDTO.getSetmealDishes();

        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });

        //保存套餐和菜品的关联关系
        setmealDishMapper.insertBatch(setmealDishes);

    }

    /**
     * 分页查询
     * @param setmealPageQurtyDto
     * @return
     */
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQurtyDto) {
        int pageNum=setmealPageQurtyDto.getPage();
        int pageSize=setmealPageQurtyDto.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        Page<SetmealVO> page=setmealMapper.pageQuery(setmealPageQurtyDto);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 批量删除套餐
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id->{
            Setmeal setmeal=setmealMapper.getById(id);
            if(StatusConstant.ENABLE==setmeal.getStatus()){
                //起售的套餐不能删除
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        ids.forEach(setmealId->{
            //删除套餐表中的数据
            setmealMapper.deleteById(setmealId);
            //删除套餐菜品关系表中的数据
            setmealDishMapper.deleteBySetmealId(setmealId);
        });
    }


    /**
     * 根据id查询套餐和套餐菜品关系
     * @param id
     * @return
     */
    public SetmealVO getByIdWithDish(Long id) {
        Setmeal setmeal=setmealMapper.getById(id);
        List<SetmealDish> setmealDishes=setmealDishMapper.getBySetmealId(id);
        SetmealVO setmealVO=new SetmealVO();
        BeanUtils.copyProperties(setmeal,setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    /**
     * 修改套餐
     * @param setmealDTO
     */
    @Transactional
    public void update(SetmealDTO setmealDTO){
      Setmeal setmeal=new Setmeal();
      BeanUtils.copyProperties(setmealDTO,setmeal);

      //1.修改套餐表，执行update
        setmealMapper.update(setmeal);

        //套餐id
        Long setmealId=setmealDTO.getId();

        //2.删除套餐和菜品的关联关系
        setmealDishMapper.deleteBySetmealId(setmealId);

        List<SetmealDish> setmealDishes=setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });

        //3.重新插入套餐和菜品的关联关系
        setmealDishMapper.insertBatch(setmealDishes);

    }

    /**
     * 对套餐进行停售或起售
     * @param status
     * @param id
     */
    public void startOrStop(Integer status, Long id) {
        //起售套餐时，判断套餐是否有停售菜品，如果有，则无法起售
        if(status==StatusConstant.ENABLE){
            List<Dish> dishList=dishMapper.getBySetmealId(id);
            if(dishList!=null&& dishList.size()>0){
                dishList.forEach(dish->{
                    if(StatusConstant.DISABLE==dish.getStatus()){
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }

        }
        Setmeal setmeal=Setmeal.builder().id(id)
                                         .status(status)
                                         .build();
        setmealMapper.update(setmeal);
    }
}
