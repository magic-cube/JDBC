package dao;

import java.util.Date;

import JDBC2.domain.User;
import dao.impl.UserDaoJdbcImpl;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao userDao=new UserDaoJdbcImpl();
		
		User user=new User();
		user.setMoney(1000.0f);
		user.setName("name1");
		user.setBirthday(new Date());
		
		userDao.addUser(user);
		
		User u=userDao.findUser(user.getName(),null);
		System.out.println(u.getId());
	}
}
