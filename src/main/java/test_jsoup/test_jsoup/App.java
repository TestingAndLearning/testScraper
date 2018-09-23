package test_jsoup.test_jsoup;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import test_sql.StockDAO;

public class App {
	
    public static void main( String[] args ) throws IOException, InterruptedException
    {   	
		//ND2EPS nd2EPS = new ND2EPS("MSFT");
		//Document incomeDocument = Jsoup.connect("https://www.marketwatch.com/investing/stock/MSFT/financials").get();
		//nd2EPS.getEPSPeriodHeader(incomeDocument, 0);
		//nd2EPS.getEPSPeriodValue(incomeDocument, 0);
	
		//ND3ROE nd3ROE = new ND3ROE("MSFT");
		//System.out.println(nd3ROE.getROEByYears());
		//System.out.println(nd3ROE.getROEByQuarters());
		
		//ND0Calculator ndCalc = new ND0Calculator("MSFT");
		//ndCalc.epsPercentIncrease(Period.YEAR);
		//ndCalc.epsPercentIncrease(Period.QUARTER);
		 
		//ND0Calculator ndCalc = new ND0Calculator("AMD");
		//System.out.println(ndCalc.hasIncreasingEPS(Period.YEAR));
		//System.out.println(ndCalc.hasIncreasingEPS(Period.QUARTER)); 
    	
		//ND0Calculator ndCalc = new ND0Calculator("AMD");
		//System.out.println(ndCalc.roePercentIncrease(Period.YEAR));
		//System.out.println(ndCalc.roePercentIncrease(Period.QUARTER));
    	
		//ND4AnalystRecommendation nd4 = new ND4AnalystRecommendation("MSFT");
		//System.out.println(nd4.getAnalystRecommendation());
	
		//ND11Insider nd11 = new ND11Insider("MSFT");
		//System.out.println(nd11.getSharesPurchasedInLastThreeMonths());
		//System.out.println(nd11.getSharesSoldInLastThreeMonths());
    	
    	/*
    	String symbol = "MSFT";
    	NDWrapper ndCalc = new NDWrapper(symbol);
    	System.out.println("Ticker: " + symbol + "\t Current Price: " + ndCalc.getCurrentPrice() + "\t PE: " + ndCalc.getPERatio() + "\t Volume: " + ndCalc.getVolume() + "\t Has Positive Latest Income?: " + ndCalc.hasPositiveLatestIncome());
    	
    	System.out.println("1A. Has Increasing Revenue For Past Year: " + ndCalc.hasIncreasingRevenue(Period.YEAR));
    	
    	
    	System.out.println("2A. Has Increasing EPS For Past Year: " + ndCalc.hasIncreasingEPS(Period.YEAR));
    	System.out.println("2B. Has Increasing EPS For Past Quarter: " + ndCalc.hasIncreasingEPS(Period.QUARTER));
    	
    	System.out.println("3A. Has Increasing ROE For Past Year: " + ndCalc.hasIncreasingROE(Period.YEAR));
    	System.out.println("3B. Has Increasing ROE For Past Quarter: " + ndCalc.hasIncreasingROE(Period.QUARTER));
    	
    	System.out.println("4A. Analysts Recommend: " + ndCalc.analystRecommendationIsPositive() + " (" + ndCalc.getAnalystRecommendation() + ")");
    	System.out.println("5A. Has More Buys Than Sells: " + ndCalc.hasMoreInsiderBuysThanSells());
    	
    	System.out.println("6A. Is in industry: " + ndCalc.getIndustry());
    	System.out.println("6B. Is in sector: " + ndCalc.getSector());
    	*/
    	
    	/*
    	String symbol = "MSFT";
    	NDWrapper stockCalc = new NDWrapper(symbol);
    	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    	LocalDateTime now = LocalDateTime.now();
    	
    	System.out.println("Ticker: "+ symbol);
    	System.out.println("Price: "+ stockCalc.getCurrentPrice());
    	System.out.println("PE: "+ stockCalc.getPERatio());
    	System.out.println("Volume: "+ stockCalc.getVolume());
    	System.out.println("PositiveLatestIncome: "+ stockCalc.hasPositiveLatestIncome());
    	System.out.println("HasIncreasingAnnualRevenue: "+ stockCalc.hasIncreasingRevenue(Period.YEAR));
    	System.out.println("AnnualRevenueIncreasePrecent: "+ stockCalc.revenuePercentIncrease(Period.YEAR));
    	System.out.println("HasIncreasingAnnualEPS: "+ stockCalc.hasIncreasingEPS(Period.YEAR));
    	System.out.println("AnnualEPSIncreasePrecent: "+ stockCalc.epsPercentIncrease(Period.YEAR));
    	System.out.println("HasIncreasingAnnualROE: "+ stockCalc.hasIncreasingROE(Period.YEAR));
    	System.out.println("AnnualROEIncreasePrecent: "+ stockCalc.roePercentIncrease(Period.YEAR));
    	System.out.println("AnalystsRecommend: "+ stockCalc.getAnalystRecommendation());
    	System.out.println("HasMoreInsiderBuys: "+ stockCalc.hasMoreInsiderBuysThanSells());
    	System.out.println("Industry: "+ stockCalc.getIndustry());
    	System.out.println("Sector: "+ stockCalc.getSector());
    	System.out.println("HasIncreasingQuarterRevenue: "+ stockCalc.hasIncreasingRevenue(Period.QUARTER));
    	System.out.println("QuarterRevenueIncreasePrecent: "+ stockCalc.revenuePercentIncrease(Period.QUARTER));
    	System.out.println("HasIncreasingQuarterEPS: "+ stockCalc.hasIncreasingEPS(Period.QUARTER));
    	System.out.println("QuarterEPSIncreasePrecent: "+ stockCalc.epsPercentIncrease(Period.QUARTER));
    	System.out.println("HasIncreasingQuarterROE: "+ stockCalc.hasIncreasingROE(Period.QUARTER));
    	System.out.println("QuarterROEIncreasePrecent: "+ stockCalc.roePercentIncrease(Period.QUARTER));
    	System.out.println("DateTime: " + dateTimeFormatter.format(now));
    	*/
    	
    	/*
    	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    	LocalDateTime todaysDate = LocalDateTime.now();
    	System.out.println(dateTimeFormatter.format(todaysDate));
    	*/
    	
    	
    	StockDAO db = new StockDAO("Test123a");
    	db.createNewDatabase();
    	db.createTodaysTable();
    	//db.insert("as", 32);
		

    	//ND0Calculator ndCalc = new ND0Calculator("CMG");
    	//System.out.println(ndCalc.industry());
    	//System.out.println(ndCalc.sector());
    }
}
