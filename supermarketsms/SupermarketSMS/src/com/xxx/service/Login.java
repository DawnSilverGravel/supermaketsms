package com.xxx.service;

import com.xxx.bean.Employee;
import com.xxx.dao.impl.EmployeeDaoImpl;


import java.util.Scanner;

public class Login {
    private Scanner in = new Scanner(System.in);
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private Employee employee = null;

    public Employee login(int role) {

        String number;
        String password;
        int i = 0;
        do {
            System.out.print("请输入员工编号:");
            number = in.next();
            System.out.print("请输入员工密码:");
            password = in.next();
            employee = employeeDao.getEmployee(number, password, role);
            if (employee != null) {
               return employee;
            } else
                System.out.println("员工不存在或密码不错误！");
            i++;
            if (i == 3) {
                System.out.println("你操作次数超过限制！");
                break;
            }
        } while (true);
        return employee;
    }
}
