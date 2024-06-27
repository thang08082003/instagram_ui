package wp.zenny.model;

import java.util.Date;

public class Result {
	String examname;
	float mark;
	Date dateTest;
	int timeFinish;
	public Result() {
	}
	public Result(String examname, float mark,  int timeFinish,Date dateTest) {
		super();
		this.examname = examname;
		this.mark = mark;
		this.dateTest = dateTest;
		this.timeFinish = timeFinish;
	}
	public String getExamname() {
		return examname;
	}
	public void setExamname(String examname) {
		this.examname = examname;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	public Date getDateTest() {
		return dateTest;
	}
	public void setDateTest(Date dateTest) {
		this.dateTest = dateTest;
	}
	public int getTimeFinish() {
		return timeFinish;
	}
	public void setTimeFinish(int timeFinish) {
		this.timeFinish = timeFinish;
	}

}
