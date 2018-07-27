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
        //ND1Revenue ND1Revenue = new ND1Revenue("MSFT");
        //System.out.println(ND1Revenue.getRevenueByYears());
        //System.out.println(ND1Revenue.getRevenueByQuarters());
        
        //ND1Revenue ND1Revenue2 = new ND1Revenue("DBX");
        //System.out.println(ND1Revenue2.getRevenueByYears());
        //System.out.println(ND1Revenue2.getRevenueByQuarters());
								
		//ND0Calculator ndCalc = new ND0Calculator("MSFT");
		//System.out.println(ndCalc.hasIncreasingRevenue(Period.YEAR));
		//System.out.println(ndCalc.hasIncreasingRevenue(Period.QUARTER)); 
    	
    		//ND2EPS nd2EPS = new ND2EPS("MSFT");
    		//Document incomeDocument = Jsoup.connect("https://www.marketwatch.com/investing/stock/MSFT/financials").get();
    		//nd2EPS.getEPSPeriodHeader(incomeDocument, 0);
    		//nd2EPS.getEPSPeriodValue(incomeDocument, 0);
    	
    		//ND3ROE nd3ROE = new ND3ROE("MSFT");
    		//System.out.println(nd3ROE.getROEByYears());
    		//System.out.println(nd3ROE.getROEByQuarters());
    		
    		ND0Calculator ndCalc = new ND0Calculator("SNAP");
    		ndCalc.revenuePercentIncrease(Period.YEAR);
    		ndCalc.revenuePercentIncrease(Period.QUARTER);
    		
    		//ND0Calculator ndCalc = new ND0Calculator("AMD");
    		//System.out.println(ndCalc.hasIncreasingEPS(Period.YEAR));
    		//System.out.println(ndCalc.hasIncreasingEPS(Period.QUARTER)); 
    }
}
