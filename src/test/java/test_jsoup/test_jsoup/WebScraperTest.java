package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
	public void testGetCurrentPrice_isFloat() throws NumberFormatException, IOException, InterruptedException {
		//System.out.println("Stock Price For " + tickerSymbol +" Is: "+ webScraper.getCurrentPrice());
		float floatValue;
		floatValue = Float.parseFloat(webScraper.getCurrentPrice());
		assertNotNull(floatValue);
	}
	
	//Tests if the site is still following the same HTML layout. Header should be a four digit number like 2013. If stock is new, this is okay to fail. 
	@Test
	public void testGetRevenuePeriodHeader_firstAnnualHeader_isYear() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String regex = "\\d{4}";
		String firstHeaderValue = webScraper.getRevenuePeriodHeader(document, 0);
		 if (!firstHeaderValue.matches(regex)) {
			 System.out.println("First Available Year For " + tickerSymbol +" Is: "+ firstHeaderValue +" and does not match the regex: " + regex);
		 }
		assertTrue(firstHeaderValue.matches("\\d{4}"));
	}
	
	//Tests if the site is still following the same HTML layout. Header should be a four digit number like 2017. 
	public void testGetRevenuePeriodHeader_lastAnnualHeader_isYears() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String lastHeaderValue = webScraper.getRevenuePeriodHeader(document, 4);
		//System.out.println("Last Available Year For " + tickerSymbol +" Is: "+ lastHeaderValue);
		assertTrue(lastHeaderValue.matches("\\d{4}"));
	}
	
	//Tests if the site is still following the same HTML layout. Revenue value should contain at least one number like 20.2M. If stock is new, this is okay to fail.
	@Test
	public void testGetRevenuePeriodValue_firstValue_hasNumbers() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String firstRevenueValue = webScraper.getRevenuePeriodValue(document,0);
		//System.out.println("First Available Value For " + tickerSymbol +" Is: "+ firstRevenueValue);
		assertTrue(firstRevenueValue.matches("\\d.*"));
	}
	
	//Tests if the site is still following the same HTML layout. Revenue value should contain at least one number like 4.2B. 
	@Test
	public void testGetRevenuePeriodValue_lastValue_hasNumbers() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeUrl).get();
		String lastHeaderValue = webScraper.getRevenuePeriodValue(document, 4);
		//System.out.println("Last Available Value For " + tickerSymbol +" Is: "+ lastHeaderValue);
		assertTrue(lastHeaderValue.matches("\\d.*"));
	}
	
	//Tests if the site is still following the same HTML layout. Header should be a full date like 31-Dec-2016. If stock is new, this is okay to fail. 
	@Test
	public void testGetRevenuePeriodHeader_firstQuarterHeader_isQuarter() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeQuarterUrl).get();
		String firstHeaderValue = webScraper.getRevenuePeriodHeader(document, 0);
		//System.out.println("First Available Year For " + tickerSymbol +" Is: "+ firstHeaderValue);
		assertTrue(firstHeaderValue.matches("\\d{2}[-][A-Z][a-z]{2}[-]\\d{4}"));
	}
	
	//Tests if the site is still following the same HTML layout. Header should be a full date like 31-Dec-2016. 
	public void testGetRevenuePeriodHeader_lastQuarterHeader_isQuarter() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeQuarterUrl).get();
		String lastHeaderValue = webScraper.getRevenuePeriodHeader(document, 4);
		System.out.println("Last Available Year For " + tickerSymbol +" Is: "+ lastHeaderValue);
		assertTrue(lastHeaderValue.matches("\\d{2}[-][A-Z][a-z]{2}[-]\\d{4}"));
	}
	
	//Tests if the site is still following the same HTML layout. Revenue value should contain at least one number like 20.2M. If stock is new, this is okay to fail.
	@Test
	public void testGetRevenuePeriodValue_firstQuarterValue_hasNumbers() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeQuarterUrl).get();
		String firstRevenueValue = webScraper.getRevenuePeriodValue(document,0);
		//System.out.println("First Available Value For " + tickerSymbol +" Is: "+ firstRevenueValue);
		assertTrue(firstRevenueValue.matches("\\d.*"));
	}
	
	//Tests if the site is still following the same HTML layout. Revenue value should contain at least one number like 4.2B. 
	@Test
	public void testGetRevenuePeriodValue_lastQuarterValue_hasNumbers() throws NumberFormatException, IOException, InterruptedException {
		document = Jsoup.connect(incomeQuarterUrl).get();
		String lastHeaderValue = webScraper.getRevenuePeriodValue(document, 4);
		//System.out.println("Last Available Value For " + tickerSymbol +" Is: "+ lastHeaderValue);
		assertTrue(lastHeaderValue.matches("\\d.*"));
	}
	
	//Tests the Map (LinkedHashMap) to see if it matches the pattern like {2013=77.65B, 2014=86.73B, 2015=92.97B, 2016=84.7B, 2017=89.4B}. 
	@Test
	public void testGetRevenueByYears() throws NumberFormatException, IOException, InterruptedException {
		Map<String, String> revenueByYears = webScraper.getRevenueByYears();
		Set<String> keys = revenueByYears.keySet();
		boolean matchesPattern = true;
		for(String k:keys) {
			//System.out.println("Key: " + k + " Value: " + revenueByYears.get(k));
			if (!k.matches("\\d{4}")) {
				System.out.println("Key pattern: \"" + k + " \" does not match pattern for Year \"\\d{4}");
				matchesPattern = false;
			}
			if (!revenueByYears.get(k).matches("\\d.*")) {
				System.out.println("Key: " + "\"" + k + "\" value's pattern: \"" + revenueByYears.get(k) + " \" does not match pattern for Revenue \"\\d.*\"");
				matchesPattern = false;
			}
		}
		assertTrue(matchesPattern);
	}
	
	//Tests the Map (LinkedHashMap) to see if it matches the pattern like {2017-Q2=23.18B, 2017-Q3=24.43B, 2017-Q4=28.9B, 2018-Q1=26.81B, 2018-Q2=30.09B}. 
	@Test
	public void testGetRevenueByQuarters() throws NumberFormatException, IOException, InterruptedException {
		Map<String, String> revenueByQuarters = webScraper.getRevenueByQuarters();
		Set<String> keys = revenueByQuarters.keySet();
		boolean matchesPattern = true;
		for(String k:keys) {
			//System.out.println("Key: " + k + " Value: " + revenueByQuarters.get(k));
			if (!k.matches("\\d{4}[-][A-Z][\\d]")) {
				System.out.println("Key pattern: \"" + k + " \" does not match pattern for Year \"\\d{4}[-][A-Z][\\d]\"");
				matchesPattern = false;
			}
			if (!revenueByQuarters.get(k).matches("\\d.*")) {
				System.out.println("Key: " + "\"" + k + "\" value's pattern: \"" + revenueByQuarters.get(k) + " \" does not match pattern for Revenue \"\\d.*\"");
				matchesPattern = false;
			}
		}
		assertTrue(matchesPattern);
	}
}
