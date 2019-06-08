package kr.or.ddit.board.model;

public class BoardVO {
	private int boardid;
	private String name;
	private String use_yn;
	private String reg_dt;
	private String userid;
	
	
	public BoardVO(int boardid, String name, String use_yn, String reg_dt,
			String userid) {
		super();
		this.boardid = boardid;
		this.name = name;
		this.use_yn = use_yn;
		this.reg_dt = reg_dt;
		this.userid = userid;
	}
	
	public BoardVO() {
		
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardid=" + boardid + ", name=" + name + ", use_yn="
				+ use_yn + ", reg_dt=" + reg_dt + ", userid=" + userid + "]";
	}

	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
