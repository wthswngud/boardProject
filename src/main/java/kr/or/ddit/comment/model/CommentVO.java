package kr.or.ddit.comment.model;

import java.util.Date;

public class CommentVO {
	private int postid;
	private String contentcul;
	private int mentid;
	private String deletecul;
	private Date dtcreation;
	private String userid;
	
	
	public CommentVO(int postid, String contentcul, int mentid, String deletecul, Date dtcreation, String userid) {
		super();
		this.postid = postid;
		this.contentcul = contentcul;
		this.mentid = mentid;
		this.deletecul = deletecul;
		this.dtcreation = dtcreation;
		this.userid = userid;
	}
	
	public CommentVO() {
		
	}
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getContentcul() {
		return contentcul;
	}
	public void setContentcul(String contentcul) {
		this.contentcul = contentcul;
	}
	public int getMentid() {
		return mentid;
	}
	public void setMentid(int mentid) {
		this.mentid = mentid;
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
}
