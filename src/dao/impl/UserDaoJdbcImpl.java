package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC2.JdbcUtils;
import JDBC2.domain.User;
import dao.DaoException;
import dao.UserDao;

public class UserDaoJdbcImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="insert into user(name,birthday,money) values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			
			//执行语句
			int i=ps.executeUpdate();
			System.out.println("i="+i);
			
		}catch(SQLException e){
			//抛一个运行时异常，即没有污染接口，也不用你的上一层去catch 
			throw new DaoException(e.getMessage(),e);
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
		
	}

	@Override
	public void delete(User user) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			st=conn.createStatement();
			String sql="delete from user where id="+user.getId();
			//执行语句
			int i=st.executeUpdate(sql);
			System.out.println("i="+i);
			
		}catch(SQLException e){
			throw new DaoException(e.getMessage(),e);
		}finally{
			JdbcUtils.free(rs, st, conn);
		}
	}

	@Override
	public User getUser(int userId) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="select id,name,birthday,money from user where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, userId);
			//执行语句
			rs=ps.executeQuery();
			//处理结果
			while(rs.next()){
				mappingUser(rs);
			}
		}catch(SQLException e){
			throw new DaoException(e.getMessage(),e);
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}
	
	@Override
	public void update(User user) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="update user set name=?,birthday=?,money=? where id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setDate(2, new java.sql.Date(user.getBirthday().getTime()));
			ps.setFloat(3, user.getMoney());
			ps.setInt(4, user.getId());
			//执行语句
			int i=ps.executeUpdate();
			System.out.println("i="+i);
			
		}catch(SQLException e){
			throw new DaoException(e.getMessage(),e);
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
	}

	@Override
	public User findUser(String loginName, String password) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			//建立连接
			conn=JdbcUtils.getConnection();
			//创建语句
			String sql="select id,name,birthday,money from user where name=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1,loginName);
			//执行语句
			rs=ps.executeQuery();
			//处理结果
			while(rs.next()){
				user=mappingUser(rs);
			}
		}catch(SQLException e){
			throw new DaoException(e.getMessage(),e);
		}finally{
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user;
		user=new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setMoney(rs.getFloat("money"));
		user.setBirthday(rs.getDate("birthday"));
		return user;
	}

}
