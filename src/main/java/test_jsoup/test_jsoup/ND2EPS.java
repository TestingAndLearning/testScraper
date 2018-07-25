package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ND2EPS extends ND1Revenue {

	public ND2EPS(String tickerSymbol) throws IOException, InterruptedException {
		super(tickerSymbol);
		
	}
	
	/** *********************** **/
	/** ND #2 EPS Section START **/
	/** *********************** **/
	
	//The period can be Years or Quarters. Years would be in the format "2013", Quarters would be in the format "30-Sep-2016"
	public String getEPSPeriodHeader(Document document, int index) throws InterruptedException {
		Element yearNode = document.getElementsByClass("crDataTable").get(1).select("th[scope]").get(index);
		String epsYear = yearNode.text();
		return epsYear;
	}
	
	//Gets the EPS values by index, 0 would be oldest period (2013 for years) and 4 would be latest period (2017 for years) at the current year of 2018. The scraper content is an HTML table. 
	public String getEPSPeriodValue(Document document, int index) throws InterruptedException {
		System.out.println(document.getElementsByClass("crDataTable").get(1).select("tbody > tr.mainRow").get(15).select("td.valueCell").get(index));
		Element epsNode = document.getElementsByClass("crDataTable").get(1).select("tbody > tr.mainRow").get(15).select("td.valueCell").get(index);
		String epsValue = epsNode.text();
		return epsValue;
	}
	
	//Returns a map of data like so: { "2015"="1.2b", "2016"="2.4b", "2017"="2.2b" }.
	public Map<String, String> getEPSByYears() throws IOException, InterruptedException {
		Map<String, String> epsByYears = new LinkedHashMap<String, String>();

		for (int i = 0; i < 5; i++) {
			String yearValue = getEPSPeriodHeader(incomeDocument, i);
			String epsValue = getEPSPeriodValue(incomeDocument, i);
			if (!yearValue.isEmpty()) {
				epsByYears.put(yearValue, epsValue);
			}
		}
		return epsByYears;
	}
	
	public Map<String, String> getEPSByQuarters() throws IOException, InterruptedException {
		Map<String, String> epsByQuarters = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < 5; i++) {
			String quarterValue = getEPSPeriodHeader(incomeQuarterDocument, i);
			String epsValue = getEPSPeriodValue(incomeQuarterDocument, i);
			
			//Converts the quarter end date from "30-Sep-2016" to "2016-Q3"
			if (!quarterValue.isEmpty()) {
				String month = quarterValue.split("-")[1];
				String year = quarterValue.split("-")[2];
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
								parsedQuarterValueBuilder.append(quarterValue);
								//Log unexpected month. 
								break;
				}
				String parsedQuarterValue = parsedQuarterValueBuilder.toString();
				epsByQuarters.put(parsedQuarterValue, epsValue);
			}
		}
		return epsByQuarters;
	}
	
	/** ********************* **/
	/** ND #2 EPS Section END **/
	/** ********************* **/

}
