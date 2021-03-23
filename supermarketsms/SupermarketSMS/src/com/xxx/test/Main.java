package com.xxx.test;

import com.xxx.bean.AttendanceInfo;
import com.xxx.dao.impl.AttendanceInfoDaoImpl;
import com.xxx.dao.impl.WorkDateDaoImpl;

import java.util.List;
import java.util.function.IntBinaryOperator;

public class Main {

    public static void main(String[] args) {
        Menu m = new Menu();
        m.addDate();
        m.login();
    }
}
