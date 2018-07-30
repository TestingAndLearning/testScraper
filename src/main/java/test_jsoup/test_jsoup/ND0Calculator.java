package test_jsoup.test_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ND0Calculator {
				String tickerSymbol;
				ND3ROE webScraper;
				
				public ND0Calculator(String tickerSymbol) throws IOException, InterruptedException{
					this.tickerSymbol = tickerSymbol;
					webScraper = new ND3ROE(tickerSymbol);
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
										break;
						case QUARTER: 	revenueByPeriod = webScraper.getRevenueByQuarters();
										break;
						default: 		System.out.println("Invalid period entered: " + period);
										break;
					}

					Set<String> keys = revenueByPeriod.keySet();
					ArrayList<String> allPeriods = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough " + period + " Data. ");
						return null;
					} else {
						for (String k : keys) {
							allPeriods.add(k);
						}
						
						String unParsedLatestPeriodRevenue = revenueByPeriod.get(allPeriods.get(allPeriods.size() - 1));
						String unParsedSecondLatestPeriodRevenue = revenueByPeriod.get(allPeriods.get(allPeriods.size() - 2));
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
				
				//Divides latest period by second latest period to get the percent increase/decrease. 
				Double revenuePercentIncrease(Period period) throws IOException, InterruptedException {
					Map<String, String> revenueByPeriod = null;
					
					switch (period) { 
						case YEAR: 		revenueByPeriod = webScraper.getRevenueByYears();	
										break;
						case QUARTER: 	revenueByPeriod = webScraper.getRevenueByQuarters();
										System.out.println(revenueByPeriod);
										break;
						default: 		System.out.println("Invalid period entered: " + period);
										break;
					}

					Set<String> keys = revenueByPeriod.keySet();
					ArrayList<String> allPeriods = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough " + period + " Data. ");
						return null;
					} else {
						for (String k : keys) {
							allPeriods.add(k);
						}
						
						String unParsedLatestPeriodRevenue = revenueByPeriod.get(allPeriods.get(allPeriods.size() - 1));
						String unParsedSecondLatestPeriodRevenue = revenueByPeriod.get(allPeriods.get(allPeriods.size() - 2));
						Long latestPeriodRevenue = webScraper.getParsedAlphaNumericMoney(unParsedLatestPeriodRevenue);
						Long secondLatestPeriodRevenue = webScraper.getParsedAlphaNumericMoney(unParsedSecondLatestPeriodRevenue);
						
						//Would be something like 1.2467 or 0.8550
						System.out.println("Latest Period is: " + latestPeriodRevenue + ", Second Latest is: " + secondLatestPeriodRevenue);
						Double unParsedPercentIncrease = webScraper.useDecimalPlaces((double)latestPeriodRevenue/(double)secondLatestPeriodRevenue, 4);
						System.out.println((double)latestPeriodRevenue + "/" + (double)secondLatestPeriodRevenue + " = " + unParsedPercentIncrease); 
						
						if (unParsedPercentIncrease >= 1) {
							String percentIncreaseText;
							if (unParsedPercentIncrease >=2) {
								percentIncreaseText = Double.toString((unParsedPercentIncrease-1)*100); //Changes 2.24670000 to 124.670000 to represent percent.
							} else {
								percentIncreaseText = Double.toString(unParsedPercentIncrease*100).substring(1); //Changes 1.2467 to 24.67. 
							}
							Double percentIncrease = webScraper.useDecimalPlaces(Double.parseDouble(percentIncreaseText), 2);
							System.out.println("Increasing: " + percentIncrease);
							return percentIncrease;
							
						} else if (unParsedPercentIncrease < 1) {
							Double differenceIncrease = 1 - unParsedPercentIncrease;
							String percentIncreaseText = Double.toString(differenceIncrease*100); //Changes 0.8550 to 14.5000000...
							Double percentIncrease = webScraper.useDecimalPlaces(Double.parseDouble(percentIncreaseText)*(-1), 2); //Changes 14.5000000 to 14.50
							System.out.println("Decreasing: " + percentIncrease);
							return percentIncrease;
						}
						
					}
					return null;
				}
				
				/** ************** **/
				/** End: 1 Revenue **/
				/** ************** **/
				
				/** ************ **/
				/** Start: 2 EPS **/
				/** ************ **/
				
				//For YEAR, compares only the latest complete fiscal year and the year before that. 
				//For QUARTER, compares only latest complete quarter and the quarter before that. 
				public Boolean hasIncreasingEPS(Period period) throws IOException, InterruptedException {
					Map<String, String> epsByPeriod = null;
					
					switch (period) { 
						case YEAR: 		epsByPeriod = webScraper.getEPSByYears();
										//System.out.println(epsByPeriod);
										break;
						case QUARTER: 	epsByPeriod = webScraper.getEPSByQuarters();
										break;
						default: 		System.out.println("Invalid period entered: " + period);
										break;
					}

					Set<String> keys = epsByPeriod.keySet();
					ArrayList<String> allPeriods = new ArrayList<>();
					
					if (keys.size() < 2) {
						System.out.println("Not Enough " + period + " Data. ");
						return null;
					} else {
						for (String k : keys) {
							allPeriods.add(k);
						}
						
						String unParsedLatestPeriodEPS = epsByPeriod.get(allPeriods.get(allPeriods.size() - 1));
						String unParsedSecondLatestPeriodEPS = epsByPeriod.get(allPeriods.get(allPeriods.size() - 2));
						
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
