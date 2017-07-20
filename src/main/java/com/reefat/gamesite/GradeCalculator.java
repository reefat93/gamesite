package com.reefat.gamesite;

public class GradeCalculator {
	
	private String grade;
	
	public String getClassification(double mark) {
		if(mark >= 90) {
			grade = "distinction";
		} else if(mark >= 80) {
			grade = "merit";
		} else if(mark >= 75) {
			grade = "pass";
		} else {
			grade = "fail";
		}
		
		return grade;
	}
	
	public static void main(String[] args) {
		
		GradeCalculator calc = new GradeCalculator();
		
		calc.getClassification(82);
	}
}
