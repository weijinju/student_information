package com.wjj.program.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wjj.program.util.DbUtil;

//Dao中是数据库的增删改查
//封装基本操作
public class BaseDao {
	private DbUtil dbUtil = new DbUtil();
	
//  关闭数据库连接并释放资源
	public void closeCon(){
		dbUtil.closeCon();
	}
	
	//多条语句查询
	public ResultSet query(String sql){
		try {
			PreparedStatement prepareStatement = dbUtil.getConnection().prepareStatement(sql);
			return prepareStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} 
//  数据库内容的修改操作
	public boolean update(String sql){
		try {
			return dbUtil.getConnection().prepareStatement(sql).executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//  连接数据库
	public Connection getConnection(){
		return dbUtil.getConnection();
	}
}
