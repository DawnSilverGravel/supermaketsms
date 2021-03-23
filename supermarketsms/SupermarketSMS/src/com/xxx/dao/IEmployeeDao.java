package com.xxx.dao;

import com.xxx.bean.Employee;

import java.util.List;

public interface IEmployeeDao {
    //查询所有员工
    public List<Employee> queryAll();
    //获取一个员工信息,通过编号和密码
    public Employee getEmployee(String number, String password,int role);
    //查询一个员工信息
    public Employee getEmployeeInfo(String number,int role);
    public List<Employee> getEmployeeInfo(int role);
    //添加一个员工信息
    public boolean addEmployee(Employee Employee);
    //修改员工的信息
    public boolean updateEmployee(Employee Employee);
    //删除员工的信息
    public boolean delEmployee(String number);
}
