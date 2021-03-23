package com.xxx.dao;

import com.xxx.bean.VIP;
import java.util.List;

public interface IVipDao {
    //查询所有会员
    public List<VIP> queryAll();
    //查询一个会员信息, 通过会员名和手机号来查询
    public VIP getVIPInfo(String name,String phone);
    //查询一个会员,通过手机号查询
    public VIP getVIPInfo(String phone);
    //通过编号查询会员
    public VIP getVIPByNum(String number);
    //添加一个会员信息
    public boolean addVIP(VIP vip);
    //修改会员的信息
    public boolean updateVIP(VIP vip);
    //删除会员的信息
    public boolean delVIP(String number);
}
