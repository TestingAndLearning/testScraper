package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NDScraperBase {
	
	/** ************************** **/
	/** Begin Initializing Scraper **/
	/** ************************** **/
	
	String tickerSymbol; //MSFT
	String mainUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}
	
	String incomeUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials
	String incomeQuarterUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/income/quarter
	
	String balanceSheetUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/balance-sheet
	String balanceSheetQuarterUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/balance-sheet/quarter
	
	String cashflowUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/cash-flow
	String cashflowQuarterUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/cash-flow/quarter
	
	int scrapeDelay = 500; //Delay in ms between each HTTP action. 
	Document mainDocument;
	Document incomeDocument;
	Document incomeQuarterDocument;
	Document balanceSheetDocument;
	Document balanceSheetQuarterUrlDocument;
	Document cashflowDocument;
	Document cashflowQuarterDocument;
	

	public NDScraperBase(String tickerSymbol) throws IOException, InterruptedException {
		this.tickerSymbol = tickerSymbol;
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		
		incomeUrl = mainUrl + "/financials";
		incomeQuarterUrl = incomeUrl + "/income/quarter";
		
		balanceSheetUrl = mainUrl + "/balance-sheet";
		balanceSheetQuarterUrl = balanceSheetUrl + "/quarter";
		
		cashflowUrl = mainUrl + "/cash-flow";
		cashflowQuarterUrl = cashflowUrl + "/quarter";
		
		mainDocument = Jsoup.connect(mainUrl).get();
		Thread.sleep(scrapeDelay);
	}
	
	/** ************************ **/
	/** End Initializing Scraper **/
	/** ************************ **/
	
	
	/** ****************************************************************** **/
	/** 000A_Start: This section is for getting general stock data values. **/ 
	/** ****************************************************************** **/
	
	//Gets current stock price at the main page. 
	public String getCurrentPrice() throws IOException {
		Elements stockPriceElement = mainDocument.select("h3.intraday__price > bg-quote");
		String elementValue = stockPriceElement.text();
		return elementValue;
	}
	
	/** ******** **/
	/** 000A_End **/
	/** ******** **/
	

}
