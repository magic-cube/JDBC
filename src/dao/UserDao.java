package dao;

import JDBC2.domain.User;

public interface UserDao {
	public void addUser(User user);
	
	public void delete(User user);
	
	public User getUser(int userId);
	
	public void update(User user);
	
	public User findUser(String loginName,String password);
	
	
}
