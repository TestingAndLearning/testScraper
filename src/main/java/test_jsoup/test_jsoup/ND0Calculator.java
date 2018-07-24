package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ND0Calculator {
				String tickerSymbol;
				ND1Revenue webScraper;
				
				public ND0Calculator(String tickerSymbol) throws IOException, InterruptedException{
					this.tickerSymbol = tickerSymbol;
					webScraper = new ND1Revenue(tickerSymbol);
				}
				
				/** Start: 1 Revenue **/
				//Compares only the latest complete fiscal year and the year before that. 
				public boolean hasIncreasingYearlyRevenue() throws IOException, InterruptedException {
					Map<String, String> revenueByYears = webScraper.getRevenueByYears();
					Set<String> keys = revenueByYears.keySet();
					ArrayList<String> allYears = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough Annual Data. ");
						return (Boolean) null;
					} else {
						for (String k : keys) {
							allYears.add(k);
						}
						
						String unParsedLatestYearRevenue = revenueByYears.get(allYears.get(allYears.size() - 1));
						String unParsedSecondLatestYearRevenue = revenueByYears.get(allYears.get(allYears.size() - 2));
						float latestYearRevenue = Float.parseFloat(unParsedLatestYearRevenue.replace("B", "").replace("M", ""));
						float secondLatestYearRevenue = Float.parseFloat(unParsedSecondLatestYearRevenue.replace("B", "").replace("M", ""));
						
						if (latestYearRevenue > secondLatestYearRevenue) {
							return true;
						} else if (latestYearRevenue < secondLatestYearRevenue) {
							return false;
						}
					}
					return (Boolean) null;
				}
				
				//Compares only latest complete quarter and the quarter before that. 
				public boolean hasIncreasingQuarterlyRevenue() throws IOException, InterruptedException{
					Map<String, String> revenueByQuarters = webScraper.getRevenueByQuarters();
					Set<String> keys = revenueByQuarters.keySet();
					ArrayList<String> allQuarters = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough Quarterly Data. ");
						return (Boolean) null;
					} else {
						for (String k : keys) {
							allQuarters.add(k);
						}
						
						String unParsedLatestQuartersRevenue = revenueByQuarters.get(allQuarters.get(allQuarters.size() - 1));
						String unParsedSecondLatestQuartersRevenue = revenueByQuarters.get(allQuarters.get(allQuarters.size() - 2));
						float latestQuartersRevenue = Float.parseFloat(unParsedLatestQuartersRevenue.replace("B", "").replace("M", ""));
						float secondLatestQuartersRevenue = Float.parseFloat(unParsedSecondLatestQuartersRevenue.replace("B", "").replace("M", ""));
						
						if (latestQuartersRevenue > secondLatestQuartersRevenue) {
							return true;
						} else if (latestQuartersRevenue < secondLatestQuartersRevenue) {
							return false;
						}
					}
					return (Boolean) null;
				}
				/** End: 1 Revenue **/
}
