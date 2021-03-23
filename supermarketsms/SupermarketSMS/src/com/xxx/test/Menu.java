package com.xxx.test;


import com.xxx.bean.Employee;
import com.xxx.dao.impl.EmployeeDaoImpl;
import com.xxx.dao.impl.WorkDateDaoImpl;
import com.xxx.service.AdminService;
import com.xxx.service.BuyerService;
import com.xxx.service.CashierService;
import com.xxx.service.Login;
import com.xxx.service.impl.VIPServiceImpl;

import java.util.Scanner;

public class Menu {
    private Employee employee = null;
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private Scanner in = new Scanner(System.in);
    private Login login = new Login();
    private WorkDateDaoImpl w = new WorkDateDaoImpl();


    //添加当前日期
    public void addDate(){
        w.addDate();
    }

    public void show() {
        System.out.println("=============欢迎来到超市收银系统=================");
        System.out.println("\t\t\t1.管理员登录");
        System.out.println("\t\t\t2.收银员登录");
        System.out.println("\t\t\t3.采购员登录");
        System.out.println("\t\t\t4.会员登录");
        System.out.println("\t\t\t5.退出");
        System.out.println("=============================================");
        System.out.print("请选择:");
    }

    public void login() {

        while (true) {
            show();
            String num = in.next();
            if (num.equals("4")) {
                VIPServiceImpl vipService = new VIPServiceImpl();
                vipService.login();
            } else if (num.equals("3")) {
                System.out.println("==========进入采购员登录===========");
                employee = login.login(3);
                if (employee != null) {
                    BuyerService buyerService = new BuyerService(employee);
                    buyerService.menu();
                }
//                getRole(3);
//                buyerService.login();
            } else if (num.equals("2")) {
                System.out.println("==========进入收银员登录===========");
                employee = login.login(2);
                if (employee != null) {
                   CashierService cashierService = new CashierService(employee);
                    cashierService.menu();
                }
            } else if(num.equals("1")){
                System.out.println("==========进入管理员登录============");
                employee = login.login(1);
                if (employee != null) {
                    AdminService adminService = new AdminService(employee);
                    adminService.mainMenu();
                }
            }else
                break;
        }

    }

    public Employee getRole(int role) {
        String number;
        String password;
        int i = 0;
        do {
            System.out.print("请输入员工编号:");
            number = in.next();
            System.out.print("请输入员工密码:");
            password = in.next();
            employee = employeeDao.getEmployee(number, password, role);
            System.out.println(employee);
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
