package com.OneToMany;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sun.xml.bind.util.Which;

public class OneToMany {

	SessionFactory factory = null;
	static Scanner scanner = null;

	public OneToMany() {
		factory = new Configuration().configure().buildSessionFactory();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {

		OneToMany oneToMany = new OneToMany();
//		//oneToMany.insertQuestion();
//		oneToMany.getAllRecord();

		while (true) {
			System.out.println(
					"1)Insert question and answer \n2)List all question and answers \n3)Delete question \n4)Update question \n5)Add Answer");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				oneToMany.insertQuestion();
				break;
			case 2:
				oneToMany.getAllRecord();
				break;
			case 3:
				oneToMany.delete();
				break;
			case 4:
				oneToMany.update();
				break;
			case 5:
				oneToMany.addAnswer();
				break;
			case 6:
				break;
			default:
				System.exit(0);
			}

		}
	}

	public void insertQuestion() {
		scanner.nextLine();
		QuestionEntity entity = new QuestionEntity();
		System.out.println("Enter Question ?");
		entity.setQuestion(scanner.nextLine());
		AnswerEntity answer = null;
		List<AnswerEntity> list = new ArrayList<AnswerEntity>();
		while (true) {
			scanner.nextLine();
			System.out.println("Enter answer");
			answer = new AnswerEntity();
			answer.setAnswer(scanner.nextLine());
			answer.setQuestion(entity);
			list.add(answer);
			System.out.println("Do you want to add more answers? Y for yes ");
			char ch = scanner.next().charAt(0);
			if (ch == 'y' || ch == 'Y') {

			} else {
				break;
			}
		}
		entity.setAnswers(list);
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(entity);
		transaction.commit();
	}

	public void getAllRecord() {
		Session session = factory.openSession();
		List<QuestionEntity> questionEntities = session.createQuery("From QuestionEntity", QuestionEntity.class)
				.getResultList();
		System.out.println("Que Id\t|\tQuestion\t|\tAnswer");
		List<String> answer = new ArrayList<String>();
		System.out.println("-------------------------------------------------------------");
		for (QuestionEntity questionEntity : questionEntities) {
			for (int i = 0; i < questionEntity.getAnswers().size(); i++) {
				System.out.println(questionEntity.getQuestionId() + "\t|\t" + questionEntity.getQuestion() + "\t|\t"
						+ questionEntity.getAnswers().get(i).getAnswer());
			}
		}
		System.out.println();
		session.close();
	}

	public void delete() {
		System.out.println("Enter QuestionId which you wanna delete record!");
		int id = scanner.nextInt();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		QuestionEntity question = session.get(QuestionEntity.class, id);
		session.delete(question);
		tx.commit();
		System.out.println("deleted successfully");
	}

	public void update() {
		System.out.println("Enter QuestionId which tou wanna update record");
		int id = scanner.nextInt();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		QuestionEntity question = session.get(QuestionEntity.class, id);
		System.out.println("your current Question:" + question.question);
		System.out.println("If you wanna update your question ! 1 for yes");
		int choice = scanner.nextInt();
		if (choice == 1) {
			scanner.nextLine();
			System.out.println("Enter new Question!");
			String newQuestion = scanner.nextLine();
			question.setQuestion(newQuestion);
			List<AnswerEntity> answers = new ArrayList<AnswerEntity>();
			for (AnswerEntity answer : question.getAnswers()) {
				answers.add(answer);
			}
			System.out.println(answers);
			for (int i = 0; i < answers.size(); i++) {
				AnswerEntity answer = session.get(AnswerEntity.class, answers.get(i).getAnswerId());
				System.out.println("your Answer is: " + answer.getAnswer());
				System.out.println("you wanna update this press 1!");
				int c = scanner.nextInt();
				scanner.nextLine();
				if (c == 1) {
					System.out.println("Enter new Answer!");
					answer.setAnswer(scanner.nextLine());
					session.update(answer);
				}
			}
			System.out.println(answers);
			question.setAnswers(answers);
		}
		session.update(question);
		tx.commit();
	}

	public void addAnswer() {
		System.out.println("Enter QuestionId in which you wanna Add Answer");
		int id = scanner.nextInt();
		scanner.nextLine();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		QuestionEntity question = session.get(QuestionEntity.class, id);
		List<AnswerEntity> answers =new ArrayList<AnswerEntity>();
		while (true) {
			
			System.out.println("Enter your Answer");
			AnswerEntity answerEntity = new AnswerEntity();
			String addAnswer = scanner.nextLine();
			answerEntity.setAnswer(addAnswer);
			answers.add(answerEntity);
			answerEntity.setQuestion(question);
			System.out.println("Do you want to add more answers? Press 1 for yes");
			int ch=scanner.nextInt();
			scanner.nextLine();
			if(ch!=1)
				break;
		}
		question.setAnswers(answers);
		session.update(question);
		tx.commit();
	}

}
