package com.xxx.dao.impl;

import com.xxx.bean.Good;
import com.xxx.dao.IGoodsDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsDaoImpl extends DBUtil<Good> implements IGoodsDao {

    @Override
    public List<Good> queryAll() {
        return query("select * from goods");
    }

    @Override
    public List<Good> getGoodsInfo() {
        //定义稀缺商品的上限
        int count = 100;
        return query("select * from goods where inventory<?",count);
    }

    @Override
    public Good getGood(int num) {
        List<Good> list = query("select * from goods where c_number=?",num);
        if(list.size()>0)
            return list.get(0);
        return null;
    }



    @Override
    public boolean addGood(Good good) {
        return update("insert into  goods values(?,?,?,?)",
                good.getName(),good.getPrice(),good.getVipPrice(),
                good.getInventory());
    }

    @Override
    public boolean updateGood(List<Good> goodList) {
        for(Good good:goodList){
            update("update goods set c_name=?, c_price=?, vip_price=?, inventory=? " +
                            "where c_number=?", good.getName(),good.getPrice(),good.getVipPrice(),
                    good.getInventory(),good.getNumber());
        }
        return false;
    }

    @Override
    public boolean updateGood(Good good) {
        return update("update goods set c_name=?, c_price=?, vip_price=?, inventory=? " +
                "where c_number=?", good.getName(),good.getPrice(),good.getVipPrice(),
                good.getInventory(),good.getNumber());
    }

    @Override
    public boolean delGood(int num) {
        return update("delete from goods where id=?",num);
    }

    @Override
    public Good getEntity(ResultSet rs) throws SQLException {
        Good good = new Good();
        good.setNumber(rs.getInt(1));
        good.setName(rs.getString(2));
        good.setPrice(rs.getDouble(3));
        good.setVipPrice(rs.getDouble(4));
        good.setInventory(rs.getInt(5));
        return good;
    }
}
