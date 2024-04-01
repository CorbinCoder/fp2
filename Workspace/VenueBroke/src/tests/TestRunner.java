package tests;

import org.junit.internal.TextListener;
import org.junit.runner.*;

public class TestRunner {
	
	static JUnitCore junit;
	static Result result;
	
	public TestRunner() {
		
		junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		
	}
	
	public void run() {
		
		System.out.println("Running tests...");
		result = junit.run(EventTest.class, VenueTest.class, OrderTest.class,
							FileHandlerTest.class, MenuTest.class);
		
	}
	
	// https://www.baeldung.com/junit-tests-run-programmatically-from-java

}
