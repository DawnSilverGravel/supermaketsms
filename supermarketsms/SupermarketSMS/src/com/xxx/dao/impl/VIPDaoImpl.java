package com.xxx.dao.impl;

import com.xxx.bean.VIP;
import com.xxx.dao.IVipDao;
import com.xxx.utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VIPDaoImpl extends DBUtil<VIP> implements IVipDao {
    @Override
    public List<VIP> queryAll() {
        return query("select * from vip");
    }

    @Override
    public VIP getVIPInfo(String name,String phone) {
        VIP vip =null;
        List<VIP> list = query("select * from vip where v_name=? and v_phone=?",name,phone);
        if (list.size() > 0)
            vip = list.get(0);
        return vip;

    }
    @Override
    public VIP getVIPInfo(String phone) {
        VIP vip =null;
        List<VIP> list = query("select * from vip where  v_phone=?",phone);
        if (list.size() > 0)
            vip = list.get(0);
        return vip;

    }

    @Override
    public VIP getVIPByNum(String number) {
        VIP vip =null;
        List<VIP> list = query("select * from vip where  v_number=?",number);
        if (list.size() > 0)
            vip = list.get(0);
        return vip;
    }

    @Override
    public boolean addVIP(VIP vip) {
        return update("insert into vip values(?,?,?,?,?)",
                vip.getVipNumber(),vip.getVipName(),vip.getVipScore(),
        vip.getVipPhone(),vip.getVipDate());
    }

    @Override
    public boolean updateVIP(VIP vip) {
        return update("update vip set v_name=?, v_score=?, v_phone=?",
                vip.getVipName(),vip.getVipScore(),
                vip.getVipPhone());
    }

    @Override
    public boolean delVIP(String number) {
        return update("delete from vip where v_number=?",number);
    }

    @Override
    public VIP getEntity(ResultSet rs) throws SQLException {
        VIP vip = new VIP();
        vip.setVipNumber(rs.getString(1));
        vip.setVipName(rs.getString(2));
        vip.setVipScore(rs.getInt(3));
        vip.setVipPhone(rs.getString(4));
        vip.setVipDate(rs.getDate(5));
        return vip;
    }
}
