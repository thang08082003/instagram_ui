package wp.zenny.model;

import java.util.Date;

public class Content {

	private String contentId;
	private String contentName;
	private String subId;
	private Date dateCreate;
	private String creator;
	
	public Content(String contentId, String contentName, String subId, Date dateCreate, String creator) {
		super();
		this.contentId = contentId;
		this.contentName = contentName;
		this.subId = subId;
		this.dateCreate = dateCreate;
		this.creator = creator;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Content() {
		// TODO Auto-generated constructor stub
	}

}
