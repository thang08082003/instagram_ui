package wp.zenny.model;

public class ClassExam {

	private String classId;
	private String examId;
	
	public ClassExam(String examId, String classId) {
		super();
		this.classId = classId;
		this.examId = examId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public ClassExam() {
		// TODO Auto-generated constructor stub
	}

}
