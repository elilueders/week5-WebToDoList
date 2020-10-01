package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	public Task() {
		super();
	}

	public Task(String due, String description) {
		this.due = due;
		this.description = description;
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

}
