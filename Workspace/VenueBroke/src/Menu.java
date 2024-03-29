
import java.util.ArrayList;
import java.util.Scanner;

import Utils.FileHandler;
import customObjects.*;

// A class used as the main controller of the system. Performs the start
// function that generates lists of all venue and event data from CSV files, 
// allows the user to navigate a menu, can make event bookings at venues, 
// display venue, event and order information to the console, automatch 
// events with venues, and exit the system.
public class Menu {
	
	// Declare ArrayList names for all lists used to contain custom objects.
	private ArrayList<Event> eventList;
	private ArrayList<Venue> venueList;
	private ArrayList<Order> orderList;
	
	// Initialize scanner to allow for user input.
	private Scanner scanner = new Scanner(System.in);
	
	// Constructor for the menu class. Calls the Utils.Filehandler class to
	// generate list data for venues and event requests that is read from a
	// CSV file. Also initializes the order list as empty.
	public Menu() {
		
		eventList = FileHandler.importRequestList();
		venueList = FileHandler.importVenueList();
		orderList = new ArrayList<>();
		
	}
	
	// The start function used to start the menu interface. 
	public void start() {	
		
		// Initialize a variable to be used for user input.
		int input = 0;
		
		System.out.println("\nWelcome to Venue Matcher!");
		
		// While the user wishes to continue to interact with the system.
		// Exit does not rely on a separate boolean value, as the system
		// is exited using a return command.
		while (true) {
			
			// Print the menu selection options to the console.
			System.out.println("\n--------------------------------------------------");
			System.out.println("> Select from main menu");
			System.out.println("--------------------------------------------------");
			System.out.print("1) List current job requests\n"
								+ "2) Browse venue by category\n"
								+ "3) Search venue by name\n"
								+ "4) Auto-match events with suitable venues\n"
								+ "5) Show order summary\n"
								+ "6) Exit\n"
								+ "Please select: ");
			
			// Used to catch a NumberFormatException if the user inputs
			// a string, or other invalid entry.
			try {
				input = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e1) {
				System.out.print("\nError. Please enter an integer value. ");
			}
			
			// Generate a switch statement that can call different functions
			// depending on user input. Input is entered as an integer, with
			// each value below 7 relating to a selection.
			switch (input) {
			case 1:
				// Generates a list of job requests that may be selected by the
				// user for more information.
				listJobRequests();
				break;
			case 2:
				// Shows a list of venue categories that show a list of suitable
				// venues based on user selection. Venues may then be selected
				// to book for events.
				browseVenueByCategory();
				break;
			case 3:
				// The user may search for a venue, then choose to book a venue
				// from a list of search results for an event.
				searchVenue();
				break;
			case 4:
				// The system automatches events with venues, which the user may
				// choose to book or attempt another match. All events are given
				// suggestions, until all have been processed.
				autoMatch();
				break;
			case 5:
				// Shows a detailed summary of all of the orders placed by the user
				// that are in the system.
				showOrderSummary();
				break;
			case 6:
				// Exits the system.
				exit();
				return;
			// A default message whenever a selection is placed incorrectly.
			default:
				System.out.println("Please select a valid menu option");
				
			}	
		}
	}
		
	// Shows a list of current job requests which may be selected to print a detailed
	// version of an events information.
	public void listJobRequests() {
				
		// An array list used to store events that match the user selection.
		ArrayList<Event> tempEvents = new ArrayList<>();
		// To be initialized with user selection.
		Event tempEvent;
		// To be used for sub menu selection.
		final String menu = "eventList";
		
		// Iterate through all events in event list.
		for (Event event : eventList) {
			
			// If the event is not already booked, add it to the temporary event
			// list, and print its name.
			if (!event.isBooked()) {
				
				tempEvents.add(event);
				System.out.printf("%d) %s\n", (tempEvents.indexOf(event)+1), event.getTitle());
				
			}
		}
		
		// If the temporary event list is not empty.
		if (tempEvents.size() > 0) {
			
			// Request user input.
			System.out.print("Please Select: ");
			
			// Catch if user input is incorrect.
			try {
				
				// initialize the temporary event variable with the selected event
				tempEvent = tempEvents.get(Integer.parseInt(scanner.next()) - 1);
				
				System.out.println("--------------------------------------------------");
				
				// Display the sub menu passing in the selection made by the user.
				displaySubMenu(menu, tempEvent.getTitle());
				
			} catch (NumberFormatException e1) {
				System.out.println("Error. Please enter a number.");
			} catch (NullPointerException e2) {
				System.out.println("Error. Please enter a number.");
			} catch (ArrayIndexOutOfBoundsException e3) {
				System.out.println("Error. Please enter a number within range.");
			}	
			
		// Inform user if all events are booked, and none can be shown to select.
		} else {
			System.out.println("\nNo available job requests.");
		}
	}
	
	// Allows the user to select a category they would like to view
	// venue matches for.
	public void browseVenueByCategory() {
		
		System.out.println("--------------------------------------------------");
		System.out.println("> Select by Category");
		
		// An array list to catch distint categories.
		ArrayList<String> venueCategories = new ArrayList<>();
		// Used to pass the user selection to the next method.
		String category;
		// Used to indicate which sub menu to show to the next method.
		final String menu = "categoryList";
		// Used to determine if the user would like to continue selecting.
		boolean exit = false;
		
		// Iterate through venue list.
		for (Venue venue : venueList) {
			
			// If the venue category list does not already contain the category
			// of the current venue.
			if (!venueCategories.contains(venue.getCategory())) {
				
				// Add the category to the list.
				venueCategories.add(venue.getCategory());
				
			}
			
		}
		
		System.out.println("--------------------------------------------------");
		
		// Index used to show possible selections to user.
		int i;
		
		// Iterate through the category list, and increment the index each time.
		for (i = 0; i < venueCategories.size(); i++) {
			
			// Print the index number, and the category.
			System.out.println((i + 1) + ") " + venueCategories.get(i));
			
		}
		
		// Show user option to exit selection.
		System.out.printf("%d) Exit\n", ++i);
 		
		// While the user wishes to continue selecting.
		while (!exit) {
			
			// Request user input.
			System.out.print("Please select: ");
			
			// Receive user input and catch a NumberFormatException if invalid.
			try {
				i = (Integer.parseInt(scanner.next()) - 1);
			} catch (NumberFormatException e1) {
				System.out.println("Error. Please enter an integer value.");
			}
			
			// If the input falls within the selection range.
			if (i < venueCategories.size()) {
				
				// Initalize category variable with selection to be passed to
				// next method.
				category = (venueCategories.get(i));
				
				System.out.println("--------------------------------------------------");
			
				// Pass the menu type, and user selection to the display sub
				// menu method.
				displaySubMenu(menu, category);
				
				// Exit the selection.
				exit = true;
				
			// If the user chooses, exit the selection.
			} else if (i == venueCategories.size()) {
				
				System.out.println("Exiting...");
				exit = true;
				
			// Inform user if selection is invalid.
			} else {
				System.out.println("Error. Please enter a valid response.");
			}	
		}
	}
	
	// Is used to display sub menus for display event request as a list
	// and to display venues by category. Passes a string to determine
	// which menu to display, and a string based on a selection made
	// by the user as part of the calling method.
	public void displaySubMenu(String menu, String selection) {
		
		// Initialize an index integer.
		int i = 0;
		// Initialize a boolean value used to determine if the user would
		// like to continue selecting, or return to the main menu.
		boolean exit = false;
		
		// If the method is being called by display events, then it displays 
		// detailed information of an event selected as part of the calling
		// method.
		if (menu == "eventList") {
			
			//Iterate through all events in list.
			for (Event event : eventList) {
				
				// If the title of the current event matches the selection
				// made by the user, print its details, then exit for loop.
				if (event.getTitle().equals(selection)) {
					
					event.printDetails();
					continue;
					
				}
			}
			
		// If the method is being called by display venues by category,
		// display a list of venue categories that can be selecting by
		// the user to show venues that fit the category that was selected
		// by the user as part of the calling method. The venues can then 
		// be selected to book events.
		} else if (menu == "categoryList") {
			
			// Initialize input variable.
			int input = 0;
			
			// While the user wishes to continue selecting venues.
			while (!exit) {
				
				// An array list used to store venue information that matches
				// the user selection.
				ArrayList<Venue> tempVenues = new ArrayList<>();
				// A venue object that is initialized with the venue that is 
				// selected by the user.
				Venue tempVenue;
				
				i = 0;
				
				// Interate through venues in venue list.
				for (Venue venue : venueList) {
						
					// If the category of the current venue matches the user selection.
					if (venue.getCategory().equals(selection)) {
						
						// Print the venue name to the console, with a number to indicate
						// their selection.
						System.out.printf("%d) %-10s\n", (i+1), venue.getClient());
						tempVenues.add(venue);
						
					}
					
					i++;
				}
				
				// Print an option for the user to exit the selection and return to the menu.
				System.out.printf("%d) Go to main menu", (tempVenues.size()+1));
				
				// Request user input.
				System.out.print("\nPlease select: ");
				
				// Catch a NumberFormatException if user does not enter an integer.
				try {
					input = Integer.parseInt(scanner.next()) - 1;
				} catch (NumberFormatException e1) {
					System.out.println("Error. Please enter an integer value. ");
				}
				
				// Check if the input is within the range of selections.
				if (input < tempVenues.size()) {
					
					// Get the venue from the venue list using the user input, and 
					// assign it to temp venue.
					tempVenue = tempVenues.get(input);
					System.out.println("--------------------------------------------------");
					
					// Print the venue information to the console.
					System.out.printf("> %s\nCapacity: %s\nCategory: %s\nSuitable for: %s\n", 
										tempVenue.getClient(), tempVenue.getCapacity(), 
										tempVenue.getCategory(), tempVenue.suitableForToString());
					System.out.println("--------------------------------------------------");
					
					// Show pricing information and ask if user would like to proceed
					// with the booking.
					System.out.printf("1) Hire for $%d2/hour\n2) Back to venue list", 
										tempVenue.getHourlyPrice());
					System.out.print("\nPlease select: ");
					
					// Receive user input. Catch NumberFormatException if entry not integer.
					try {
						input = Integer.parseInt(scanner.next());
					} catch (NumberFormatException e1) {
						System.out.println("Error. Please enter an integer value. ");
					}
					
					// If the input is 1, proceed with the booking and pass the venue information
					// to the following method. Set exit to true.
					if (input == 1) {
						
						exit = bookVenue(tempVenue);
					
					// If the user selects 2, do not proceed with the booking.
					} else if (input == 2) {
						
						exit = true;
					
					// Otherwise, inform user entry is invalid.
					} else {
						
						System.out.println("Input is invalid.");
						
					}
					
				// User to exit when user is selecting venue from list.
				} else if (input == tempVenues.size()) {
					System.out.println("Returning to main menu.");
					exit = true;					
				// Inform user input is invalid.
				} else {
					System.out.println("Input is invalid.");
				}	
			}
		// If the menu selection passed from calling method is wrong (unlikely).
		} else {
			System.out.println("Error. Sub menu selection issue.");
		}
	}
	
	// Used to book an event to a venue, with the venue selection being passed
	// from a previous method. Can be used in different menu selection methods,
	// and is designed to be interchangeable.
	public boolean bookVenue(Venue venue) throws NumberFormatException {
		
		// Declare variables for event selection, duration, date, and time.
		Event tempEvent;
		int duration;
		String date;
		String time;
		// Variable for user input.
		String input;
		
		// Request hiring details for event from user.
		System.out.println("Hiring Details");
		// Request duration and initialize duration variable with input.
		System.out.print("Please enter number of hours: ");
		duration = Integer.parseInt(scanner.next());
		// Request date and initialize date variable with input.
		System.out.print("Please enter date: ");
		date = scanner.next();
		// Request time and initialize time variable with input.
		System.out.print("Please enter time: ");
		time = scanner.next();
			
		// Iterate through event list.
		for (Event event : eventList) {
			
			// Check if current event is a match for details entered by the user.
			if (event.getDuration() == duration 
					&& event.getDate().equals(date) 
					&& event.getTime().equals(time)) {
				
				// Initialize temp event with current event based on user selection.
				tempEvent = event;
				// Inform user of match.
				System.out.println("Event match!");
				
				// Check if the event is not already booked.
				if (!tempEvent.isBooked()) {
					
					// Check if the venue is suitable for the event.
					if (checkSuitability(tempEvent, venue)) {
						
						// Check if the venue is not already booked on that date.
						if (checkAvailability(venue, tempEvent)) {
							
							// Print the details of the event match.
							System.out.printf("Event name: %s\nArtist name: %s\nRequestor name: %s\n", 
									tempEvent.getTitle(), tempEvent.getArtist(), tempEvent.getClient());
				
							// Request the user to confirm the order.
							System.out.print("Confirm order (y/n): ");
							// Receive user input.
							input = scanner.next();
							
							// If the user input is 'y'.
							if (input.equals("y")) {
								
								// Proceed with booking by initializing a new order object that contains
								// the event and venue selected by the user, and set the comission rate
								// to the maximum at this stage.
								orderList.add(new Order(event, venue, calculateComissionRate()));
								
								// Check if there are more than 1 order in the order list.
								if (orderList.size() > 1) {
									
									// Iterate through order list.
									for (Order order : orderList) {
										
										// Update each order accordingly.
										order.setComissionRate(calculateComissionRate());
										
									}
								}
								
								// Notify user that the booking was successful.
								System.out.println("Booking successful.");
								
								// Return true to the calling method.
								return true;
								
							// If the user input is 'n'.
							} else if (input.equals("n")) {
								
								// Inform user that the booking process has been cancelled.
								System.out.println("Booking cancelled...");
								// Return false to the calling method.
								return false;
							
							// Inform the user that their input was invalid.
							} else {
								System.out.println("Error. Please select a valid response.");
							}
						
						// Inform user that venue is already booked on date of event.
						} else {
							System.out.println("Venue is not available on that date.");
						}
					
					// Inform user that venue is not suitable for event conditions.
					} else {
						System.out.println("Venue is not suitable for event type.");
					}
				
				// Inform user that event is already booked, then exit the selection.
				} else {
					System.out.println("Event is already booked to a venue.");
					break;
				}
			} 
		}
			
		// Inform user that there was no match for the event details that they entered.
		System.out.println("No event match. Please review entry and try again.");
		return false;
		
	}
	
	// Allows the user to search for a venue by name, and select it to be booked for an
	// event.
	public void searchVenue() {
		
		// Used to receive user input as a string to be used to search for a venue.
		String venueClient;
		// A list to build with venue matches based on user input.
		ArrayList<Venue> tempVenues = new ArrayList<>();
		// Index used to indicate selections to user.
		int i = 0;
		// Used to recieve input from the user.
		int input = 0;
		// Used to determine if the user would like to continue making selections.
		boolean exit = false;
		
		//Request input from user.
		System.out.print("Please enter a venue name: ");
		// Receive input as string.
		venueClient = scanner.next();
		
		// Iterate through venue list.
		for (Venue venue : venueList) {
			
			// If any part of the current venue name matches the user input.
			if (venue.getClient().toLowerCase().contains(venueClient)) {
				
				// Add the venue to the temporary venue list.
				tempVenues.add(venue);
				
			}
		}
		
		// Iterate through the temporary venue list.
		for (Venue venue : tempVenues) {
			
			// Print a number used for selection, and the venue name.
			System.out.printf("%d) %s\n", (i+1), venue.getClient());
			// Increment the indexing variable.
			i++;

		}
		
		// Indicate user selection to return to the main menu.
		System.out.printf("%d) Go to main menu\n", ++i);
		
		// While the user wishes to continue making selections.
		while (!exit) {
			
			// Request user input.
			System.out.print("Please select: ");
		
			// Recieve user input and minus by 1. Catch a NumberFormatException
			// if entry is invalid.
			try {
				input = (Integer.parseInt(scanner.next()) - 1);
			} catch(NumberFormatException e1) {
				System.out.println("Error. Please enter an integer. ");
			}
			
			// If the input is within the selection range.
			if (input < tempVenues.size()) {
				
				// Proceed with venue booking by passing the user selection to the
				// next method.
				bookVenue(tempVenues.get(input));
				// Exit the selection.
				exit = true;
				
			// If the user chooses to, exit the selection.
			} else if (input == tempVenues.size()) {
				
				System.out.println("Exiting...");
				exit = true;
			
			// Inform user if input is invalid.
			} else { 
				System.out.println("Please enter a valid response.");	
			}
		}
	}
	
	// Used to calculate the comission rate of an event, based on the number of orders
		// already placed in the system.
		public double calculateComissionRate() {
			
			// Standard rate is 10% (0.1 * 100).
			double rate = 0.1;
			
			// If there is greater than 1 order in the order list.
			if (orderList.size() > 1) {
				
				// Set rate to 9% (0.09 * 100)
				rate = 0.09;
				
			}
			
			// Return the rate to the calling method.
			return rate;
			
		}
	
	// Used to check if a venue is suitable for the specific requirements of an event.
	// Recieves and event and a venue from the calling method to compare.
	public boolean checkSuitability (Event event, Venue venue) {
		
		// If the venue selected suitable for conditions for the event type, and has a large
		// enough capacity to hold the target attendees.
		if (venue.suitableForToString().toLowerCase().contains(event.getType().toLowerCase()) 
				&& event.getTarget() <= venue.getCapacity()) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	// Check if a venue is available on the day of an event, or if it is already
	// booked. Recieves a venue and an event from the calling method.
	public boolean checkAvailability(Venue venue, Event event) {
		
		// Iterate through the order list.
		for (Order order : orderList) {
			
			// If the current order venue name matches the one to be checked.
			if (order.getVenue().getClient().equals(venue.getClient())) {
				
				// If the date of the event to be checked matches the date of 
				// event of the current order.
				if (order.getEvent().getDate().equals(event.getDate())) {
					
					// Inform the user that it is not available on that day.
					return false;
					
				// If there is not match.
				} else {
					
					// Inform the user that it is available on that day.
					return true;
					
				}	
			}
		}
		
		// If none of the if statements match, the venue must be available.
		return true;
	}
	
	// Matches all events in event list with suitable venues that are available
	// to book on the date of the event, and allows the user to choose to book, 
	// or attempt to find a different match.
	public void autoMatch() {
		
		// Used to recieve user input.
		String input = "";
		
		// Iterate through each event in the event list.
		for (Event event : eventList) {
			
			// If the current event is not booked.
			if (!event.isBooked()) {
				
				// Iterate through each venue in the venue list.
				for (Venue venue : venueList) {
					
					// Check if event has been booked since checking previously.
					if (!event.isBooked()) {
						
						// Check if venue available to book on that date.
						if (checkAvailability(venue, event)) {
							
							// Check that venue is suitable for event conditions.
							if (checkSuitability(event, venue)) {
								
								// Print details of the event and the venue.
								System.out.printf("\nEVENT DETAILS\n"
													+ "Event name: %s\n"
													+ "Event client: %s\n"
													+ "Event date: %s\n"
													+ "Event time: %s\n"
													+ "Event duration: %d\n"
													+ "Event target: %d\n"
													+ "\nVENUE DETAILS\n"
													+ "Venue name: %s\n"
													+ "Venue type: %s\n"
													+ "Venue capacity: %d\n\n", 
													event.getTitle(), event.getClient(),
													event.getDate(), event.getTime(),
													event.getDuration(), event.getTarget(), 
													venue.getClient(), venue.getCategory(),
													venue.getCapacity());
								
								// Reset input.
								input = "";
								
								// While input does not equal 'n'.
								while (!input.equals("n")) {
									
									// Request user input.
									System.out.print("Would you like to continue with booking (y/n): ");
									// Recieve user input.
									input = scanner.next();
									
									// If input equals 'y'.
									if (input.equals("y")) {
										
										// Proceed with booking. Add new order to the order list, with the 
										// event and venue selected, and calculate the comission rate.
										orderList.add(new Order(event, venue, calculateComissionRate()));
										
										// Inform user the event has been booked succesfully.
										System.out.println("Event booked succesfully.");
										break;
									
									// If the input equals 'n'.
									} else if (input.equals("n")) {
										
										// Inform user event not booked and continue. Will exit while loop.
										System.out.println("Event not booked.");
										continue;
										
									// Inform user input was invalid.
									} else {
										System.out.println("Error. Please enter a valid response.");
									}
								}
							// Inform user venue is not suitable for event conditions.
							} else {
								System.out.println("Venue is not suitable for event.");
							}
						// Inform user that venue is unavailable on that date.
						} else {
							System.out.println("Venue is not available to book.");
							continue;
						}
					// Inform user that event has already been booked to a venue.	
					} else {
						continue;
					}	
				}	
			// Inform user that event has already been booked to a venue.
			} else {
				continue;
			}
		}
	}
	
	// Shows a detailed list of all orders that have been placed on the system.
	public void showOrderSummary() {
		
		// Check if there is at least one order.
		if (orderList.size() > 0) {
			
			System.out.println();
			
			// Iterate through order list.
			for (Order order : orderList) {
				
				// Print an indexing number for the job.
				System.out.printf("Job #%d\n", (orderList.indexOf(order)+1));
				// Display the order information to the console.
				order.display();
				
			}
		// If there are no orders, inform user.
		} else {
			System.out.println("No orders placed.");
		}
	}
	
	// Exits the system. Called when user has completed their tasks.
	// Will also exit the switch statement, thus closing the menu.start
	// process.
	public void exit() {
		
		// Inform the user of the system exit.
		System.out.println("Exiting system... Goodbye!");
		// Close the scanner.
		scanner.close();
		
	}
}
