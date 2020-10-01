import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.TaskHelper;
import model.Task;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static TaskHelper th = new TaskHelper();

	public static void addATask() {
		//TODO get < 43 chars
		System.out.printf("%61s","Enter the task: ");
		String description = in.nextLine();
		//TODO get valid date
		System.out.printf("%61s","Enter a due date (mm/dd/yy): ");
		String due = in.nextLine();
		
		Task toAdd = new Task(due, description);
		th.addItem(toAdd);
	}
	public static void deleteATask() {
		List<Task> foundTasks = returnTaskList();
		printToDoList(foundTasks);
		System.out.printf("%47s","Select an ID to delete");
		Task toDelete = foundTasks.get(promptUserForInt()-1);
	
		th.deleteTask(toDelete);
	}
	public static void editATask() {
		List<Task> foundTasks = returnTaskList();
		printToDoList(foundTasks);
		System.out.printf("%47s","Select an ID to edit");
		Task toEdit = foundTasks.get(promptUserForInt()-1);
		System.out.println("Task to edit ~ "+toEdit.getTaskDetailFormatted());
		System.out.println("| 1-Incomplete | 2-Complete |");
		System.out.printf("%47s","");
		
		int choice = promptUserForInt();
		int newComplete=0;
		if (choice==2)
			newComplete=1;
		toEdit.setCompleted(newComplete);
		System.out.printf("%61s","Enter new due date (mm/dd/yy or 0 for no change): ");
		String newDue = in.nextLine();
		if (!newDue.equals("0"))
			toEdit.setDue(newDue);
		System.out.printf("%61s","Enter new task (0 for no change): ");
		String newDescription = in.nextLine();
		if (!newDescription.equals("0")) 
			toEdit.setDescription(newDescription);
		th.editTask(toEdit);
	}
	public static void markATaskComplete() {	List<Task> foundTasks = returnTaskList();
	printToDoList(foundTasks);
	System.out.printf("%47s","Select an ID to check off");
	Task toEdit = foundTasks.get(promptUserForInt()-1);
	toEdit.setCompleted(1);
	th.editTask(toEdit);
	}
	public static List<Task> returnTaskList() {
		System.out.println("\n| 1-Incomplete | 2-Complete | 3-All |");
		System.out.printf("%47s","How would you like your tasks FILTERED?");
		int choice = promptUserForInt();
		String filterBy;
		switch (choice) {
		case 1:
			filterBy = " WHERE task.completed = 0";
			break;
		case 2:
			filterBy = " WHERE task.completed = 1";
			break;
		default:
			filterBy = "";
			break;
		}
		System.out.println("\n| 1-Date Due | 2-Task(A-Z) | 3-Order Added |");
		System.out.printf("%47s","How would you like your tasks SORTED?");
		choice = promptUserForInt();
		String sortedBy;
		switch (choice) {
		case 1:
			sortedBy = " ORDER BY task.completed,task.due";
			break;
		case 2:
			sortedBy = " ORDER BY task.description";
			break;
		default:
			sortedBy = " ORDER BY task.id";
			break;
		}

		
		List<Task> foundTasks = th.getFilteredResults(filterBy,sortedBy);
		return foundTasks;
	}
	public static void printToDoList(List<Task> foundTasks) {
		if (foundTasks.isEmpty()) {
			System.err.println("!! No tasks meet your criteria !!");
		}else {
			System.out.println("=============================================================");
			System.out.println("                      T O   D O   L I S T");
			System.out.println("                      ^^^^^^^^^^^^^^^^^^^");
			System.out.println("=============================================================");
			System.out.println("ID   | D U E    | T A S K");
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			for (int i = 0; i < foundTasks.size(); i++) {
				System.out.printf("%-3d",i+1);
				System.out.println(foundTasks.get(i).getTaskDetailFormatted());
				
			}
			System.out.println("=============================================================");
		}
		
	}
	
	public static int promptUserForInt() {
		boolean isInvalid = true;
		int result=0;
		do {
			try {
				System.out.print(" ~ ToDoList ~ ");
				result = in.nextInt();
				isInvalid = false;
				in.nextLine();
			} catch (InputMismatchException e) {
				System.err.print("Enter a number only...");
				in.nextLine();
			}
			
		} while (isInvalid);
		return result;
	}
	public static void main(String[] args) {
		runMenu();
	}
	private static void runMenu() {
		boolean repeat = true;
		// welcome banner
		System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxx           xxxxxxxxx      xxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxx   xxxxx    xxxx   xx   xxxxx    xxxxxxxxxxxxx\nxxxxxxxxxxxxxxx   xxxx  xx  xxx   xxxx   xx  xx  xxxxxxxxxxxx\nxxxxxxxxxxxxxxx   xxxx  xx  xxx   xxxxx   x  xx  xxxxxxxxxxxx\nxxxxxxxxxxxxxxx   xxxx  xx  xxx   xxxxx   x  xx  xxxxxxxxxxxx\nxxxxxxxxxxxxxxx   xxxxx    xxxx   xxxxx   xx    xxxxxxxxxxxxx\nxxxxxxxxxxxxxxx   xxxxxxxxxxxxx   xxxx    xxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   xxx   xxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx      xxxxxxxxxxxxxxxxxxxxxxxx\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n     W E L C O M E   T O   T H E   T O   D O   L I S T !     ");
		while (repeat) {
			System.out.println("\n| 1-Check Off | 2-Add | 3-Edit | 4-Delete | 5-View | 6-EXIT |");
			System.out.printf("%47s","What do you want to do?");
			
			int choice = promptUserForInt();
			
			switch (choice) {
			case 1:
				markATaskComplete();
				break;
			case 2:
				addATask();
				break;
			case 3:
				editATask();
				break;
			case 4:
				deleteATask();
				break;
			case 5:
				printToDoList(returnTaskList());
				break;
			case 6:
				th.cleanup();
				System.out.println("\nBye for now!");
				repeat = false;
				break;
			default:
				break;
			}
		}
	}
}
