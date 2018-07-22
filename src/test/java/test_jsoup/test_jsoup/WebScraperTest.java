package test_jsoup.test_jsoup;

import java.io.IOException;

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
	int scrapeDelay = 1000; //Delay in ms between each HTTP action. 
	
	public WebScraperTest() throws IOException {
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		incomeUrl = mainUrl + "/financials";
		incomeQuarterUrl = incomeUrl+"/income/quarter";
		balanceUrl = mainUrl + "/balance-sheet";
		cashUrl = mainUrl + "/cash-flow";
	}
	
	@Test
	public void testGetCurrentPrice() throws NumberFormatException, IOException, InterruptedException {
		System.out.println("Stock Price for " + tickerSymbol +" Is: "+ webScraper.getCurrentPrice());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getCurrentPrice());
		Thread.sleep(scrapeDelay);
		assertNotNull(floatValue);
	}
	
	@Test
	public void testGetRevenuePeriodHeader() throws NumberFormatException, IOException {
		//System.out.println("Revenue for " + tickerSymbol +" Is: "+ webScraper.getRevenue());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getRevenuePeriodHeader(null, 0));
		assertNotNull(floatValue);
	}
	
	@Test
	public void testGetRevenuePeriodValue() throws NumberFormatException, IOException {
		//System.out.println("Revenue for " + tickerSymbol +" Is: "+ webScraper.getRevenue());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getRevenueByYear());
		assertNotNull(floatValue);
	}
	
	@Test
	public void testGetRevenueByYears() throws NumberFormatException, IOException {
		//System.out.println("Revenue for " + tickerSymbol +" Is: "+ webScraper.getRevenue());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getRevenueByYear());
		assertNotNull(floatValue);
	}
	
	@Test
	public void testGetRevenueByQuarters() throws NumberFormatException, IOException {
		//System.out.println("Revenue for " + tickerSymbol +" Is: "+ webScraper.getRevenue());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getRevenueByYear());
		assertNotNull(floatValue);
	}
}
