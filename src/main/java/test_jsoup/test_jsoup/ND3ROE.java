package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ND3ROE extends ND1Revenue {

	//This class get the Return On Equity percent value by getting the Net Income divided by the Total Shareholder's Equity. 
	public ND3ROE(String tickerSymbol) throws IOException, InterruptedException {
		super(tickerSymbol);
		balanceSheetDocument = Jsoup.connect(balanceSheetUrl).get();
		Thread.sleep(scrapeDelay);
		balanceSheetQuarterDocument = Jsoup.connect(balanceSheetQuarterUrl).get();
		Thread.sleep(scrapeDelay);
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
	
	//Returns a map of data like so: { "2015"="12.21", "2016"="22.24", "2017"="22.22" }.
	public Map<String, String> getROEByYears() throws IOException, InterruptedException {
		Map<String, String> roeByYears = new LinkedHashMap<String, String>();

		for (int i = 0; i < 5; i++) {
			String netIncomeYearValue = getNetIncomePeriodHeader(incomeDocument, i);
			String shareHolderEquityYearValue = getShareHolderEquityPeriodHeader(balanceSheetDocument, i);
			
			String netIncomeValue = getNetIncomePeriodValue(incomeDocument, i);
			String shareHolderEquityValue = getShareHolderEquityPeriodValue(balanceSheetDocument, i);
			
			if (!netIncomeYearValue.equals(shareHolderEquityYearValue)) {
				System.out.println("["+tickerSymbol+"]: Cannot convert to Map, years for Net Income and Share Holder Equity are different. ");
				return null;
			}
			
			//If all fields are available, divide Net Income by Share Holder Equity to get Return On Equity ratio. Then multiple by 100 and use only 4 digits to get the percent. Output should be something like 23.45. 
			if (netIncomeValue.matches("\\d.*") && shareHolderEquityValue.matches("\\d.*") && !netIncomeYearValue.isEmpty()) {
				Double rawNetIncomeValue = (double)getParsedAlphaNumericMoney(netIncomeValue);
				Double rawShareHolderEquityValue = (double)getParsedAlphaNumericMoney(shareHolderEquityValue);
				String rawROEValue = Double.toString(rawNetIncomeValue/rawShareHolderEquityValue*100);
				String roeValue = rawROEValue.substring(0,5);
				roeByYears.put(netIncomeYearValue, roeValue);
			}
		}
		return roeByYears;
	}
	
	//Do not use for calculate ROE, use latest COMPLETE year. 
	public Map<String, String> getROEByQuarters() throws IOException, InterruptedException {
		Map<String, String> roeByQuarters = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < 5; i++) {
			String netIncomeQuarterValue = getNetIncomePeriodHeader(incomeQuarterDocument, i);
			String shareHolderEquityQuarterValue = getShareHolderEquityPeriodHeader(balanceSheetQuarterDocument, i);
			
			String netIncomeValue = getNetIncomePeriodValue(incomeQuarterDocument, i);
			String shareHolderEquityValue = getShareHolderEquityPeriodValue(balanceSheetQuarterDocument, i);
			
			if (!netIncomeQuarterValue.equals(shareHolderEquityQuarterValue)) {
				System.out.println("["+tickerSymbol+"]: Cannot convert to Map, quarters for Net Income and Share Holder Equity are different. ");
				return null;
			}
			
			//Converts the quarter end date from "30-Sep-2016" to "2016-Q3"
			if (netIncomeValue.matches("\\d.*") && shareHolderEquityValue.matches("\\d.*") && !netIncomeQuarterValue.isEmpty()) {
				String month = netIncomeQuarterValue.split("-")[1];
				String year = netIncomeQuarterValue.split("-")[2];
				StringBuilder parsedQuarterValueBuilder = new StringBuilder();
				parsedQuarterValueBuilder.append(year+"-Q");
				
				switch (month) {
					case "Mar": parsedQuarterValueBuilder.append("1");
								break;
					case "Jun": parsedQuarterValueBuilder.append("2");
								break;
					case "Sep": parsedQuarterValueBuilder.append("3");
								break;
					case "Dec": parsedQuarterValueBuilder.append("4");
								break;
					default: 	parsedQuarterValueBuilder.setLength(0);
								parsedQuarterValueBuilder.append(netIncomeQuarterValue);
								System.out.println("["+ tickerSymbol + "]: Cannot convert to Map, unexpected Quarter Month value: " + month);
								break;
				}
				String parsedQuarterValue = parsedQuarterValueBuilder.toString();
				
				//If all fields are available, divide Net Income by Share Holder Equity to get Return On Equity ratio. Then multiple by 100 and use only 4 digits to get the percent. Output should be something like 23.45. 
				if (netIncomeValue.matches("\\d.*") && shareHolderEquityValue.matches("\\d.*") && !netIncomeQuarterValue.isEmpty()) {
					Double rawNetIncomeValue = (double)getParsedAlphaNumericMoney(netIncomeValue);
					Double rawShareHolderEquityValue = (double)getParsedAlphaNumericMoney(shareHolderEquityValue);
					String rawROEValue = Double.toString(rawNetIncomeValue/rawShareHolderEquityValue*100);
					String roeValue = rawROEValue.substring(0,5);
					roeByQuarters.put(parsedQuarterValue, roeValue);
				}
			}
		}
		return roeByQuarters;
	}

	/** Total Shareholder's Equity End. **/
	
	/** ********************* **/
	/** ND #3 ROE Section END **/
	/** ********************* **/
}