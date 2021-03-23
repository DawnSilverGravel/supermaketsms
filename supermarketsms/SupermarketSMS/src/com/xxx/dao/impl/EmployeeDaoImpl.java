package com.xxx.dao.impl;

import com.xxx.bean.Employee;

import com.xxx.dao.IEmployeeDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl extends DBUtil<Employee> implements IEmployeeDao {
    @Override
    public List<Employee> queryAll() {
        return query("select * from employee");
    }

    @Override
    public Employee getEmployee(String number,String password,int role) {
        List<Employee> list = query("select * from employee where number=? and password=? and role=?",number,password,role);
        if(list.size()>0)
          return list.get(0);
        return null;
    }

    @Override
    public Employee getEmployeeInfo(String number, int role) {
         List<Employee> list = query("select * from employee where number=? and role=?",number,role);
         if(list.size()>0)
             return list.get(0);
        return null;
    }

    @Override
    public List<Employee> getEmployeeInfo(int role) {
        return query("select * from employee where role=?",role);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return update("insert into employee values(?,?,?,?,?,?,?)",employee.getNumber(),
                employee.getUsername(),employee.getPassword(),employee.getSex(),employee.getPhone(),
                employee.getRole(),employee.getRemark());
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return update("update employee set username=?,password=?,sex=?,phone=?,role=?,remark=? where number=?)",
                employee.getUsername(),employee.getPassword(),employee.getSex(),employee.getPhone(),
                employee.getRole(),employee.getRemark(),employee.getNumber());
    }

    @Override
    public boolean delEmployee(String number) {
        return update("delete from employee where number=?",number);
    }

    @Override
    public Employee getEntity(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setNumber(rs.getString(1));
        employee.setUsername(rs.getString(2));
        employee.setPassword(rs.getString(3));
        employee.setSex(rs.getString(4));
        employee.setPhone(rs.getString(5));
        employee.setRole(rs.getInt(6));
        employee.setRemark(rs.getInt(7));
        return employee;
    }
}
