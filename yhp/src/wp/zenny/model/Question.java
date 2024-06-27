package wp.zenny.model;

import java.util.Date;

public class Question {

	private String questionId;
	private String questionName;
	private String contentId;
	private Date dateCreate;
	private String creator;
	
	public Question(String questionId, String questionName, String contentId, Date dateCreate,
			String creator) {
		super();
		this.questionId = questionId;
		this.questionName = questionName;
		this.contentId = contentId;
		this.dateCreate = dateCreate;
		this.creator = creator;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
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

	public Question() {
		// TODO Auto-generated constructor stub
	}

}
