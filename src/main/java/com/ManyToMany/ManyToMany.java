package com.ManyToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;

public class ManyToMany {
	SessionFactory sf = null;
	static Scanner scanner=null;
	public ManyToMany() {
		sf= new Configuration().configure().buildSessionFactory();
		scanner = new Scanner(System.in);
	}

	public void insertDeveloper() {
		scanner.nextLine();
		DeveloperEntity developer = new DeveloperEntity();
		System.out.println("Enter Developer Name");
		developer.setName(scanner.nextLine());
		Session session = sf.openSession();
		List<ProjectEntity> projects=session.createQuery("from ProjectEntity",ProjectEntity.class).getResultList();
		for(int i=0 ;i<projects.size();i++ )
		{
			System.out.println("Project Name :- " +projects.get(i).getProject());
			System.out.println("Project Id :- "+projects.get(i).getProjectId());
		}
		int i=0;
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		while(true && i<projects.size())
		{
			System.out.println("Enter Project Id :-");
			ProjectEntity  project=session.get(ProjectEntity.class, scanner.nextInt());
			list.add(project);
			List<DeveloperEntity> developers=new ArrayList<DeveloperEntity>();
			developers.add(developer);
			project.setDevelopers(developers);
			Transaction tx =session.beginTransaction();
			session.save(project);
			tx.commit();
			System.out.println("Do you want to add more Projects?If Yes press 1");
			int choice=scanner.nextInt();
			if(choice!=1)
				break;
			i++;
		}
		Transaction tx = session.beginTransaction();
		session.save(developer);
		tx.commit();
	}

	public void insertProject() {
		scanner.nextLine();
		ProjectEntity project = new ProjectEntity();
		System.out.println("Enter ProjectName");
		project.setProject(scanner.nextLine());
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		session.save(project);
		tx.commit();
	}
	public static void main(String[] args) {
		ManyToMany obj = new ManyToMany();
		
		while (true) {
			System.out.println("1)Insert Developer \n2)Insert Project \n3)List All Records");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				obj.insertDeveloper();
				break;
			case 2:
				obj.insertProject();
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				break;
			}
		}

	}
}
