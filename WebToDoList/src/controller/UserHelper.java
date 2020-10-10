package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.User;

public class UserHelper {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ToDoList");

	public void addUser(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(User toDelete) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<User> typedQuery = em.createQuery(
				"SELECT user FROM User user WHERE user.id = :selectedId", 
				User.class);
		typedQuery.setParameter("selectedId", toDelete.getId());
		typedQuery.setMaxResults(1);
		
		User result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public User searchForUserById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User found = em.find(User.class, id);
		em.close();
		return found;
	}

	@SuppressWarnings("unchecked")
	public List<User> showAllUsers() {
		EntityManager em = emf.createEntityManager();
		List<User> allUsers = em.createQuery("SELECT user FROM User user").getResultList();
		return allUsers;
	}
}
