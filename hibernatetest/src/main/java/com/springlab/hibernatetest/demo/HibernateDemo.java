package com.springlab.hibernatetest.demo;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.springlab.hibernatetest.entities.Student;

public class HibernateDemo {
	
	public static void main(String[] args) {
		Student student1 = new Student("Ramesh", "Fadetare", "a@gmail.com", 
				(new GregorianCalendar(1980, 1, 20)).getTime());
		Student student2 = new Student("Gildong", "Hong", "b@gmail.com", 
				(new GregorianCalendar(1900, 7, 2)).getTime());
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			
			session.persist(student1);
			System.out.println(">>> 학생 레코드 추가 ID: " + student1.getId());
			session.persist(student2);
			System.out.println(">>> 학생 레코드 추가 ID: " + student2.getId());
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Student> students = session.createQuery("select s from Student s", Student.class).list();
			students.forEach(s -> System.out.println(">>> " + s.toString()));
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}
	
}
