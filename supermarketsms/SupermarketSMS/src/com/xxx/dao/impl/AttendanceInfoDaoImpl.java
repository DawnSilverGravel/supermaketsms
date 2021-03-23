package com.xxx.dao.impl;

import com.xxx.bean.AttendanceInfo;
import com.xxx.dao.IAttendanceInfoDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AttendanceInfoDaoImpl extends DBUtil<AttendanceInfo> implements IAttendanceInfoDao {
    @Override
    public List<AttendanceInfo> getAllAttendanceInfo() {
        return query("select * from check_info");
    }
    @Override
    public List<AttendanceInfo> getAnEmployeeAllAttendanceInfo(String employeeNo) {
        return query("select * from check_info where employee_no = ?", employeeNo);
    }
    public  List<AttendanceInfo>  getAttendanceOfEYMD(String employeeNo,Integer...ymd){
        String year = "";
        String month = "";
        String day = "";
        if (ymd.length > 0)
            year = " year(work_date)=? ";
        if (ymd.length > 1)
            month = " and month(work_date)=? ";
        if (ymd.length > 2)
            day = " and day(work_date)=? ";
        if(employeeNo==null)
             return query("select * from check_info where "+year+month+day,ymd);
        else
            return query("select * from check_info where employee_no=\'"+employeeNo+"\' and "+year+month+day,ymd);
    }

    @Override
    public AttendanceInfo getEntity(ResultSet rs) throws SQLException {
       AttendanceInfo ai = new AttendanceInfo();
       ai.setWorkDate(rs.getDate(1));
       ai.setEmployeeNo(rs.getString(2));
       ai.setClockInTime(rs.getTimestamp(3));
       ai.setClockOffTime(rs.getTimestamp(4));
       ai.setDiffInStatus(rs.getString(5));
       ai.setDiffOffTime(rs.getString(6));
       return ai;
    }
}
