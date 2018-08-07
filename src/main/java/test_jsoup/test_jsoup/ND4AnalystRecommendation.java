package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class ND4AnalystRecommendation extends ND3ROE{

	public ND4AnalystRecommendation(String tickerSymbol) throws IOException, InterruptedException {
		super(tickerSymbol);
		analystDocument = Jsoup.connect(analystUrl).get();
		
	}
	
	public String getAnalystRecommendation() {
		Element analystRecommendationNode = analystDocument.select("table.ratings > tbody > tr").get(5).getElementsByClass("current").get(0);
		String analystRecommendation = analystRecommendationNode.text().replaceAll("[()]\"", "").toUpperCase(); //Active value may have double quotes around it.  
		return analystRecommendation;
	}

}
