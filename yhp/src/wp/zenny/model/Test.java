package wp.zenny.model;

import java.util.Date;

public class Test {

	private String testId;
	private String testName;
	private Date dateCreate;
	private String creator;
	private String subId;
	
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Test(String testId, String testName, Date dateCreate, String creator, String subId) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.dateCreate = dateCreate;
		this.creator = creator;
		this.subId = subId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
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
	public Test() {
		// TODO Auto-generated constructor stub
	}

}
