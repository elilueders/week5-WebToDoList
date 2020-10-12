package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "COMPLETED")
	private int completed;
	@Column(name = "DUE")
	private String due;
	@Column(name = "DESCRIPTION")
	private String description;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private User user;

	public Task() {
		super();
	}


	public Task(String due, String description) {
		this.due = due;
		this.description = description;
	}

	public Task(String due, String description, User user) {
		this.due = due;
		this.description = description;
		this.user = user;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String returnTaskDetail() {
		return completed + " | " + due + " | " + description;
	}

	public String getTaskDetailFormatted() {
		String result = "[";
		if (completed == 1)
			result += "X";
		else
			result += " ";
		result += "] " + due + " | " + description;
		return result;
	}

	public String getCompleteHTML() {
		String result=" ";
		if (completed == 1)
			result = "&#10003;";
		return result;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", completed=" + completed + ", due=" + due + ", description=" + description
				+ ", user=" + user + "]";
	}
}
