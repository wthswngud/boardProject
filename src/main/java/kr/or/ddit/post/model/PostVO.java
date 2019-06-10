package kr.or.ddit.post.model;

import java.sql.Date;

public class PostVO {
	private int postid;
	private int boardid;
	private String contentcul;
	private int postid2;
	private String deletecul;
	private Date dtcreation;
	private String userid;
	private String titlecul;
	private int viewscul;
	private int lv;
	private int groupSeq;
	private int rn;

	public PostVO(int postid, int boardid, String contentcul, int postid2,
			String deletecul, Date dtcreation, String userid, String titlecul,
			int viewscul, int lv, int groupSeq, int rn) {
		super();
		this.postid = postid;
		this.boardid = boardid;
		this.contentcul = contentcul;
		this.postid2 = postid2;
		this.deletecul = deletecul;
		this.dtcreation = dtcreation;
		this.userid = userid;
		this.titlecul = titlecul;
		this.viewscul = viewscul;
		this.lv = lv;
		this.groupSeq = groupSeq;
		this.rn = rn;
	}
	
	public PostVO() {
		
	}
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getContentcul() {
		return contentcul;
	}
	public void setContentcul(String contentcul) {
		this.contentcul = contentcul;
	}
	public int getPostid2() {
		return postid2;
	}
	public void setPostid2(int postid2) {
		this.postid2 = postid2;
	}
	public String getDeletecul() {
		return deletecul;
	}
	public void setDeletecul(String deletecul) {
		this.deletecul = deletecul;
	}
	public Date getDtcreation() {
		return dtcreation;
	}
	public void setDtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitlecul() {
		return titlecul;
	}
	public void setTitlecul(String titlecul) {
		this.titlecul = titlecul;
	}
	public int getViewscul() {
		return viewscul;
	}
	public void setViewscul(int viewscul) {
		this.viewscul = viewscul;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	
	@Override
	public String toString() {
		return "PostVO [postid=" + postid + ", boardid=" + boardid
				+ ", contentcul=" + contentcul + ", postid2=" + postid2
				+ ", deletecul=" + deletecul + ", dtcreation=" + dtcreation
				+ ", userid=" + userid + ", titlecul=" + titlecul
				+ ", viewscul=" + viewscul + ", lv=" + lv + ", groupSeq="
				+ groupSeq + ", rn=" + rn + "]";
	}
}
