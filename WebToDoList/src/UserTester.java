import java.util.List;

import controller.UserHelper;
import model.User;

public class UserTester {

	public static void main(String[] args) {
		User adam = new User("Adam");
		UserHelper uh = new UserHelper();
		uh.addUser(adam);
		List<User> allUsers = uh.showAllUsers();
		for (User user : allUsers) {
			System.out.println(user.toString());
		}
	}

}
