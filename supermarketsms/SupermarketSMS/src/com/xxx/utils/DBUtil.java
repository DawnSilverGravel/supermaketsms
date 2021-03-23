package com.xxx.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DBUtil<T> {
    private Connection conn = null;
    private PreparedStatement pds = null;
    private ResultSet rs = null;

    //共享连接方法
    public Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket?"
        + "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull", "root", "katomegumi");
        return conn;
    }

    //查询
    public List<T> query(String sql,Object...objects)  {
        List<T> list=new ArrayList<>();
        try {
            pds = getConn().prepareStatement(sql);
            for(int i=0;i<objects.length;i++){
                pds.setObject(i+1,objects[i]);
            }
            rs=pds.executeQuery();
            while(rs.next()){
                T t=getEntity(rs);
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            getClose(rs,pds,conn);
        }
        return list;
    }



    public abstract T getEntity(ResultSet rs) throws SQLException;

    //事务的增删改查
//    public boolean update(String sql,List<T> list,Object...objects){
//        try {
//            pds = getConn().prepareStatement(sql);
//
//            getConn().setAutoCommit(false);
//
//            for(int i=0;i<objects.length;i++){
//                pds.setObject(i+1,objects[i]);
//            }
//            int res=pds.executeUpdate();
//            if(res>0)
//                return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        finally {
//            getClose(rs,pds,conn);
//        }
//        return false;
//    }

    //增删改
    public boolean update(String sql,Object...objects){
        try {
            pds = getConn().prepareStatement(sql);
            for(int i=0;i<objects.length;i++){
                pds.setObject(i+1,objects[i]);
            }
            int res=pds.executeUpdate();
            if(res>0)
                return true;
        } catch (SQLException e) {
           e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            getClose(rs,pds,conn);
        }
        return false;
    }




    //关闭数据库连接
    public void getClose(ResultSet rs, PreparedStatement pds, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (pds != null)
                pds.close();
            if (conn != null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
