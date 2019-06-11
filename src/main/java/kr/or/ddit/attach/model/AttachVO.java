package kr.or.ddit.attach.model;

public class AttachVO {
	private int postid;
	private String filename;
	private String attachpath;
	private int attachid;
	
	public AttachVO(int postid, String filename, String attachpath, int attachid) {
		super();
		this.postid = postid;
		this.filename = filename;
		this.attachpath = attachpath;
		this.attachid = attachid;
	}
	
	public AttachVO() {
		
	}
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getAttachpath() {
		return attachpath;
	}
	public void setAttachpath(String attachpath) {
		this.attachpath = attachpath;
	}
	public int getAttachid() {
		return attachid;
	}
	public void setAttachid(int attachid) {
		this.attachid = attachid;
	}
}
