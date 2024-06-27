package wp.zenny.model;

public class Subject {

	private String subId;
	private String subName;
	
	public Subject(String subId, String subName) {
		super();
		this.subId = subId;
		this.subName = subName;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public Subject() {
		// TODO Auto-generated constructor stub
	}

}
