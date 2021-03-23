package com.xxx.dao.impl;

import com.xxx.bean.SellInfo;
import com.xxx.dao.ISellInfoDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SellInfoDaoImpl extends DBUtil<SellInfo> implements ISellInfoDao {
    @Override
    public List<SellInfo> queryAll() {
        return query("select * from sell_info");
    }

    @Override
    public List<SellInfo> getSellInfo(String[] strings, Object... objects) {
        return null;
    }


    @Override
    public boolean addSellInfo(SellInfo SellInfo) {
        return false;
    }

    @Override
    public boolean addSellInfo(List<SellInfo> list) {
        for(SellInfo sellInfo:list){
            update("insert into sell_info values(?,?,?,?,?)",sellInfo.getGoodNumber(),
                    sellInfo.getQuantity(),sellInfo.getTime(),sellInfo.geteNumber(),sellInfo.getVipNumber());
        }
        return false;
    }

    @Override
    public boolean updateSellInfo(SellInfo SellInfo) {
        return false;
    }

    @Override
    public boolean delSellInfo(int empno) {
        return false;
    }

    @Override
    public SellInfo getEntity(ResultSet rs) throws SQLException {
        return null;
    }
}
