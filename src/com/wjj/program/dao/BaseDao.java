package com.wjj.program.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wjj.program.util.DbUtil;

//Dao�������ݿ����ɾ�Ĳ�
//��װ��������
public class BaseDao {
	private DbUtil dbUtil = new DbUtil();
	
//  �ر����ݿ����Ӳ��ͷ���Դ
	public void closeCon(){
		dbUtil.closeCon();
	}
	
	//��������ѯ
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
//  ���ݿ����ݵ��޸Ĳ���
	public boolean update(String sql){
		try {
			return dbUtil.getConnection().prepareStatement(sql).executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//  �������ݿ�
	public Connection getConnection(){
		return dbUtil.getConnection();
	}
}
