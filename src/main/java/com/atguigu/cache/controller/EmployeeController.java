package com.atguigu.cache.controller;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 通过ID来查询
     * @param id
     * @return
     */
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

    /**
     *  更新职员
     * @param employee
     * @return
     */
    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee emp = employeeService.updateEmp(employee);

        return emp;
    }

    /**
     * 删除指定职员的缓存
     * @param id
     * @return
     */
    @GetMapping("/delemp")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    /**
     * 删除cache名字为emp下的所有数据
     * @return
     */
    @GetMapping("/delempAll")
    public String deleteEmpAll(){
        employeeService.clear();
        return "success";
    }

    /**
     * 获取职员通过lastName
     * @param lastName
     * @return
     */
    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
       return employeeService.getEmpByLastName(lastName);
    }

    /**
     * 通过性别来获取职员的序列
     * @param gender
     * @return
     */
    @GetMapping("/emp/gender")
    public List<Employee> listEmployeeByGender(@RequestParam("gender") Integer gender){
        return employeeService.listEmployeeByGender(gender);
    }

}
