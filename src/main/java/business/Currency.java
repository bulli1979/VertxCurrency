package business;

import java.math.BigDecimal;

public class Currency {
	private String code;
	private BigDecimal course;
	private String name;
	public Currency(){}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getCourse() {
		return course;
	}
	public void setCourse(BigDecimal course) {
		this.course = course;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
