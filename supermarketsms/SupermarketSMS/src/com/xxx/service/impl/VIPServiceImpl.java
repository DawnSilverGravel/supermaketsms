package com.xxx.service.impl;

import com.xxx.bean.VIP;
import com.xxx.dao.impl.VIPDaoImpl;
import com.xxx.service.IVIPService;

import java.util.List;
import java.util.Scanner;

public class VIPServiceImpl implements IVIPService {
    private VIPDaoImpl ivipDao = new VIPDaoImpl();
    private Scanner in = new Scanner(System.in);
    @Override
    public void login() {

        System.out.println("==========进入会员登录===========");
        String name;
        String phone;
        List<VIP> list;
        VIP vip;
        int i=1;
        do{
            System.out.print("请输入会员名:");
            name = in.next();
            System.out.print("请输入手机号码:");
            phone = in.next();
            vip = ivipDao.getVIPInfo(name,phone);
            if(vip!=null){
                search(vip);
                break;
            }
            else
                System.out.println("手机号号码不正确或会员不存在！");
            i++;
            if(i==3){
                System.out.println("你操作次数超过限制！");
                break;
            }
        }while(true);

    }


    private void search(VIP vip) {
        System.out.println("尊敬的"+vip.getVipName()+"会员");
        System.out.println("您的基本信息为:");
        System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
        System.out.println( vip.getVipNumber()+"\t"+vip.getVipName()+"\t"+vip.getVipScore()+
                "\t"+vip.getVipPhone()+"\t"+vip.getVipDate());
        System.out.print("输入任意键退出登录:");
        String str=in.next();
    }
}
