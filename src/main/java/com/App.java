package com;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Entity.AddressEntity;
import com.Entity.EmployeeEntity;

public class App {
	SessionFactory sf = null;
	static Scanner scr = new Scanner(System.in);

	public App() {
		sf = new Configuration().configure().buildSessionFactory();
	}

	public static void main(String[] args) {
		App app = new App();

		int choice = -1;

		while (true) {
			System.out.println(
					"1 For add Stock\n2 For List Stock\n3 For Update Stock\n4 For Delete Stock\n5 for GetStockByID\n0 for Exit\nEnter choice");
			choice = scr.nextInt();

			switch (choice) {
			case 1:
				app.insertEmplyee();
				break;
			case 2:
				app.getAllRecord();
				break;
			case 3:
				app.updateEmployee();
				break;
			case 4:
				app.deleteEmployee();
				break;
			case 5:
				app.getById();
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("Invalid Choice!!!!!");
			}
		}
	}

	public void insertEmplyee() {

		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setName("kashayap");

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setStreet("idher hi");
		addressEntity.setCity("ahmedabad");
		addressEntity.setPincode(7985);

		employeeEntity.setAddressEntity(addressEntity);
		addressEntity.setEmployeeEntity(employeeEntity);

		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(employeeEntity);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
	}

	public void getAllRecord() {
		Session session = sf.openSession();
		List<EmployeeEntity> employeeEntities = session.createQuery("From EmployeeEntity", EmployeeEntity.class)
				.getResultList();
		System.out.println("EmployeeId\tName\t\tstreet\t\tCity\t\tpincode");
		System.out.println("-----------------------------------------------------------------------------------");
		for (EmployeeEntity employeeEntity : employeeEntities) {
			System.out.println(employeeEntity.getEmployeeId() + "\t\t" + employeeEntity.getName() + "\t\t"
					+ employeeEntity.getAddressEntity().getStreet() + "\t" + employeeEntity.getAddressEntity().getCity()
					+ "\t" + employeeEntity.getAddressEntity().getPincode());
		}
		System.out.println("-----------------------------------------------------------------------------------");
		session.close();
	}

	public void deleteEmployee() {
		System.out.println("enter Employee id which you want to delete!");
		int deleteId = scr.nextInt();
		Session session = sf.openSession();
		EmployeeEntity entity = session.get(EmployeeEntity.class, deleteId);
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(entity);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		session.close();
	}

	void updateEmployee() {
		System.out.println("Enter EmployeeId");
		int userId = scr.nextInt();
		Session session = sf.openSession();
		EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, userId);
		AddressEntity addressEntity = session.get(AddressEntity.class, userId);
		if (employeeEntity == null) {
			System.out.println("InvalidUserId");
		} else {

			Transaction tx = session.beginTransaction();

			System.out.println("Existing value : Employee Name : " + employeeEntity.getName());
			System.out.println("Do you want to update Stock Name?1 for yes?");
			int userChoice = scr.nextInt();
			if (userChoice == 1) {
				System.out.println("Enter new Employee Name");
				employeeEntity.setName(scr.next());
			}

			System.out.println("Existing value : Street : " + employeeEntity.getAddressEntity().getStreet());
			System.out.println("Do you want to update Street?1 for yes?");
			userChoice = scr.nextInt();
			if (userChoice == 1) {
				System.out.println("Enter new  Street");
				scr.next();
				addressEntity.setStreet(scr.nextLine());
				scr.next();
			}

			System.out.println("Existing value : city : " + employeeEntity.getAddressEntity().getCity());
			System.out.println("Do you want to update city?1 for yes?");
			userChoice = scr.nextInt();
			if (userChoice == 1) {
				System.out.println("Enter new city");
				scr.next();
				addressEntity.setCity(scr.next());
				scr.next();
			}
			System.out.println("Existing value : pincode : " + employeeEntity.getAddressEntity().getPincode());
			System.out.println("Do you want to update pincode?1 for yes?");
			userChoice = scr.nextInt();
			if (userChoice == 1) {
				System.out.println("Enter new pincode");
				
				addressEntity.setPincode(scr.nextInt());
			}
			employeeEntity.setAddressEntity(addressEntity);

			try {
				session.update(employeeEntity);//
				tx.commit();
				System.out.println("User updated.. ");

			} catch (Exception e) {
				tx.rollback();
				System.out.println("Sometinh went wrong !!! " + e.getMessage());
			}

		}
		session.close();

	}

	public void getById() {
		System.out.println("Enter UserId");
		int userId = scr.nextInt();
		Session session = sf.openSession();
		EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, userId);
		System.out.println("EmployeeId\tName\t\tstreet\t\tCity\t\tpincode");
		System.out.println("-----------------------------------------------------------------------------------");

		System.out.println(employeeEntity.getEmployeeId() + "\t\t" + employeeEntity.getName() + "\t\t"
				+ employeeEntity.getAddressEntity().getStreet() + "\t" + employeeEntity.getAddressEntity().getCity()
				+ "\t" + employeeEntity.getAddressEntity().getPincode());
		System.out.println("-----------------------------------------------------------------------------------");
		session.close();
	}

}
