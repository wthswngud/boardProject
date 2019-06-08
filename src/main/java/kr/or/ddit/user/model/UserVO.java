package kr.or.ddit.user.model;

public class UserVO {
	private String userid;
	private String name;
	private String alias;
	private String pass;
	
	public UserVO(String userid, String name, String alias, String pass) {
		super();
		this.userid = userid;
		this.name = name;
		this.alias = alias;
		this.pass = pass;
	}
	
	public UserVO() {
	
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
