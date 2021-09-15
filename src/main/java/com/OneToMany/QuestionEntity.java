package com.OneToMany;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class QuestionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int questionId;
	
	@Column(name="question",length = 100,nullable = false,unique=true)
	String question;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy ="question")
	List<AnswerEntity> answers;

	@Override
	public String toString() {
		return "QuestionEntity [questionId=" + questionId + ", question=" + question + ", answers=" + answers + "]";
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<AnswerEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerEntity> answers) {
		this.answers = answers;
	}
	
	

}
