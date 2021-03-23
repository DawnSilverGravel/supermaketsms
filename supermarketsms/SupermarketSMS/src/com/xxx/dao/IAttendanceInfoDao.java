package com.xxx.dao;

import com.xxx.bean.AttendanceInfo;
import com.xxx.bean.Employee;

import java.util.List;

public interface IAttendanceInfoDao {
    //查询所有的员工出勤记录
    public List<AttendanceInfo> getAllAttendanceInfo();
    //查询某员工所有出勤记录
    public List<AttendanceInfo> getAnEmployeeAllAttendanceInfo(String employeeNo);
    //参数有员工编号,年,月,日
    public List<AttendanceInfo>  getAttendanceOfEYMD(String employeeNo,Integer...ymd);

}
