package com.sky.service.impl;
import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.vo.DishVO;
import com.github.pagehelper.PageHelper;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;
    /**
     * 新增菜品和口味
     * @param dishDTO
     */
    @Transactional //由于该方法涉及到两个数据库的，因此需要开启事务
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish=new Dish();

        BeanUtils.copyProperties(dishDTO,dish);


        //向菜品表插入数据
        dishMapper.insert(dish);

        //获取Insert生成的主键值
        long dishID=dish.getId();

        //向口味表插入数据
        List<DishFlavor> flavors=dishDTO.getFlavors();
        if(flavors!=null&& flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishID);
            });
            //批量插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     *菜品分页
     * @param dishPageQueryDTO
     * @return
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page=dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 菜品批量删除
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //判断当前菜品是否能够被删除---是否存在起售的商品
       for(Long id :ids){
           Dish dish=dishMapper.getById(id);
           if(dish.getStatus()== StatusConstant.ENABLE){
               //当前菜品在起售，不可删除
               throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
           }

       }


        //判断当前菜品是否能够删除--是否被套餐关联
         List<Long> setmealids=setmealDishMapper.getSetmealIdsByDishIds(ids);
          if(setmealids!=null&& setmealids.size()>0){
              throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
          }
        //删除菜品的数据
        for(Long id:ids){
            dishMapper.deleteById(id);
            //删除菜品关联的口味数据
            dishFlavorMapper.deleteByDishId(id);
        }



    }

    /**
     * 根据id查询菜品和口味
     * @param id
     * @return
     */
    public DishVO getByIdwithFlavor(Long id) {
        //根据id查询菜品数据
        Dish dish=dishMapper.getById(id);

        //根据菜品id查询口味数据
        List<DishFlavor> dishFlavors=dishFlavorMapper.getByDishId(id);

      //将查询到的数据封装到VO
        DishVO dishVO=new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavors);

        return dishVO;
    }

    /**
     * 根据id修改菜品的基本信息和对应的口味信息
     * @param dishDTO
     */
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish=new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

        //修改菜品表基本信息
        dishMapper.update(dish);


        //删除原有得口味数据"
        dishFlavorMapper.deleteByDishId(dishDTO.getId());
        
        //重新插入口味数据
        List<DishFlavor> flavors=dishDTO.getFlavors();
        if(flavors != null && flavors.size()>0){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
                    }
                    
            );
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    public List<Dish> list(Long categoryId) {
        Dish dish=Dish.builder().categoryId(categoryId).status(StatusConstant.ENABLE)
                 .build(); //要求dish是属于该分类且是开启状态
        return dishMapper.list(dish);

    }
}
