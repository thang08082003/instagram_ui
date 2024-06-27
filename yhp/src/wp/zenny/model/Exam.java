package wp.zenny.model;

import java.util.Date;

public class Exam {
	private String examId;
	private String testId;
	private String examName;
	private int timeTest;
	private Date dateStart;
	private Date dateCreate;
	private String creator;
	
	
	public Exam(String examId, String testId, String examName, int timeTest, Date dateStart, Date dateCreate,
			String creator) {
		super();
		this.examId = examId;
		this.testId = testId;
		this.examName = examName;
		this.timeTest = timeTest;
		this.dateStart = dateStart;
		this.dateCreate = dateCreate;
		this.creator = creator;
	}


	public String getExamId() {
		return examId;
	}


	public void setExamId(String examId) {
		this.examId = examId;
	}


	public String getTestId() {
		return testId;
	}


	public void setTestId(String testId) {
		this.testId = testId;
	}


	public String getExamName() {
		return examName;
	}


	public void setExamName(String examName) {
		this.examName = examName;
	}


	public int getTimeTest() {
		return timeTest;
	}


	public void setTimeTest(int timeTest) {
		this.timeTest = timeTest;
	}


	public Date getDateStart() {
		return dateStart;
	}


	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
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


	public Exam() {}
}
