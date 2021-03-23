package com.xxx.dao;



import com.xxx.bean.SellInfo;

import java.util.List;

public interface ISellInfoDao {
    //查询所有账单信息
    public List<SellInfo> queryAll();
    //查询一个账单信息
    public List<SellInfo> getSellInfo(String[] strings,Object...objects);
    //添加一个账单信息
    public boolean addSellInfo(SellInfo SellInfo);
    //添加一些账单信息
    public boolean addSellInfo(List<SellInfo> list);
    //修改账单的信息
    public boolean updateSellInfo(SellInfo SellInfo);
    //删除账单的信息
    public boolean delSellInfo(int empno);
}
