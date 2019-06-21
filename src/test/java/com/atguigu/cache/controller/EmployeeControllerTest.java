package com.atguigu.cache.controller;

import com.atguigu.cache.Springboot01CacheApplicationTests;
import com.atguigu.cache.service.EmployeeService;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author ruanjiayu
 * @dateTime 2019/6/21 10:16
 */
@Component
public class EmployeeControllerTest extends Springboot01CacheApplicationTests{

    @Resource
    private EmployeeService employeeService;

    @Test
    public void deleteEmpAll() throws Exception {
        employeeService.clear();
    }

}