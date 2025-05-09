package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 返回分页查询的页码数
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用或禁用员工
     * @param status
     * @param id
     */
    void startStop(Integer status, long id);

    /**
     * 根据员工ID查询
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 编辑员工
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
