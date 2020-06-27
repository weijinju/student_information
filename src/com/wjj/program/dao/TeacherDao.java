package com.wjj.program.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wjj.program.model.Page;
import com.wjj.program.model.Teacher;
import com.wjj.program.util.StringUtil;

/**
 * 
 *教师表数据库操作
 */
//教师数据库信息交互
public class TeacherDao extends BaseDao {
	public boolean addTeacher(Teacher teacher){
		String sql = "insert into s_teacher values(null,'"+teacher.getSn()+"','"+teacher.getName()+"'";
		sql += ",'" + teacher.getPassword() + "'," + teacher.getClazzId();
		sql += ",'" + teacher.getSex() + "','" + teacher.getMobile() + "'";
		sql += ",'" + teacher.getQq() + "',null)";
		return update(sql);
	}
//  编辑教师信息
	public boolean editTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "update s_teacher set name = '"+teacher.getName()+"'";
		sql += ",sex = '" + teacher.getSex() + "'";
		sql += ",mobile = '" + teacher.getMobile() + "'";
		sql += ",qq = '" + teacher.getQq() + "'";
		sql += ",clazz_id = " + teacher.getClazzId();
		sql += " where id = " + teacher.getId();
		return update(sql);
	}
//  设置教师图片
	public boolean setTeacherPhoto(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "update s_teacher set photo = ? where id = ?";
		Connection connection = getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setBinaryStream(1, teacher.getPhoto());
			prepareStatement.setInt(2, teacher.getId());
			return prepareStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update(sql);
	}
//  删除教师信息
	public boolean deleteTeacher(String ids) {
		// TODO Auto-generated method stub
		String sql = "delete from s_teacher where id in("+ids+")";
		return update(sql);
	}
//  获取教师信息
	public Teacher getTeacher(int id){
		String sql = "select * from s_teacher where id = " + id;
		Teacher teacher = null;
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				teacher = new Teacher();
				teacher.setId(resultSet.getInt("id"));
				teacher.setClazzId(resultSet.getInt("clazz_id"));
				teacher.setMobile(resultSet.getString("mobile"));
				teacher.setName(resultSet.getString("name"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setPhoto(resultSet.getBinaryStream("photo"));
				teacher.setQq(resultSet.getString("qq"));
				teacher.setSex(resultSet.getString("sex"));
				teacher.setSn(resultSet.getString("sn"));
				return teacher;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teacher;
	}
//  获取教师列表
	public List<Teacher> getTeacherList(Teacher teacher,Page page){
		List<Teacher> ret = new ArrayList<Teacher>();
		String sql = "select * from s_teacher ";
		if(!StringUtil.isEmpty(teacher.getName())){
			sql += "and name like '%" + teacher.getName() + "%'";
		}
		if(teacher.getClazzId() != 0){
			sql += " and clazz_id = " + teacher.getClazzId();
		}
		if(teacher.getId() !=0 ){
			sql += " and id = " + teacher.getId();
		}
		sql += " limit " + page.getStart() + "," + page.getPageSize();
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				Teacher t = new Teacher();
				t.setId(resultSet.getInt("id"));
				t.setClazzId(resultSet.getInt("clazz_id"));
				t.setMobile(resultSet.getString("mobile"));
				t.setName(resultSet.getString("name"));
				t.setPassword(resultSet.getString("password"));
				t.setPhoto(resultSet.getBinaryStream("photo"));
				t.setQq(resultSet.getString("qq"));
				t.setSex(resultSet.getString("sex"));
				t.setSn(resultSet.getString("sn"));
				ret.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
//  获取教师信息总数
	public int getTeacherListTotal(Teacher teacher){
		int total = 0;
		String sql = "select count(*)as total from s_teacher ";
		if(!StringUtil.isEmpty(teacher.getName())){
			sql += "and name like '%" + teacher.getName() + "%'";
		}
		if(teacher.getClazzId() != 0){
			sql += " and clazz_id = " + teacher.getClazzId();
		}
		if(teacher.getId() !=0 ){
			sql += " and id = " + teacher.getId();
		}
		ResultSet resultSet = query(sql.replaceFirst("and", "where"));
		try {
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}
//  设置教师登录
	public Teacher login(String name ,String password){
		String sql = "select * from s_teacher where name = '" + name + "' and password = '" + password + "'";
		ResultSet resultSet = query(sql);
		try {
			if(resultSet.next()){
				Teacher teacher = new Teacher();
				teacher.setId(resultSet.getInt("id"));
				teacher.setName(resultSet.getString("name"));
				teacher.setPassword(resultSet.getString("password"));
				teacher.setClazzId(resultSet.getInt("clazz_id"));
				teacher.setMobile(resultSet.getString("mobile"));
				teacher.setPhoto(resultSet.getBinaryStream("photo"));
				teacher.setQq(resultSet.getString("qq"));
				teacher.setSex(resultSet.getString("sex"));
				teacher.setSn(resultSet.getString("sn"));
				return teacher;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//  教师修改自己的密码
	public boolean editPassword(Teacher teacher,String newPassword) {
		// TODO Auto-generated method stub
		String sql = "update s_teacher set password = '"+newPassword+"' where id = " + teacher.getId();
		return update(sql);
	}
}
