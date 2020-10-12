package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int id;
	@Column(name = "USER_NAME")
	private String userName;
	

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_tasks",
			joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
			inverseJoinColumns = { @JoinColumn(name = "TASK_ID", referencedColumnName = "ID", unique = true)}
			)
	private List<Task> userTaskList;

	public User() {
		super();
	}

	public User(int id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public User(String userName) {
		super();
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Task> getUserTaskList() {
		return userTaskList;
	}

	public void setUserTaskList(List<Task> userTaskList) {
		this.userTaskList = userTaskList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userTaskList=" + userTaskList + "]";
	}

}
