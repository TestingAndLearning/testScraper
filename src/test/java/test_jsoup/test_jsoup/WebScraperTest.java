package test_jsoup.test_jsoup;

import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;

public class WebScraperTest extends TestCase {
	String tickerSymbol = "MSFT";
	WebScraper webScraper = new WebScraper(tickerSymbol);
	
	public WebScraperTest() throws IOException {
		
	}
	
	@Test
	public void testGetCurrentPrice() throws NumberFormatException, IOException {
		System.out.println("Stock Price for " + tickerSymbol +" Is: "+ webScraper.getCurrentPrice());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getCurrentPrice());
		assertNotNull(floatValue);
	}
	
	@Test
	public void testGetRevenue() throws NumberFormatException, IOException {
		//System.out.println("Revenue for " + tickerSymbol +" Is: "+ webScraper.getRevenue());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getRevenue());
		assertNotNull(floatValue);
	}
}
