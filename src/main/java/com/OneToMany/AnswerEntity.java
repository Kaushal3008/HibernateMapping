package com.OneToMany;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Answer")
public class AnswerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int answerId;

	String answer;	
	@ManyToOne(cascade = CascadeType.ALL)
	QuestionEntity question;

	public QuestionEntity getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "answerId"+" "+answerId+" "+"Answer"+" "+answer;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
