package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Hello world!
 *
 */
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
    	
    	String symbol = "MSFT";
    	ND0Calculator ndCalc = new ND0Calculator(symbol);
    	System.out.println("Ticker: " + symbol + "\t Current Price: " + ndCalc.getCurrentPrice() + "\t PE: " + ndCalc.getPERatio() + "\t Volume: " + ndCalc.getVolume());
    	
    	System.out.println("1A. Has Increasing Revenue For Past Year: " + ndCalc.hasIncreasingRevenue(Period.YEAR));
    	System.out.println("1B. Has Increasing Revenue For Past Quarter: " + ndCalc.hasIncreasingRevenue(Period.QUARTER));
    	
    	System.out.println("2A. Has Increasing EPS For Past Year: " + ndCalc.hasIncreasingEPS(Period.YEAR));
    	System.out.println("2B. Has Increasing EPS For Past Quarter: " + ndCalc.hasIncreasingEPS(Period.QUARTER));
    	
    	System.out.println("3A. Has Increasing ROE For Past Year: " + ndCalc.hasIncreasingROE(Period.YEAR));
    	System.out.println("3B. Has Increasing ROE For Past Quarter: " + ndCalc.hasIncreasingROE(Period.QUARTER));
    	
    	System.out.println("4A. Analysts Recommend: " + ndCalc.analystRecommendationIsPositive() + " (" + ndCalc.getAnalystRecommendation() + ")");
    	System.out.println("5A. Has More Buys Than Sells: " + ndCalc.hasMoreInsiderBuysThanSells());
    	
    	System.out.println("6A. Is in industry: " + ndCalc.getIndustry());
    	System.out.println("6B. Is in sector: " + ndCalc.getSector());
    	

    	//ND0Calculator ndCalc = new ND0Calculator("CMG");
    	//System.out.println(ndCalc.industry());
    	//System.out.println(ndCalc.sector());
    }
}
