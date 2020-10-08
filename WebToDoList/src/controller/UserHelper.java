package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class UserHelper {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ToDoList");

	public void insertUser(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<User> showAllUsers() {
		EntityManager em = emf.createEntityManager();
		List<User> allUsers = em.createQuery("SELECT user FROM User user").getResultList();
		return allUsers;
	}
}
