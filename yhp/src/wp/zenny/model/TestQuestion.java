package wp.zenny.model;

public class TestQuestion {
	private String testId;
	private String questionId;
	
	public TestQuestion(String testId, String questionId) {
		super();
		this.testId = testId;
		this.questionId = questionId;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public TestQuestion() {
		// TODO Auto-generated constructor stub
	}

}
