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
				
				//Past 3 years
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
						
						System.out.println(revenueByYears.get(allYears.size() - 1));
						float latestYearRevenue = Float.parseFloat(revenueByYears.get(allYears.size() - 1).replace("B", "").replace("M", ""));
						float secondLatestYearRevenue = Float.parseFloat(revenueByYears.get(allYears.size() - 2).replace("B", "").replace("M", ""));
						
						if (latestYearRevenue > secondLatestYearRevenue) {
							return true;
						} else if (latestYearRevenue < secondLatestYearRevenue) {
							return false;
						}
					}
					return (Boolean) null;
				}
				
				//Past 3 quarters
				public boolean hasIncreasingQuarterlyRevenue() throws IOException, InterruptedException{
								//Map<String, String> revenueByQuarters = webScraper.getRevenueByQuarters();
								//Set<String> keys = revenueByQuarters.keySet();
								System.out.println("");
								return false;
				}
}
