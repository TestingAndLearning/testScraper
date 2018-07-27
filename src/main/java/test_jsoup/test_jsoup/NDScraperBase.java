package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.Arrays;
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
	Document balanceSheetQuarterDocument;
	Document cashflowDocument;
	Document cashflowQuarterDocument;
	

	public NDScraperBase(String tickerSymbol) throws IOException, InterruptedException {
		this.tickerSymbol = tickerSymbol;
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		
		incomeUrl = mainUrl + "/financials";
		incomeQuarterUrl = incomeUrl + "/income/quarter";
		
		balanceSheetUrl = incomeUrl + "/balance-sheet";
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
	
	/** ****************************************************** **/
	/** 000B_Start: This Section is for parsing money values.  **/
	/** ****************************************************** **/
	
	//Converts 11.1B to 11,100,000,000 and 2M to 2,000,000 without the commas. 
	public Long getParsedAlphaNumericMoney(String money) {
		int decimalSpaces = 0;
		
		if (!money.contains("M") && !money.contains("B"))
		{
			System.out.println("No M or B detected, invalid input for money. ");
			return null;
		}
		
		if (money.contains(".")) {
			decimalSpaces = money.split("\\.")[1].length()-1; //-1 because of M and B at the end. 
		}
		StringBuilder zeroAppender = new StringBuilder();
		zeroAppender.append(money);
		
		if (money.contains("B")) {
			for (int i = 0; i < 9 - decimalSpaces; i++) {
				zeroAppender.append("0");
			}
			
		} else if (money.contains("M")) {
			for (int i = 0; i < 6 - decimalSpaces; i++) {
				zeroAppender.append("0");
			}
			
		} else {
			System.out.println("Money already numeric. ");
			return Long.parseLong(money);
		}
		//String numericMoney = zeroAppender.toString().replace("B", "").replace("B", "").replace(".", "");
		String numericMoney = zeroAppender.toString().replaceAll("[BM.()]", "");
		long parsedAlphaNumericMoney = Long.parseLong(numericMoney);
		return parsedAlphaNumericMoney;
	}
	
	/** ******** **/
	/** 000B_End **/
	/** ******** **/

}
