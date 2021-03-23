package com.xxx.dao.impl;

import com.xxx.bean.Turnover;
import com.xxx.dao.ITurnoverDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TurnoverDaoImpl extends DBUtil<Turnover> implements ITurnoverDao {
    private String sql = "select  s_c_number, s_quantity," +
            " s_time, s_e_number, s_vip_number" +
            ",c_price,vip_price " +
            " from sell_info,goods where s_c_number = c_number ";

    @Override
    public double getAll() {
        List<Turnover> list = query(sql);
        return getTurnover(list);
    }

    @Override
    public double getTurnoverOfYMD(Integer... ymd) {
        String year = "";
        String month = "";
        String day = "";
        if (ymd.length > 0)
            year = "and year(s_time)=? ";
        if (ymd.length > 1)
            month = " and month(s_time)=? ";
        if (ymd.length > 2)
            day = " and day(s_time)=? ";
        return getTurnover(query(sql + "  " + year + month + day, ymd));
    }


    @Override
    public double getTurnoverOfQuarter(int year, int quarter) {
        String month = "";
        if (quarter == 1)
            month = " month(s_time)>=1 and month<=3";
        else if (quarter == 2)
            month = " month(s_time)>=4 and month<=6";
        else if (quarter == 3)
            month = " month(s_time)>=7 and month<=9";
        else
            month = " month(s_time)>=10 and month<=12";

        return getTurnover( query(sql+ "and year(s_time)=? and "+month,year));
    }


    @Override
    public double getTurnoverYearToYear(int year1, int year2) {
        return getTurnover(query(sql + " and year(s_time)>=? and year(s_time)<=?", year1, year2));
    }

    @Override
    public Turnover getEntity(ResultSet rs) throws SQLException {
        Turnover t = new Turnover();
        t.setGoodNumber(rs.getInt(1));
        t.setQuantity(rs.getInt(2));
        t.setTime(rs.getDate(3));
        t.seteNumber(rs.getString(4));
        t.setVipNumber(rs.getString(5));
        t.setPrice(rs.getDouble(6));
        t.setVipPrice(rs.getDouble(7));
        return t;
    }

    private double getTurnover(List<Turnover> list) {
        double totalCost = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVipNumber() != null) {
                totalCost += list.get(i).getPrice() * list.get(i).getQuantity();
            } else
                totalCost += list.get(i).getVipPrice() * list.get(i).getQuantity();
        }
        return totalCost;
    }

}
