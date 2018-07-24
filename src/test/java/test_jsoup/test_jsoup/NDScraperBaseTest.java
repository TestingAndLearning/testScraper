package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import junit.framework.TestCase;

public class NDScraperBaseTest extends TestCase {
	String tickerSymbol = "MSFT";
	String mainUrl;
	String incomeUrl;
	String incomeQuarterUrl;
	String balanceUrl;
	String cashUrl;
	
	Document mainDocument;
	Document incomeDocument;
	Document incomeQuarterDocument;
	Document balanceSheetDocument;
	Document balanceSheetQuarterUrlDocument;
	Document cashflowDocument;
	Document cashflowQuarterDocument;
	
	NDScraperBase webScraper = new NDScraperBase(tickerSymbol);
	int scrapeDelay = 500;
	
	public NDScraperBaseTest() throws IOException, InterruptedException {
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
	
	@Test
	public void testGetParsedAlphaNumericMoneyB() {
		long result = 11120000000L;
		long parsedNumericMoney = webScraper.getParsedAlphaNumericMoney("11.12B");
		assertEquals(result, parsedNumericMoney);
	}
	
	@Test
	public void testGetParsedAlphaNumericMoneyM() {
		long result = 311120000L;
		long parsedNumericMoney = webScraper.getParsedAlphaNumericMoney("311.12M");
		assertEquals(result, parsedNumericMoney);
	}
	
	@Test
	public void testGetParsedAlphaNumericMoneyNoDecB() {
		long result = 22000000000L;
		long parsedNumericMoney = webScraper.getParsedAlphaNumericMoney("22B");
		assertEquals(result, parsedNumericMoney);
	}
	
	@Test
	public void testGetParsedAlphaNumericMoneyNoDecM() {
		long result = 222000000L;
		long parsedNumericMoney = webScraper.getParsedAlphaNumericMoney("222M");
		assertEquals(result, parsedNumericMoney);
	}
	
	@Test
	public void testGetParsedAlphaNumericMoneyNoLetter() {
		Long result = null;
		Long parsedNumericMoney = webScraper.getParsedAlphaNumericMoney("11.12");
		assertEquals(result, parsedNumericMoney);
	}
	
	@Test
	public void testGetParsedAlphaNumericMoneyNoLetterNoDec() {
		Long result = null;
		Long parsedNumericMoney = webScraper.getParsedAlphaNumericMoney("311");
		assertEquals(result, parsedNumericMoney);
	}
	
}
