package com.xxx.dao;

import com.xxx.bean.Good;

import java.util.List;

public interface IGoodsDao {
    //查询所有商品
    public List<Good> queryAll();
    //查询稀缺商品
    public List<Good> getGoodsInfo();
    //查询一种商品,通过编号查询
    public Good getGood(int num);
    //添加一个商品信息
    public boolean addGood(Good good);
    //修改一些商品信息
    public boolean updateGood(List<Good> goodList);
    //修改一个商品信息
    public boolean updateGood(Good good);
    //删除商品的信息
    public boolean delGood(int num);
}
