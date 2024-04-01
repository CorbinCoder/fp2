
import tests.*;
import java.util.Scanner;

// The main class for the program. Initializes the menu class which 
// is used as the controller.
public class Main {
	
	// Declare a menu variable.
	private static Menu menu;
	// Initialize a scanner used for input.
	private static Scanner in = new Scanner(System.in);
	// Declare variable used for input.
	private static int input;
	
	// Main method.
	public static void main(String args[]) {
		
		// While the user wishes to remain in the program.
		while (true) {
			
			// Initialize input variable.
			input = 0;
			
			// Display menu options to user and request input.
			System.out.print("\n> What would you like to do?\n"
					+"------------------------------\n"
					+ "1) Start VenueBroke\n"
					+ "2) Perform tests\n"
					+ "3) EXIT\n"
					+ "Please select: ");

			// Receive input.
			input = Integer.parseInt(in.next());
			System.out.println();
			
			// Switch statement based on user input.
			switch (input) {
			case 1:
				// Initialize menu variable.
				menu = new Menu();
				// Start the menu. Displays program menu.
				menu.start();
				break;
			case 2:
				// Initialize a new test runner.
				TestRunner testRunner = new TestRunner();
				// Runs a test runner then shows results to user.
				testRunner.run();
				break;
			case 3:
				// Inform user that system is being exited.
				System.out.println("Exiting system.");
				// Close scanner.
				in.close();
				return;
			default:
				// Inform user of invalid input.
				System.out.println("Input is invalid.");
			}	
		}
	}
}