package test_jsoup.test_jsoup;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class ND2EPSTest {
	String tickerSymbol = "MSFT";
	String mainUrl;
	String incomeUrl;
	String incomeQuarterUrl;
	String balanceUrl;
	String cashUrl;
	
	ND2EPS webScraper = new ND2EPS("MSFT");
	int scrapeDelay = 500;
	
	public ND2EPSTest () throws IOException, InterruptedException {
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		incomeUrl = mainUrl + "/financials";
		incomeQuarterUrl = incomeUrl+"/income/quarter";
		balanceUrl = mainUrl + "/balance-sheet";
		cashUrl = mainUrl + "/cash-flow";
	}

	//Tests if the site is still following the same HTML layout. Header should be a four digit number like 2013. If stock is new, this is okay to fail. 
	@Test
	public void testGetEPSPeriodHeader() throws InterruptedException, IOException {
		Document incomeDocument = Jsoup.connect(incomeUrl).get();
		Thread.sleep(scrapeDelay);
		String firstHeaderValue = webScraper.getEPSPeriodHeader(incomeDocument, 0);
		String regex = "\\d{4}";
		if (!firstHeaderValue.matches(regex)) {
			System.out.print("testGetEPSPeriodHeader_firstAnnualHeader_isYear: ");
			System.out.println("First available year for " + tickerSymbol +" is: "+ firstHeaderValue +" and does not match the regex: " + regex);
		}
		assertTrue(firstHeaderValue.matches(regex));
	}
	
	@Test
	public void testGetEPSPeriodValue() {
		
	}

}
