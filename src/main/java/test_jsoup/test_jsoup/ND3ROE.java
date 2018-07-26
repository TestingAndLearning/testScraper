package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ND3ROE extends ND1Revenue {

	//This class get the Return On Equity percent value by getting the Net Income divided by the Total Shareholder's Equity. 
	public ND3ROE(String tickerSymbol) throws IOException, InterruptedException {
		super(tickerSymbol);
		
	}
	
	/** *********************** **/
	/** ND #3 ROE Section START **/
	/** *********************** **/
	
	/** Total Net Income Start. **/
	//The period can be Years or Quarters. Years would be in the format "2013", Quarters would be in the format "30-Sep-2016"
	public String getNetIncomePeriodHeader(Document document, int index) throws InterruptedException {
		Element yearNode = document.getElementsByClass("crDataTable").get(1).select("th[scope]").get(index);
		String netIncomeYear = yearNode.text();
		return netIncomeYear;
	}
	
	//Gets the EPS values by index, 0 would be oldest period (2013 for years) and 4 would be latest period (2017 for years) at the current year of 2018. The scraper content is an HTML table. 
	public String getNetIncomePeriodValue(Document document, int index) throws InterruptedException {
		Element netIncomeNode = document.getElementsByClass("crDataTable").get(1).select("tbody > tr.totalRow").get(0).select("td.valueCell").get(index);
		String netIncomeValue = netIncomeNode.text().replaceAll("[()]", ""); //Sometimes values will have brackets like "(0.08)". 
		return netIncomeValue;
	}
	/** Total Net Income End. **/
	
	/** Total Shareholder's Equity Start. **/
	//The period can be Years or Quarters. Years would be in the format "2013", Quarters would be in the format "30-Sep-2016"
	public String getShareHolderEquityPeriodHeader(Document document, int index) throws InterruptedException {
		Element yearNode = document.getElementsByClass("crDataTable").get(2).select("th[scope]").get(index);
		String shareHolderEquityYear = yearNode.text();
		return shareHolderEquityYear;
	}
	
	//Gets the EPS values by index, 0 would be oldest period (2013 for years) and 4 would be latest period (2017 for years) at the current year of 2018. The scraper content is an HTML table. 
	public String getShareHolderEquityPeriodValue(Document document, int index) throws InterruptedException {
		Element shareHolderEquityNode = document.getElementsByClass("crDataTable").get(2).select("tbody > tr.partialSum").get(1).select("td.valueCell").get(index);
		String shareHolderEquityValue = shareHolderEquityNode.text().replaceAll("[()]", ""); //Sometimes values will have brackets like "(0.08)". 
		return shareHolderEquityValue;
	}
	
	//Net income is in https://www.marketwatch.com/investing/stock/msft/financials
	//Shareholder Equity is in https://www.marketwatch.com/investing/stock/msft/financials/balance-sheet
	
	public Double getReturnOnEquityRatio() {
		
		return 1.0;
		
	}
	/** Total Shareholder's Equity End. **/
	
	/** ********************* **/
	/** ND #3 ROE Section END **/
	/** ********************* **/
}