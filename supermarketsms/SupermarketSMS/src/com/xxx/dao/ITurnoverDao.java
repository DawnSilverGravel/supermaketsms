package com.xxx.dao;

import com.xxx.bean.Turnover;

import java.util.List;

public interface ITurnoverDao {
    //获取所有的营业额
    public double getAll();
    //获取某年/某月/某天的营业额
    public double getTurnoverOfYMD(Integer...ymd);

    //获取季度营业额
    public double getTurnoverOfQuarter(int year,int quarter);

    //获取某年到某年的营业额
    public double getTurnoverYearToYear(int year1,int year2);

}
