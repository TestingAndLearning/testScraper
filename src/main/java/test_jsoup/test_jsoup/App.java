package test_jsoup.test_jsoup;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        ND1Revenue ND1Revenue = new ND1Revenue("MSFT");
        System.out.println(ND1Revenue.getRevenueByYears());
        System.out.println(ND1Revenue.getRevenueByQuarters());
        
        ND1Revenue ND1Revenue2 = new ND1Revenue("DBX");
        System.out.println(ND1Revenue2.getRevenueByYears());
        System.out.println(ND1Revenue2.getRevenueByQuarters());
    }
}
