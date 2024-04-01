package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.*;

import customObjects.Venue;

// Used to test the venue class.
public class VenueTest {
	
	// Declare variable used to store data.
	private ArrayList<Venue> testVenues;

	@Before
	public void setUp() {
		testVenues = new ArrayList<>();
	}
	
	// Test adding valid data.
	@Test
	public void addValidData() {
		
		testVenues.add(new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100));
		testVenues.add(new Venue("Venue 2", 2000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 2", 200));
		testVenues.add(new Venue("Venue 3", 3000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 3", 300));
		
		assertEquals("Category 3", testVenues.get(2).getCategory());
	}
	
	// Test list not null after data added.
	@Test
	public void testListNotNull() {
		
		testVenues.add(new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100));
		assertTrue(testVenues.size() > 0);
	}
	
	// Test accuracy of list element.
	@Test
	public void testListDataAccurate() {
		
		testVenues.add(new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100));
		testVenues.add(new Venue("Venue 2", 2000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 2", 200));
		
		assertEquals("Category 2", testVenues.get(1).getCategory());
	}
		
	// Test output of toString method.
	@Test
	public void testToString() {
		
		testVenues.add(new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100));
		
		String builtString = "Client ID: 20 Name: Venue 1 Capacity: 1000 "
								+ "Suitable For: Type 1, Type 2, Type 3 "
								+ "Category: Category 1 Hire Fee: 100";
		String stringFromList = testVenues.get(0).toString();
		assertEquals(builtString, stringFromList);
	}
	
	// Test output of suitableForToString method.
	@Test
	public void testSuitableForToString() {
		
		testVenues.add(new Venue("Venue 1", 1000, new String[]
				{"Type 1", "Type 2", "Type 3"}, "Category 1", 100));
		
		String builtString = "Type 1, Type 2, Type 3";
		String stringFromArray = testVenues.get(0).suitableForToString();
		assertEquals(builtString, stringFromArray);
	}
}
