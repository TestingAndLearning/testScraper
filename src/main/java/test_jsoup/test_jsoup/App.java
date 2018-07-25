package test_jsoup.test_jsoup;

import java.io.IOException;

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
    	
    		ND2EPS nd2EPS = new ND2EPS("MSFT");
    		nd2EPS.getEPSPeriodHeader(document, index);
    		nd2EPS.getEPSPeriodValue(document, index);
    }
}
