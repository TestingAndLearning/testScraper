package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ND0Calculator {
				String tickerSymbol;
				ND1Revenue webScraper = new ND1Revenue(tickerSymbol);
				
				public ND0Calculator(String tickerSymbol) throws IOException, InterruptedException{
								this.tickerSymbol = tickerSymbol;
				}
				
				//Past 3 years
				public boolean hasIncreasingAnnualRevenue() throws IOException, InterruptedException {
								Map<String, String> revenueByYears = webScraper.getRevenueByYears();
								Set<String> keys = revenueByYears.keySet();
								ArrayList<String> allYears = new ArrayList<>();
								System.out.println(keys.size());
								for (String k : keys) {

								}
								return false;
				}
				
				//Past 3 quarters
				public boolean hasIncreasingQuarterlyRevenue() throws IOException, InterruptedException{
								Map<String, String> revenueByQuarters = webScraper.getRevenueByQuarters();
								Set<String> keys = revenueByQuarters.keySet();
								System.out.println("");
								return false;
				}
}
