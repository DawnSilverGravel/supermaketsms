package com.xxx.bean;
/*
*员工实体类
*
*
 */
public class Employee {

    private String  number; //员工编号
    private String username; //员工姓名
    private String password; //员工密码
    private String sex;  //员工性别
    private String phone; //手机号码
    private int role; //员工职位 ，数据库表中是int ，这里使用连接表查询
    private int remark;//员工级别

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRemark() {
        return remark;
    }

    public void setRemark(int remark) {
        this.remark = remark;
    }
}
