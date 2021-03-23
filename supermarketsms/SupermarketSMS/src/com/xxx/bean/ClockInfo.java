package com.xxx.bean;


import java.sql.Date;
import java.sql.Timestamp;


public class ClockInfo {
    private int clockId; //id
    private String employeeNo; //员工编号
    private Timestamp clockInTime;  //上班打卡时间
    private Timestamp clockOffTime; //下班打卡时间
    private Date clockDate; //打卡日期


    public int getClockId() {
        return clockId;
    }

    public void setClockId(int clockId) {
        this.clockId = clockId;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Timestamp getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Timestamp clockInTime) {
        this.clockInTime = clockInTime;
    }

    public Timestamp getClockOffTime() {
        return clockOffTime;
    }

    public void setClockOffTime(Timestamp clockOffTime) {
        this.clockOffTime = clockOffTime;
    }

    public Date getClockDate() {
        return clockDate;
    }

    public void setClockDate(Date clockDate) {
        this.clockDate = clockDate;
    }
}
