package testORM;
/**
 * �����ݿ��е�dept���Ӧ
 * @author hc
 *
 */
public class Dept {
	private Integer id;
	private String name;
	private String address;
	
	//�չ�����
	public Dept() {
		
	}
	//����id�Ĺ�����
	public Dept(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	//��id�Ĺ�����
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
