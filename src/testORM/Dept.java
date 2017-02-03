package testORM;
/**
 * 与数据库中的dept表对应
 * @author hc
 *
 */
public class Dept {
	private Integer id;
	private String name;
	private String address;
	
	//空构造器
	public Dept() {
		
	}
	//不带id的构造器
	public Dept(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	//带id的构造器
	public Dept(Integer id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
