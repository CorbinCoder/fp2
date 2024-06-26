package customObjects;

import java.time.*;

import interfaces.Bookable;
import utils.FileHandler;

// Class used to define an Event object. Events are performances
// that are sold with the intention of attendees purchasing tickets.
// Events may be booked to a venue as part of an order.
// Extends the Client class, and implements the Bookable interface.
public class Event extends Client implements Bookable {
	
	// Declare event variables client name, event title, artist name,
	// event date, time, target attendees, duration, type, and category.
	// isBooked is used as part of the Bookable interface.

	private String title;
	private String artist;
	private LocalDate date;
	private LocalTime time;
	private int target;
	private int duration;
	private String type;
	private String category;
	private boolean isBooked;
	
	// Constructor for an Event object. Uses super to define abstract class 
	// variables and defines all other variables when constructed. Does not 
	// require isBooked to be passed from the calling method, as it is defined 
	// by the constructor.
	public Event(String client, String title, String artist, 
					LocalDate date, LocalTime time, int target, 
					int duration, String type, String category) {
		super(client);
		this.title = title;
		this.artist = artist;
		this.date = date;
		this.time = time;
		this.target = target;
		this.duration = duration;
		this.type = type;
		this.category = category;
		this.isBooked = false;
		formatDateTime();
	}
	
	// Formats the date/time variables to accept the stream from the
	// CSV file.
	public void formatDateTime() {
		
		this.date.format(FileHandler.dateFormatter);
		this.time.format(FileHandler.timeFormatter);
		
	}
	
	// A method to return the event details as a single line string. 
	// Overrides the built-in toString method.
	@Override
	public String toString() {
		
		// Temporary string to build with event details.
		String temp = "";
		
		// Try/Catch to catch NullPointerException if one
		// or more event details are null.
		try {
			// Build temporary string with event details.
			temp =  "ClientID: " + this.clientID + " "
					+ "Client: " + this.client + " " 
					+ "Title: " + this.title + " "
					+ "Artist: " + this.artist + " " 
					+ "Date: " + this.date + " "
					+ "Time: " + this.time + " " 
					+ "Target: " + this.target + " "
					+ "Duration: " + this.duration + " " 
					+ "Type: " + this.type + " " 
					+ "Category: " + this.category;
		} catch(NullPointerException e) {
			System.out.println("Error. One or more event details are incomplete.");
		}
		
		// Return the built string.
		return temp;
	}
	
	// A method to print the details of an event over several lines.
	// Does not return a string, but prints the details directly.
	public void printDetails() {
		
		// Try/Catch to catch NullPointerException if one
		// or more details are null.
		try {
			
			System.out.printf("%-15s%d\n"
								+ "%-15s%s\n" 
								+ "%-15s%s\n"
								+ "%-15s%s\n"
								+ "%-15s%s\n"
								+ "%-15s%s\n\n",
								"Client ID: ", this.clientID, "Client: ", this.client, 
								"Event Name: ", this.title, "Artist: ", this.artist, 
								"Event Date: ", FileHandler.dateFormatter.format(this.date), 
								"Event Time: ", FileHandler.timeFormatter.format(this.time));
			
		} catch (NullPointerException e1) {
			System.out.println("Error. One or more event details are incomplete.");
		}
	}
	
	// Implement the isBooked method from the Bookable interface.
	// Returns whether the event is booked, or unbooked as a t/f
	public boolean isBooked() {
		
		return isBooked;
		
	}
	
	// Implement the book method from the Bookable interface.
	// Updates the isBooked variable when booked.
	public void book() {
		this.isBooked = true;
	}
	
	// Various get/set methods.
	
	public String getClient() {
		return this.client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getArtist() {
		return this.artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalTime getTime() {
		return this.time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public int getTarget() {
		return this.target;
	}

	public void setTarget(int target) {
		this.target = target;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int getClientID() {
		return this.getClientID();
	}
}
