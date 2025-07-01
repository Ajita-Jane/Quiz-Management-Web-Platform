package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Quiz {
	@Id
	public int quizno;
	public String name;
	public String Subject;
	public int mark;
	public List<Integer> questions;
	
	
	public Quiz() {
		super();
	}


	public int getQuizno() {
		return quizno;
	}


	public void setQuizno(int quizno) {
		this.quizno = quizno;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSubject() {
		return Subject;
	}


	public void setSubject(String subject) {
		Subject = subject;
	}


	public int getMark() {
		return mark;
	}


	public void setMark(int mark) {
		this.mark = mark;
	}


	public List<Integer> getQuestions() {
		return questions;
	}


	public void setQuestions(List<Integer> questions) {
		this.questions = questions;
	}
	
	

}
