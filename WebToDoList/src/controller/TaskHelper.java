package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Task;

public class TaskHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ToDoList");

	public void addItem(Task task) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(task);
		em.getTransaction().commit();
		em.close();
	}

	public void cleanup() {
		emfactory.close();
	}

	public List<Task> getFilteredResults(String whereClause, String orderClause) {

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Task> typedQuery = em.createQuery("SELECT task FROM Task task" + whereClause + orderClause,
				Task.class);
		List<Task> foundTasks = typedQuery.getResultList();
		em.close();
		return foundTasks;

	}

	public void deleteTask(Task toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Task> typedQuery =em.createQuery(
				"SELECT task FROM Task task WHERE task.id = :id",
				Task.class);
		typedQuery.setParameter("id", toDelete.getId());
		Task result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public void editTask(Task toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public Task searchForTaskById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Task found = em.find(Task.class, id);
		em.close();
		return found;
	}

}
