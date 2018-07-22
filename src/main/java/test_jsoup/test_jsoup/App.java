package test_jsoup.test_jsoup;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        NDScraperBase webScraper = new NDScraperBase("MSFT");
        //webScraper.getCurrentPrice();
        System.out.println(webScraper.getRevenueByYears());
        System.out.println(webScraper.getRevenueByQuarters());
        
        NDScraperBase webScraper2 = new NDScraperBase("DBX");
        //webScraper2.getCurrentPrice();
        System.out.println(webScraper2.getRevenueByYears());
        System.out.println(webScraper2.getRevenueByQuarters());
    }
}
