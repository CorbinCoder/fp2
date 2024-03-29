package customObjects;

// Class used to define a venue object. Venues are locations owned
// by clients that can be used for the purposes of holding an event
// for an hourly price. Venues may book events as part of an order.
// Extends the Client class.
public class Venue extends Client {
	
	// Declare variables for client name, venue capacity, an array of 
	// suitability types for differing events, venue category, and 
	// hourly hire price.

	private int capacity;
	private String[] suitableFor;
	private String category;
	private int hourlyPrice;
	
	// Constructor for a venue object. Uses super to define abstract 
	// class variables and defines all other variables when constructed.
	// suitableFor is an array of string elements.
	public Venue(String client, int capacity, String[] suitableFor, 
					String category, int hourlyPrice) {
		
		super(client);
		this.capacity = capacity;
		this.suitableFor = suitableFor;
		this.category = category;
		this.hourlyPrice = hourlyPrice;
		
	}
	
	// Returns the venue details as a single-line string.
	// Overrides the built-in toString method.
	@Override
	public String toString() {
		
		// Temporary string to build with venue details.
		String temp = "";
		
		// Temporary stringbuilder object to build with
		// venue suitability details.
		StringBuilder suitable = new StringBuilder();
		
		int count = 0;
		
		// Iterate through the suitableFor array, and
		// add each suitablity to a stringbuilder object.
		while (count < suitableFor.length) {
			
			// Catch NullPointerException if an element is null.
			try {
				
				// Append suitable with current suitableFor element.
				suitable.append(suitableFor[count] + ", ");
				count++;
				
			} catch (NullPointerException e) {
				System.out.print("Error. Suitability element missing.");
			}
		}
		
		// Catch NullPointerException if one or more elements are null.
		try {
			
			// Build single-line string with venue details.
			temp = "Name: " + this.client + " "
					+ "Capacity: " + this.capacity + " "
					+ "Suitable For: " + suitable + " "
					+ "Category: " + this.category;
		} catch (NullPointerException e) {
			System.out.println("Error. One or more venue details are incomplete.");
		}
		
		// Return string of venue details.
		return temp;
		
	}
	
	// Return a single-line string of suitableFor elements.
	public String suitableForToString() {
		
		// String to be filled with suitability details.
		String suitableForString = "";
		
		// Iterate through suitableFor elements and add
		// them to a single-line string.
		for (String s : suitableFor) {
			
			suitableForString += (s + ", ");
			
		}
		
		// Return string of suitability elements.
		return suitableForString;
		
	}
	
	// Various get/set methods.
	public String[] getSuitableFor() {
		return this.suitableFor;
	}
	
	public void setSuitableFor(String[] suitableFor) {
		this.suitableFor = suitableFor;
	}
	
	public String getClient() {
		return this.client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
 
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getHourlyPrice() {
		return this.hourlyPrice;
	}
	
	public void setHourlyPrice(int hourlyPrice) {
		this.hourlyPrice = hourlyPrice;
	}
}
