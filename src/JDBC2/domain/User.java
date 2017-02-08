package JDBC2.domain;

import java.util.Date;

/**
 * domain对象，实体，与数据库中的表对应
 * @author hc
 *
 */
public class User {
	private int id;
	private String name;
	private Date birthday;
	private Float money;
	
	
	public User(int id, String name, Date birthday, Float money) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.money = money;
	}

	public User() {
		
	}
	
	public User(int id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	public Float getMoney() {
		return money;
	}
	
	public void setMoney(Float money) {
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
