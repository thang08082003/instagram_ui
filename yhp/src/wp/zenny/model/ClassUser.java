package wp.zenny.model;

public class ClassUser {

	private String classId;
	private String username;
	
	public ClassUser(String classId, String username) {
		super();
		this.classId = classId;
		this.username = username;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ClassUser() {
		// TODO Auto-generated constructor stub
	}

}
