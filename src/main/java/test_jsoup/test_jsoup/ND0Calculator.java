package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ND0Calculator {
				String tickerSymbol;
				ND2EPS webScraper;
				
				public ND0Calculator(String tickerSymbol) throws IOException, InterruptedException{
					this.tickerSymbol = tickerSymbol;
					webScraper = new ND2EPS(tickerSymbol);
				}
				
				/** **************** **/
				/** Start: 1 Revenue **/
				/** **************** **/
				//For YEAR, compares only the latest complete fiscal year and the year before that. 
				//For QUARTER, compares only latest complete quarter and the quarter before that. 
				public Boolean hasIncreasingRevenue(Period period) throws IOException, InterruptedException {
					Map<String, String> revenueByPeriod = null;
					
					switch (period) { 
						case YEAR: 		revenueByPeriod = webScraper.getRevenueByYears();
										System.out.println(revenueByPeriod);
										break;
						case QUARTER: 	revenueByPeriod = webScraper.getRevenueByQuarters();
										break;
						default: 		System.out.println("Invalid period entered: " + period);
										break;
					}

					Set<String> keys = revenueByPeriod.keySet();
					ArrayList<String> allQuarters = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough Quarterly Data. ");
						return null;
					} else {
						for (String k : keys) {
							allQuarters.add(k);
						}
						
						String unParsedLatestPeriodRevenue = revenueByPeriod.get(allQuarters.get(allQuarters.size() - 1));
						String unParsedSecondLatestPeriodRevenue = revenueByPeriod.get(allQuarters.get(allQuarters.size() - 2));
						Long latestPeriodRevenue = webScraper.getParsedAlphaNumericMoney(unParsedLatestPeriodRevenue);
						Long secondLatestPeriodRevenue = webScraper.getParsedAlphaNumericMoney(unParsedSecondLatestPeriodRevenue);
						
						if (latestPeriodRevenue > secondLatestPeriodRevenue) {
							return true;
						} else if (latestPeriodRevenue < secondLatestPeriodRevenue) {
							return false;
						}
					}
					return null;
				}
				/** ************** **/
				/** End: 1 Revenue **/
				/** ************** **/
				
				/** ************ **/
				/** Start: 2 EPS **/
				/** ************ 
				 * @throws InterruptedException 
				 * @throws IOException **/
				
				//For YEAR, compares only the latest complete fiscal year and the year before that. 
				//For QUARTER, compares only latest complete quarter and the quarter before that. 
				public Boolean hasIncreasingEPS(Period period) throws IOException, InterruptedException {
					Map<String, String> epsByPeriod = null;
					
					switch (period) { 
						case YEAR: 		epsByPeriod = webScraper.getEPSByYears();
										System.out.println(epsByPeriod);
										break;
						case QUARTER: 	epsByPeriod = webScraper.getEPSByQuarters();
										break;
						default: 		System.out.println("Invalid period entered: " + period);
										break;
					}

					Set<String> keys = epsByPeriod.keySet();
					ArrayList<String> allQuarters = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough Quarterly Data. ");
						return null;
					} else {
						for (String k : keys) {
							allQuarters.add(k);
						}
						
						String unParsedLatestPeriodEPS = epsByPeriod.get(allQuarters.get(allQuarters.size() - 1));
						String unParsedSecondLatestPeriodEPS = epsByPeriod.get(allQuarters.get(allQuarters.size() - 2));
						System.out.println(unParsedLatestPeriodEPS);
						System.out.println(unParsedSecondLatestPeriodEPS);
						
						Double latestPeriodEPS = Double.parseDouble(unParsedLatestPeriodEPS);
						Double secondLatestPeriodEPS = Double.parseDouble(unParsedSecondLatestPeriodEPS);
						
						if (latestPeriodEPS > secondLatestPeriodEPS) {
							return true;
						} else if (latestPeriodEPS < secondLatestPeriodEPS) {
							return false;
						}
					}
					return null;
				}
				
				/** ********** **/
				/** End: 2 EPS **/
				/** ********** **/
}
