package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import junit.framework.TestCase;

public class WebScraperTest extends TestCase {
	String tickerSymbol = "MSFT";
	String mainUrl;
	String incomeUrl;
	String incomeQuarterUrl;
	String balanceUrl;
	String cashUrl;
	
	WebScraper webScraper = new WebScraper(tickerSymbol);
	Document document; 
	
	public WebScraperTest() throws IOException {
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		incomeUrl = mainUrl + "/financials";
		incomeQuarterUrl = incomeUrl+"/income/quarter";
		balanceUrl = mainUrl + "/balance-sheet";
		cashUrl = mainUrl + "/cash-flow";
	}
	
	//Tests if the site is still following the same HTML layout. Stock price should be convertible into a float like 103.58. 
	@Test
	public void testGetCurrentPrice() throws NumberFormatException, IOException, InterruptedException {
		System.out.println("Stock Price For " + tickerSymbol +" Is: "+ webScraper.getCurrentPrice());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getCurrentPrice());
		assertNotNull(floatValue);
	}
	
	//Tests if the site is still following the same HTML layout. Header should be convertible into an integer like 2013. 
	@Test
	public void testGetRevenuePeriodHeader_firstAnnualHeader() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String firstHeaderValue = webScraper.getRevenuePeriodHeader(document, 0);
		int headerIntValue;
		headerIntValue = Integer.parseInt(firstHeaderValue);
		System.out.println("First Available Year For " + tickerSymbol +" Is: "+ firstHeaderValue);
		assertNotNull(headerIntValue);
	}
	
	//Tests if the site is still following the same HTML layout. Header should be convertible into an integer like 2017. 
	public void testGetRevenuePeriodHeader_lastAnnualHeader() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String lastHeaderValue = webScraper.getRevenuePeriodHeader(document, 4);
		int headerIntValue;
		headerIntValue = Integer.parseInt(lastHeaderValue);
		System.out.println("Last Available Year For " + tickerSymbol +" Is: "+ lastHeaderValue);
		assertNotNull(headerIntValue);
	}
	
	//Tests if the site is still following the same HTML layout. Revenue value should contain at least one number like 20.2M. 
	@Test
	public void testGetRevenuePeriodValue_firstValue() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String firstRevenueValue = webScraper.getRevenuePeriodValue(document,0);
		System.out.println("First Available Value For " + tickerSymbol +" Is: "+ firstRevenueValue);
		assertTrue(firstRevenueValue.matches("^.\\d.*"));
	}
	
	//Tests if the site is still following the same HTML layout. Revenue value should contain at least one number like 4.2B. 
	@Test
	public void testGetRevenuePeriodValue_lastValue() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String lastHeaderValue = webScraper.getRevenuePeriodValue(document, 4);
		System.out.println("Last Available Value For " + tickerSymbol +" Is: "+ lastHeaderValue);
		assertTrue(lastHeaderValue.matches("^.\\d.*"));
	}
	
	@Test
	public void testGetRevenueByYears() throws NumberFormatException, IOException {
		
	}
	
	@Test
	public void testGetRevenueByQuarters() throws NumberFormatException, IOException {
		
	}
}
