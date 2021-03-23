package com.xxx.service;

import com.xxx.bean.Employee;
import com.xxx.bean.Good;
import com.xxx.bean.VIP;
import com.xxx.dao.impl.EmployeeDaoImpl;
import com.xxx.dao.impl.GoodsDaoImpl;
import com.xxx.dao.impl.VIPDaoImpl;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private VIPDaoImpl vipDao = new VIPDaoImpl();
    private GoodService goodService = new GoodService();
    private Scanner in = new Scanner(System.in);
    private Employee employee;
    private AttendanceService as = new AttendanceService();
    private TurnoverService ts = new TurnoverService();

    public AdminService(Employee e) {
        this.employee = e;
    }

    public void item1(int role) {
        String num;
        String name;
        if (role == 2)
            name = "收银员";
        else
            name = "采购员";
        while (true) {
            System.out.println("\t\t1.查询所有" + name);
            System.out.println("\t\t2.查询一个" + name);
            System.out.println("\t\t3.添加一个" + name);
            System.out.println("\t\t4.修改一个" + name);
            System.out.println("\t\t5.删除一个" + name);
            System.out.println("\t\t0.返回<管理员>界面");
            System.out.println("请选择操作:");
            num = in.next();
            switch (num) {
                case "1":
                    System.out.println("============所有<" + name + ">栏目==============");
                    searchAll(role);
                    break;
                case "2":
                    System.out.println("============查询<" + name + ">栏目==============");
                    System.out.print("请输入<" + name + ">编号:");
                    searchOne(role);
                    break;
                case "3":
                    System.out.println("============添加<" + name + ">栏目==============");
                    addStaff(role);
                    break;
                case "4":
                    System.out.println("============修改<" + name + ">栏目==============");
                    System.out.print("请输入要修改的<" + name + ">编号:");
                    updateStaff(role);
                    break;
                case "5":
                    System.out.println("============删除<" + name + ">栏目==============");
                    System.out.print("请输入要删除<" + name + ">的编号:");
                    String number = in.next();
                    delStaff(number, role);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("操作失败!");
                    break;
            }
        }

    }

    //会员操作
    public void item2() {
        String num;
        List<VIP> vipList;
        while (true) {
            System.out.println("\t\t1.查询所有会员");
            System.out.println("\t\t2.查询一个会员(phone查询)");
            System.out.println("\t\t3.修改一个会员(按账号修改)");
            System.out.println("\t\t4.添加一个会员");
            System.out.println("\t\t5.删除一个会员(按账号修改)");
            System.out.println("\t\t0.返回<管理员>界面");
            num = in.next();
            switch (num) {
                case "1":
                    System.out.println("============所有<会员>栏目>==============");
                    vipList = vipDao.queryAll();
                    if (vipList != null) {
                        System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
                        for (VIP vip : vipList)
                            System.out.println(vip.getVipNumber() + "\t" + vip.getVipName() + "\t" + vip.getVipScore() +
                                    "\t" + vip.getVipPhone() + "\t" + vip.getVipDate());
                    } else
                        System.out.println("暂时还没有会员...");
                    break;
                case "2":
                    System.out.println("============查询<会员>栏目==============");
                    System.out.print("请输入<会员>编号:");
                    String vipNum;
                    vipNum = in.next();
                    VIP vip = vipDao.getVIPByNum(vipNum);
                    if (vip != null) {
                        System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
                        System.out.println(vip.getVipNumber() + "\t" + vip.getVipName() + "\t" + vip.getVipScore() +
                                "\t" + vip.getVipPhone() + "\t" + vip.getVipDate());
                    } else
                        System.out.println("没有该会员...");
                    break;
                case "3":
                    System.out.println("============添加<会员>栏目==============");
                    addVIP();
                    break;
                case "4":
                    System.out.println("============修改<会员>栏目==============");
                    System.out.print("请输入要修改的<会员>编号:");
                    updateVIP();
                    break;
                case "5":
                    System.out.println("============删除<会员>栏目==============");
                    System.out.print("请输入要删除<会员>的编号:");
                    String number = in.next();
                    VIP vip1 = vipDao.getVIPByNum(number);
                    if (vip1 == null) {
                        System.out.println("没有检测到会员数据!");
                        return;
                    } else {
                        System.out.println("检测到一条会员数据:");
                        System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
                        System.out.println(vip1.getVipNumber() + "\t" + vip1.getVipName() + "\t" + vip1.getVipScore() +
                                "\t" + vip1.getVipPhone() + "\t" + vip1.getVipDate());
                        System.out.println("确认删除?y/n:");
                        if (in.next().equals("y")) {
                            if (vipDao.delVIP(number))
                                System.out.println("删除数据成功!");
                            else
                                System.out.println("删除数据失败!");
                        } else {
                            System.out.println("你取消删除该数据操作!");
                        }
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("操作失败!");
                    break;
            }
        }
    }

    //员工出勤管理
    public void item3() {
        String num;
        String employeeNo;
        while (true) {
            System.out.println("============管理员<" + employee.getUsername() + ">进入员工出勤管理栏目==================");
            System.out.println("\t\t1.查询所有员工所有出勤记录");
            System.out.println("\t\t2.查询所有员工某年出勤记录");
            System.out.println("\t\t3.查询所有员工某年某月出勤记录");
            System.out.println("\t\t4.查询所有员工某年某月某天出勤记录");
            System.out.println("\t\t5.查询某个员工所有的出勤记录");
            System.out.println("\t\t6.查询某个员工某年的出勤记录");
            System.out.println("\t\t7.查询某个员工某年某月勤记录");
            System.out.println("\t\t8.查询某个员工某年某月某日出勤记录");
            System.out.println("\t\t9.修改某个员工某年某日的出勤记录出勤记录");
            System.out.println("\t\t0.返回上一层界面");
            System.out.print("请选择操作:");
            num = in.next();
            switch (num) {
                case "1":
                    System.out.println("============<查询所有员工出勤记录栏目>==============");
                    as.queryAll();
                    break;
                case "2":
                    System.out.println("============<查询所有员工某年出勤记录栏目>==============");
                    as.queryAttendanceOfYMD(null,1);
                    break;
                case "3":
                    System.out.println("============<查询所有员工某年某月出勤记录栏目>==============");
                    as.queryAttendanceOfYMD(null,2);
                    break;
                case "4":
                    System.out.println("============<查询所有员工某年某月某天出勤记录栏目>==============");
                    as.queryAttendanceOfYMD(null,3);
                    break;
                case "5":
                    System.out.println("============<查询某个员工所有的出勤记录栏目>==============");
                    System.out.print("输入员工编号:");
                    employeeNo = in.next();
                    as.queryAnEmployeeAll(employeeNo);
                    break;
                case "6":
                    System.out.println("============<查询某个员工某年的出勤记录栏目>==============");
                    System.out.print("输入员工编号:");
                    employeeNo = in.next();
                    as.queryAttendanceOfYMD(employeeNo,1);
                    break;
                case "7":
                    System.out.println("============<查询某个员工某年某月勤记录栏目>==============");
                    System.out.print("输入员工编号:");
                    employeeNo = in.next();
                    as.queryAttendanceOfYMD(employeeNo,2);
                    break;
                case "8":
                    System.out.println("============<查询某个员工某年某月某天勤记录栏目>==============");
                    System.out.print("输入员工编号:");
                    employeeNo = in.next();
                    as.queryAttendanceOfYMD(employeeNo,3);
                    break;
                case "9":
                    System.out.println("============< 修改某个员工某年某日的出勤记录出勤记录栏目>==============");
                    System.out.print("输入员工编号:");
                    employeeNo = in.next();
                    as.updateAnEmployeeOfDay(employeeNo);

                case "0":
                    return;
                default:
                    System.out.println("操作失败!");
                    break;
            }
            System.out.println("----------------------按任意b键返回--------------");
            in.next();
        }
    }

    //查询营业额
    public void item4() {
        String num;
        while (true) {
            System.out.println("============管理员<" + employee.getUsername() + ">查询超市营业额栏目==================");
            System.out.println("\t\t1.天营业额");
            System.out.println("\t\t2.月度营业额");
            System.out.println("\t\t3.季度营业额");
            System.out.println("\t\t4.年营业额");
            System.out.println("\t\t5.查询连续几年营业额:");
            System.out.println("\t\t0.返回上一层界面");
            System.out.print("请选择操作:");
            num = in.next();
            switch (num) {
                case "1":
                    System.out.println("============<查询天营业额栏目>==============");
                    ts.turnoverOfYMD(3);
                    break;
                case "2":
                    System.out.println("============<查询月度营业额栏目>==============");
                    ts.turnoverOfYMD(2);
                    break;
                case "3":
                    System.out.println("============<查季度营业额栏目>==============");
                    ts.turnoverOfQuarter();
                case "4":
                    System.out.println("============<查询年度营业额栏目>==============");
                    ts.turnoverOfYMD(1);
                    break;
                case "5":
                    System.out.println("============<查询连续几年营业额栏目>==============");
                    ts.turnoverYearToYear();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("操作失败!");
                    break;
            }
            System.out.println("----------------------按任意b键返回--------------");
            in.next();
        }
    }


    //商品管理
    public void item5() {
        String num;
        while (true) {
            System.out.println("\t\t1.查询所有商品信息");
            System.out.println("\t\t2.查询某商品信息");
            System.out.println("\t\t3.更新某商品的定价");
            System.out.println("\t\t4.上架某商品");
            System.out.println("\t\t5.下架某商品");
            System.out.println("\t\t0.返回上一层");
            System.out.print("请选择:");
            num = in.next();
            switch (num) {
                case "1":
                    goodService.getAllGoodInfo();
                    break;
                case "2":
                    System.out.print("请输入要查询的商品编号:");
                    goodService.getGoodInfo();
                    break;
                case "3":
                    System.out.print("请输入要更新的商品编号:");
                    goodService.updateGoodPrice();
                    break;
                case "4":
                    goodService.goodsShelves();
                    break;
                case "5":
                    goodService.delGood();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("操作失误!");
                    break;

            }
            System.out.println("-----------按b返回上一层----------------");
            in.next();
        }

    }

    public void mainMenu() {
        String num;

        while (true) {
            System.out.println("============欢迎管理员<" + employee.getUsername() + ">==================");
            System.out.println("\t\t1.收银员账户管理");
            System.out.println("\t\t2.采购员账户管理");
            System.out.println("\t\t3.会员账户管理");
            System.out.println("\t\t4.员工出勤管理");
            System.out.println("\t\t5.查询超市营业额");
            System.out.println("\t\t6.超市商品管理");
            System.out.println("\t\t7.上班打卡");
            System.out.println("\t\t8.下班打卡");
            System.out.println("\t\t0.退出登录");
            System.out.print("请选择操作:");
            num = in.next();
            switch (num) {
                case "1":
                    item1(2);
                    break;
                case "2":
                    item1(3);
                    break;
                case "3":
                    item2();
                    break;
                case "4":
                    item3();
                    break;
                case "5":
                    item4();
                    break;
                case "6":
                    item5();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("操作失误!");
            }
        }

    }


    //添加会员
    private void addVIP() {
        String phone;
        String name;
        int num = 0;
        String str = "0";
        List<VIP> list = vipDao.queryAll();
        boolean flag;
        while (true) {
            flag = true;
            System.out.print("请输入手机号:");
            phone = in.next();
            if (phone.length() < 11) {
                System.out.println("手机号为11位");
                continue;
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(list.size() - 1).getVipNumber().length() > 6) {
                    str = list.get(list.size() - 1).getVipNumber().substring(6);
                    if (num < Integer.parseInt(str))
                        num = Integer.parseInt(str);
                }
                if (phone.equals(list.get(i).getVipPhone())) {

                    flag = false;
                    break;
                }
            }
            if (!flag) {
                System.out.println("手机号重复!1.重新输入 2.退出添加会员");
                if (in.next().equals("1"))
                    continue;
                else
                    break;
            } else {
                System.out.print("请输入会员名:");
                name = in.next();
                str = "vip201" + (num + 1);
                VIP newVip = new VIP();
                Date date = new Date();
                newVip.setVipNumber(str);
                newVip.setVipName(name);
                newVip.setVipPhone(phone);
                newVip.setVipDate(new java.sql.Date(date.getTime()));
                if (vipDao.addVIP(newVip))
                    System.out.println("添加会员成功!");
                else
                    System.out.println("添加会员失败!");
                System.out.print("-----------按b返回上一层----------------");
                in.next();
                break;

            }
        }

    }

    //修改会员
    private void updateVIP() {
        String number; //通过会员编号获取该会员
        String num; //分支操作
        String newPhone; //电话号码
        number = in.next();

        VIP vip = vipDao.getVIPByNum(number);
        if (vip != null) {
            System.out.println("old information:");
            System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
            System.out.println(vip.getVipNumber() + "\t" + vip.getVipName() + "\t" + vip.getVipScore() +
                    "\t" + vip.getVipPhone() + "\t" + vip.getVipDate());
            while (true) {
                System.out.println("1.修改会员名2.修改手机号3.修改积分0.退出修改");
                num = in.next();
                switch (num) {
                    case "1":
                        System.out.print("请输入新的会员名:");
                        vip.setVipName(in.next());
                        break;
                    case "2":
                        System.out.print("请输入新的手机号:");
                        List<VIP> vipList = vipDao.queryAll();
                        do {
                            System.out.println("请输入新的手机号码:(不少于11位:)");
                            newPhone = in.next();
                            if (newPhone.length() < 11) {
                                System.out.println("手机号码少于11位!");
                            } else {
                                boolean flag = true;
                                for (int i = 0; i < vipList.size(); i++) {
                                    if (newPhone.equals(vipList.get(i).getVipPhone())) {
                                        System.out.println("手机号码有重复,请确认手机号码!");
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag) {
                                    vip.setVipPhone(newPhone);
                                    break;
                                }
                            }
                            System.out.print("是否继续修改手机号码?y/n:");
                        } while (in.next().equals("y"));
                    case "3":
                        System.out.print("请输入修改积分:");
                        vip.setVipScore(in.nextInt());
                        break;
                    case "0":
                        return;
                    default:
                        break;
                }
                System.out.print("是否确认修改?y/n:");
                if (in.next().equals("y"))
                    break;
            }
            if (vipDao.updateVIP(vip)) {
                System.out.println("new information:");
                System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
                System.out.println(vip.getVipNumber() + "\t" + vip.getVipName() + "\t" + vip.getVipScore() +
                        "\t" + vip.getVipPhone() + "\t" + vip.getVipDate());
            } else
                System.out.println("更改会员数据失败!");
        } else {
            System.out.println("没有该会员的信息");
        }
    }

    //查询某个职位的所有员工
    private void searchAll(int role) {
        List<Employee> list = employeeDao.getEmployeeInfo(role);
        System.out.println("员工编号\t用户名\t密码\t性别\t手机号码\t职位\t等级");
        for (Employee e : list) {
            System.out.println(e.getNumber() + "\t" + e.getUsername() + "\t" + e.getPassword() + "\t"
                    + e.getSex() + "\t" + e.getPhone() + "\t" + e.getRole() + "\t" + e.getRemark());
        }
    }

    //查询某个职位的某个员工
    private void searchOne(int role) {
        String number = in.next();
        Employee e = employeeDao.getEmployeeInfo(number, role);
        if (e == null)
            System.out.println("没有查询到该职位的员工");
        else {
            System.out.println("员工编号\t用户名\t密码\t\t性别\t\t手机号码\t\t职位\t\t等级");
            System.out.println(e.getNumber() + "\t" + e.getUsername() + "\t\t" + e.getPassword() + "\t"
                    + e.getSex() + "\t" + e.getPhone() + "\t" + e.getRole() + "\t" + e.getRemark());
        }
    }

    //添加某个职位的员工
    private void addStaff(int role) {
        List<Employee> list = employeeDao.queryAll();
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (num < Integer.parseInt(list.get(i).getNumber().substring(1))) {
                num = Integer.parseInt(list.get(i).getNumber().substring(1));
            }
        }
        String name, password, sex, phone;
        boolean flag;
        while (true) {

            flag = true;
            System.out.print("输入员工姓名:");
            name = in.next();
            System.out.print("输入员工密码:");
            password = in.next();
            System.out.print("输入员工性别:");
            sex = in.next();
            System.out.print("输入员工手机号码");
            phone = in.next();
            for (int i = 0; i < list.size(); i++) {
                if (num < Integer.parseInt(list.get(i).getNumber().substring(1))) {
                    num = Integer.parseInt(list.get(i).getNumber().substring(1));
                }
                if (phone.equals(list.get(i).getPhone())) {
                    flag = false;
                    break;
                }
            }
            //flag判断手机号码是否重复
            if (flag) {
                Employee employee = new Employee();
                String number = "S";
                if (String.valueOf(num).length() < 4) {
                    for (int i = 0; i < 4 - String.valueOf(num).length(); i++) {
                        number += "0";
                    }
                }
                number += (num + 1);
                employee.setNumber(number);
                employee.setUsername(name);
                employee.setPassword(password);
                employee.setSex(sex);
                employee.setPhone(phone);
                employee.setRole(role);
                employee.setRemark(1);
                if (employeeDao.addEmployee(employee)) {
                    System.out.println("员工信息添加成功!");
                    System.out.println("员工编号\t用户名\t密码\t性别\t手机号码\t职位\t等级");
                    System.out.println(employee.getNumber() + "\t" + employee.getUsername() + "\t" + employee.getPassword() + "\t"
                            + employee.getSex() + "\t" + employee.getPhone() + "\t" + employee.getRole() + "\t" + employee.getRemark());
                    break;
                } else
                    System.out.println("添加失败!");
            } else {
                System.out.println("手机号码重复,请重新输入!");
                continue;
            }
        }


    }

    //修改某职位的员工
    private void updateStaff(int role) {
        Employee e = null;
        String number = in.next();
        List<Employee> list = employeeDao.getEmployeeInfo(role);
        for (int i = 0; i < list.size(); i++) {
            if (number.equals(list.get(i).getNumber())) {
                System.out.println("检测一条员工信息:");
                e = list.get(i);
                break;
            }
            if (i == list.size() - 1) {
                System.out.println("检测不到该员工信息!");
                return;
            }
        }
        System.out.println("old information:");
        System.out.println("员工编号\t用户名\t密码\t性别\t手机号码\t职位\t等级");
        System.out.println(e.getNumber() + "\t" + e.getUsername() + "\t" + e.getPassword() + "\t"
                + e.getSex() + "\t" + e.getPhone() + "\t\t" + e.getRole() + "\t" + e.getRemark());

        String num;
        String str;
        do {
            System.out.println("\t\t1.修改姓名2.修改密码3.修改手机号码4.修改职位5.修改等级0.退出修改");
            num = in.next();
            if (num.equals("1")) {
                System.out.print("请输入新姓名:");
                str = in.next();
                e.setUsername(str);
            } else if (num.equals("2")) {
                do {
                    System.out.print("请输入新密码(不少于4位):");
                    str = in.next();
                    if (str.length() < 4) {
                        System.out.println("密码长度过短!");
                    } else {
                        e.setPassword(str);
                        break;
                    }
                    System.out.print("是否继续修改密码?y/n:");
                } while (in.next().equals("y"));
                if (str.length() < 4)
                    continue;
            } else if (num.equals("3")) {
                do {
                    System.out.println("请输入新的手机号码:(不少于11位:)");
                    str = in.next();
                    if (str.length() < 11) {
                        System.out.println("手机号码少于11位!");
                    } else {
                        boolean flag = true;
                        for (int i = 0; i < list.size(); i++) {
                            if (str.equals(list.get(i).getPhone())) {
                                System.out.println("手机号码有重复,请确认手机号码!");
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            e.setPhone(str);
                            break;
                        }
                    }
                    System.out.print("是否继续该手机号码?y/n:");
                } while (in.next().equals("y"));
            } else if (num.equals("4")) {
                System.out.println("请输入新的职位(1.管理员2.收银员3.采购员):");
                int newRole = in.nextInt();
                e.setRole(newRole);
            } else if (num.equals("5")) {
                System.out.println("请输入新等级:");
                int remark = in.nextInt();
                e.setRemark(remark);
            } else if (num.equals("0")) {
                System.out.print("是否退出修改,未确认的数据将会失效!y/n:");
                if (in.next().equals("y")) {
                    System.out.println("你退出修改");
                    return;
                } else
                    continue;
            } else {
                System.out.println("操作错误!请重新操作");
                continue;
            }
            System.out.print("是否继续修改?");
        } while (!in.next().equals("y"));
        if (employeeDao.updateEmployee(e)) {
            System.out.println("数据更新成功! new information:");
            System.out.println("员工编号\t用户名\t密码\t性别\t手机号码\t职位\t等级");
            System.out.println(e.getNumber() + "\t" + e.getUsername() + "\t" + e.getPassword() + "\t"
                    + e.getSex() + "\t" + e.getPhone() + "\t" + e.getRole() + "\t" + e.getRemark());

        } else
            System.out.println("更新数据失败!");
    }

    //删除某职位的员工
    private void delStaff(String number, int role) {
        Employee e = employeeDao.getEmployeeInfo(number, role);
        if (e == null) {
            System.out.println("没有检测到员工数据!");
            return;
        } else {
            System.out.println("检测到一条员工数据:");
            System.out.println("员工编号\t用户名\t密码\t性别\t手机号码\t职位\t等级");
            System.out.println(e.getNumber() + "\t" + e.getUsername() + "\t" + e.getPassword() + "\t"
                    + e.getSex() + "\t" + e.getPhone() + "\t" + e.getRole() + "\t" + e.getRemark());
            System.out.println("确认删除?y/n:");
            if (in.next().equals("y")) {
                if (employeeDao.delEmployee(number))
                    System.out.println("删除数据成功!");
                else
                    System.out.println("删除数据失败!");
            } else {
                System.out.println("你取消删除该数据操作!");
            }
        }
    }

}
