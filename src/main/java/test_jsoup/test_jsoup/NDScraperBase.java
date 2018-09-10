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
	String analystUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/analystestimates
	String profileUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/profile
	String insiderUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/insideractions
	
	String incomeUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials
	String incomeQuarterUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/income/quarter
	
	String balanceSheetUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/balance-sheet
	String balanceSheetQuarterUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/balance-sheet/quarter
	
	String cashflowUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/cash-flow
	String cashflowQuarterUrl; //https://www.marketwatch.com/investing/stock/${tickerSymbol}/financials/cash-flow/quarter
	
	
	int scrapeDelay = 500; //Delay in ms between each HTTP action. 
	Document mainDocument;
	Document analystDocument;
	Document profileDocument;
	Document insiderDocument;
	Document incomeDocument;
	Document incomeQuarterDocument;
	Document balanceSheetDocument;
	Document balanceSheetQuarterDocument;
	Document cashflowDocument;
	Document cashflowQuarterDocument;

	
	public NDScraperBase(String tickerSymbol) throws IOException, InterruptedException {
		this.tickerSymbol = tickerSymbol;
		mainUrl = "https://www.marketwatch.com/investing/stock/" + tickerSymbol;
		analystUrl = mainUrl + "/analystestimates";
		profileUrl = mainUrl + "/profile";
		insiderUrl = mainUrl + "/insideractions";
		
		incomeUrl = mainUrl + "/financials";
		incomeQuarterUrl = incomeUrl + "/income/quarter";
		
		balanceSheetUrl = incomeUrl + "/balance-sheet";
		balanceSheetQuarterUrl = balanceSheetUrl + "/quarter";
		
		cashflowUrl = mainUrl + "/cash-flow";
		cashflowQuarterUrl = cashflowUrl + "/quarter";
		
		mainDocument = Jsoup.connect(mainUrl).get();
		profileDocument = Jsoup.connect(profileUrl).get();
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
		Element stockPriceElement = profileDocument.getElementsByClass("pricewrap").get(0).getElementsByClass("data").get(0);
		String elementValue = stockPriceElement.text();
		return elementValue;
	}
	
	//Get current P/E Ratio from profile page. 
	public String getPERatio() throws InterruptedException {
		Element industryNode = profileDocument.select("div.block.threewide.addgutter").get(0).select("div.section").get(0).select("p").get(1);
		String industry = industryNode.text();
		return industry;
	}
	
	//Gets current volume from profile page. 
	public String getVolume() throws InterruptedException {
		Element industryNode = profileDocument.select("div.section.activeQuote.bgQuote.down").get(0).select("div").get(5).select("p").get(3).select("span").get(1);
		String industry = industryNode.text();
		return industry;
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
		
		if (!money.contains("M") && !money.contains("B") && !money.contains(","))
		{
			System.out.println("No M or B detected, invalid input for money. " + money + ")");
			//If still number, just return the number. 
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
			String parsedMoney = money.replaceAll(",", "");
			return Long.parseLong(parsedMoney);	//Sometimes value will be 170,000, need to replace comma. 
		}
		//String numericMoney = zeroAppender.toString().replace("B", "").replace("B", "").replace(".", "");
		String numericMoney = zeroAppender.toString().replaceAll("[BM.)]", "").replaceAll("[(]", "-");
		long parsedAlphaNumericMoney = Long.parseLong(numericMoney);
		return parsedAlphaNumericMoney;
	}
	
	//Convert to only use decimal of last few digits. (Omits 0s at the end though). 
	public Double useDecimalPlaces(Double rawValue, int decimalSpaces) {
		//Prevent issues with digits too low. 
		//if (rawValue.toString().length() < 5) {
			//rawValue = rawValue *10000;
		//}
		
		String rawValueText = rawValue.toString();
		
		Double convertedValue = null;
		
		if (rawValueText.contains(".")) {
			//Check if sufficient amount of digits after decimal, if it is like 10.1, then next steps may run into outofbounds issues. 
			if (rawValueText.split("\\.")[1].length() < 5) {
				rawValueText = rawValueText + "0000";
			}
			
			int decimalIndex = rawValueText.indexOf(".");
			convertedValue = Double.parseDouble(rawValueText.substring(0,decimalIndex+decimalSpaces+1));
		} else {
			System.out.println("No decimal detected in :" + rawValueText + "Truncating to " + decimalSpaces + " digits: " + convertedValue);
			convertedValue = Double.parseDouble(rawValueText.substring(0,decimalSpaces+1));
		}
		return convertedValue;
	}
	
	//Divides latest period by second latest period to get the percent increase/decrease. For large values like 1.1B that have been converted to 1100000000. 
	public Double convertDifferenceToPercent(Long latestPeriodValue, Long secondLatestPeriodValue) {
		//Would be something like 1.2467 or 0.8550
		Double unParsedPercentIncrease = useDecimalPlaces((double)latestPeriodValue/(double)secondLatestPeriodValue, 4);
		
		if (unParsedPercentIncrease >= 1) {
			String percentIncreaseText;
			if (unParsedPercentIncrease >=2) {
				percentIncreaseText = Double.toString((unParsedPercentIncrease-1)*100); //Changes 2.24670000 to 124.670000 to represent percent.
			} else {
				percentIncreaseText = Double.toString(unParsedPercentIncrease*100).substring(1); //Changes 1.2467 to 24.67. 
			}
			Double percentIncrease = useDecimalPlaces(Double.parseDouble(percentIncreaseText), 2);
			//System.out.println("Increasing: " + percentIncrease);
			return percentIncrease;
			
		} else if (unParsedPercentIncrease < 1) {
			Double differenceIncrease = 1 - unParsedPercentIncrease;
			String percentIncreaseText = Double.toString(differenceIncrease*100); //Changes 0.8550 to 14.5000000...
			Double percentIncrease = useDecimalPlaces(Double.parseDouble(percentIncreaseText), 2)*-1; //Changes 14.5000000 to 14.50
			//System.out.println("Decreasing: " + percentIncrease);
			return percentIncrease;
		}
		return null;
	}
	
	//Divides latest period by second latest period to get the percent increase/decrease. For values like EPS that have decimals: 10.2
	public Double convertDifferenceToPercent(Double latestPeriodValue, Double secondLatestPeriodValue) {
		//Would be something like 1.2467 or 0.8550
		Double unParsedPercentIncrease = useDecimalPlaces(latestPeriodValue/secondLatestPeriodValue, 4);
		
		if (unParsedPercentIncrease >= 1) {
			String percentIncreaseText;
			if (unParsedPercentIncrease >=2) {
				percentIncreaseText = Double.toString((unParsedPercentIncrease-1)*100); //Changes 2.24670000 to 124.670000 to represent percent.
			} else {
				percentIncreaseText = Double.toString(unParsedPercentIncrease*100).substring(1); //Changes 1.2467 to 24.67. 
			}
			Double percentIncrease = useDecimalPlaces(Double.parseDouble(percentIncreaseText), 2);
			//System.out.println("Increasing: " + percentIncrease);
			return percentIncrease;
			
		} else if (unParsedPercentIncrease < 1) {
			Double differenceIncrease = 1 - unParsedPercentIncrease;
			String percentIncreaseText = Double.toString(differenceIncrease*100); //Changes 0.8550 to 14.5000000...
			Double percentIncrease = useDecimalPlaces(Double.parseDouble(percentIncreaseText), 2)*-1; //Changes 14.5000000 to 14.50
			//System.out.println("Decreasing: " + percentIncrease);
			return percentIncrease;
		}
		return null;
	}

	
	/** ******** **/
	/** 000B_End **/
	/** ******** **/
}
