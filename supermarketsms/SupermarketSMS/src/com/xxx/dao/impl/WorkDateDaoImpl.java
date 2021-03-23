package com.xxx.dao.impl;

import com.xxx.bean.WorkDate;
import com.xxx.dao.IWorkDateDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class WorkDateDaoImpl extends DBUtil<WorkDate> implements IWorkDateDao {
    @Override
    public WorkDate getEntity(ResultSet rs) throws SQLException {
        WorkDate wd = new WorkDate();
        wd.setWorkDateId(rs.getInt(1));
        wd.setWorkDate(rs.getDate(2));
        return wd;
    }

    @Override
    public boolean addDate() {
        java.sql.Date date= new java.sql.Date(new Date().getTime());
        List<WorkDate> list = query("select * from work_date where work_date=?",date);
        if(list.size()>0)
            return false;
        else
            return update("insert into work_date(work_date) values(?)",date);
    }
}
