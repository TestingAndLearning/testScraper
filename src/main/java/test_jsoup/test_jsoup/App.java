package test_jsoup.test_jsoup;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        WebScraper webScraper = new WebScraper("MSFT");
        //webScraper.getCurrentPrice();
        webScraper.getRevenueByYears();
        webScraper.getRevenueByQuarters();
        
        WebScraper webScraper2 = new WebScraper("DBX");
        //webScraper2.getCurrentPrice();
        webScraper2.getRevenueByYears();
        webScraper2.getRevenueByQuarters();
    }
}
