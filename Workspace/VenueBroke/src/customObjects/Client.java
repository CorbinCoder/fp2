package customObjects;

// Abstract class that allows easy implementation of
// new client types in later versions.
public abstract class Client {
	
	protected String client;

	// Constructs client with client name;
	public Client(String client) {
		this.client = client;
	}
	
	// Abstract methods to be implemented in child classes.
	public abstract String getClient();
	public abstract void setClient(String client);

}
