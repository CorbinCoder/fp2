package customObjects;

// Abstract class that allows easy implementation of
// new client types in later versions.
public abstract class Client {
	
	protected String client;
	protected final int clientID;
	protected static int clientIDGenerator = 0;

	// Constructs client with client name;
	public Client(String client) {
		this.client = client;
		this.clientID = ++clientIDGenerator;
	}
	
	// Abstract methods to be implemented in child classes.
	public abstract String getClient();
	public abstract void setClient(String client);
	public abstract int getClientID();

}
