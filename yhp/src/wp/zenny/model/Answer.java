package wp.zenny.model;

public class Answer {

	private String answerId;
	private String questionId;
	private String answerName;
	private String isCorrect;
	
	public Answer(String answerId, String answerName, String questionId, String isCorrect) {
		super();
		this.answerId = answerId;
		this.answerName = answerName;
		this.questionId = questionId;
		this.isCorrect = isCorrect;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public String getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Answer() {
		// TODO Auto-generated constructor stub
	}

}
