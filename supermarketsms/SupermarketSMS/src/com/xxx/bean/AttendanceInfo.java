package com.xxx.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class AttendanceInfo {
    private Date workDate; //工作日期
    private String employeeNo; //员工编号
    private Timestamp clockInTime; //上班打卡时间
    private Timestamp clockOffTime; //下班打卡时间
    private String diffInStatus;  //上班打卡状态
    private String diffOffTime;   //下班打卡状态

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
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

    public String getDiffInStatus() {
        return diffInStatus;
    }

    public void setDiffInStatus(String diffInStatus) {
        this.diffInStatus = diffInStatus;
    }

    public String getDiffOffTime() {
        return diffOffTime;
    }

    public void setDiffOffTime(String diffOffTime) {
        this.diffOffTime = diffOffTime;
    }
}
