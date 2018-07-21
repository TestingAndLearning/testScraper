package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {
	//Add /quarter to the income, balance, and cash urls to get quarterly data, otherwise it is annual by default. 
	String tickerSymbol;
	String mainUrl;
	String incomeUrl; //Financials. 
	String balanceUrl;
	String cashUrl;
	
	Document document;

	public WebScraper(String tickerSymbol) throws IOException {
		this.tickerSymbol = tickerSymbol;
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		incomeUrl = mainUrl + "/financials";
		balanceUrl = mainUrl + "/balance-sheet";
		cashUrl = mainUrl + "/cash-flow";
		
		//document = Jsoup.connect(mainUrl).get();
		//System.out.println(mainUrl);
	}
	
	public String getCurrentPrice() throws IOException {
		document = Jsoup.connect(mainUrl).get();
		Elements stockPriceElement = document.select("h3.intraday__price > bg-quote");
		String elementValue = stockPriceElement.text();
		return elementValue;
	}
	
	public String getRevenue() throws IOException {
		document = Jsoup.connect(incomeUrl).get();
		//Element stockPriceElement = document.getElementsByClass("crDataTable").get(0);
		Element stockPriceElement = document.getElementsByClass("crDataTable").get(0).select("th[scope]").get(0);
		System.out.println(stockPriceElement);
		String elementValue = stockPriceElement.text();
		System.out.println(elementValue);
		return elementValue;
	}
}
