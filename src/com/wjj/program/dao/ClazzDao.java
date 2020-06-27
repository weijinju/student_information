package com.wjj.program.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjj.program.model.Clazz;
import com.wjj.program.model.Page;
import com.wjj.program.util.StringUtil;

//�༶��Ϣ���ݿ⽻������
public class ClazzDao extends BaseDao {
	public List<Clazz> getClazzList(Clazz clazz,Page page){
		List<Clazz> ret = new ArrayList<Clazz>();
		String sql = "select * from s_clazz ";
		if(!StringUtil.isEmpty(clazz.getName())){
			sql += "where name like '%" + clazz.getName() + "%'";
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				Clazz cl = new Clazz();
				cl.setId(resultSet.getInt("id"));
				cl.setName(resultSet.getString("name"));
				cl.setInfo(resultSet.getString("info"));
				ret.add(cl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
//  ��ȡ�༶��Ϣ������
	public int getClazzListTotal(Clazz clazz){
		int total = 0;
		String sql = "select count(*)as total from s_clazz ";
		if(!StringUtil.isEmpty(clazz.getName())){
			sql += "where name like '%" + clazz.getName() + "%'";
		}
		ResultSet resultSet = query(sql);
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
//  ���Ӱ༶��Ϣ
	public boolean addClazz(Clazz clazz){
		String sql = "insert into s_clazz values(null,'"+clazz.getName()+"','"+clazz.getInfo()+"') ";
		return update(sql);
	}
//  ɾ���༶��Ϣ
	public boolean deleteClazz(int id){
		String sql = "delete from s_clazz where id = "+id;
		return update(sql);
	}
//  �༭�༶��Ϣ
	public boolean editClazz(Clazz clazz) {
		String sql = "update s_clazz set name = '"+clazz.getName()+"',info = '"+clazz.getInfo()+"' where id = " + clazz.getId();
		return update(sql);
	}
	
}
