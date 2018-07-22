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
	
}
