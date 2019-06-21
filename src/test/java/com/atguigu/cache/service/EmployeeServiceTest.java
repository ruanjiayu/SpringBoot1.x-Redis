package com.atguigu.cache.service;

import com.atguigu.cache.Springboot01CacheApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author ruanjiayu
 * @dateTime 2019/6/21 10:04
 */
@Component
@Slf4j
public class EmployeeServiceTest extends Springboot01CacheApplicationTests{

    @Resource
    private EmployeeService employeeService;

    @Test
    public void getEmp() throws Exception {
        log.info("【查询得到职员数据】:{}",employeeService.getEmp(2));
    }

}