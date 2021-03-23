package com.xxx.bean;

import java.sql.Date;

public class WorkDate {
    private int workDateId; //工作日期ID
    private Date workDate;  //工作日期

    public int getWorkDateId() {
        return workDateId;
    }

    public void setWorkDateId(int workDateId) {
        this.workDateId = workDateId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }
}
