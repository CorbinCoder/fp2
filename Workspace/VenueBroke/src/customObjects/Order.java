package customObjects;

// A class used to define an order object. Orders are comprised of
// venue and event information, and are used to define an event booked
// at a venue. Orders hold and calculate event pricing information.
public class Order {
	
	// Define variables for event to book, venue to book at, venue hire
	// fee, agent comission, and rate of comission depending on number of
	// events booked with agent.
	private Event event;
	private Venue venue;
	private double venueHire;
	private double comission = 0;
	private double comissionRate;
	
	// Constructor for order class. Initializes event, venue, and
	// comissionRate variables by being passed from the calling method.
	// Order cost is determined by the calculateCost method, and the
	// event is set to booked.
	public Order(Event event, Venue venue, double comissionRate) {
		this.event = event;
		this.venue = venue;
		this.comissionRate = comissionRate;
		calculateCost();
		event.book();
	}
	
	// Print event details as several lines.
	public void display() {
		
		System.out.printf("\nClient: %s\n" +
							"Venue: %5s\n" +
							"Event name: %5s\n" +
							"Artist: %5s\n" +
							"Event date: %5s\n" +
							"Event time: %5s\n" +
							"\n" +
							"%d hours venue hire @ $%d = $%5.2f\n" +
							"Brokering comission @ %.0f%% = $%5.2f\n", 
							this.event.getClient(), this.venue.getClient(), 
							this.event.getTitle(), this.event.getArtist(), 
							this.event.getDate(), this.event.getTime(), 
							this.event.getDuration(), this.venue.getHourlyPrice(), 
							this.venueHire, (this.comissionRate*100), 
							this.comission);
			
	}
	
	// Calculates the cost of an order.
	public void calculateCost() {
		
		// Calculate venue hire fee: event duration by venue hourly price.
		this.venueHire = this.event.getDuration() * this.venue.getHourlyPrice();
		
		// Calculate agent comission: venue hire by comission rate.
		this.comission = this.venueHire * this.comissionRate;
		
	}
	
	// Various get/set methods. setEvent and getEvent use calculate cost 
	// to update venue hire when order details are changed.
	public Event getEvent() {
		return this.event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
		calculateCost();
	}
	
	public Venue getVenue() {
		return this.venue;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
		calculateCost();
	}
	
	public double getVenueHire() {
		return this.venueHire;
	}
	
	public double getComission() {
		return this.comission;
	}
	
	public void setComission(double comission) {
		this.comission = comission;
	}
	
	public double getComissionRate() {
		return this.comissionRate;
	}
	
	public void setComissionRate(double comissionRate) {
		this.comissionRate = comissionRate;
	}
	
}
