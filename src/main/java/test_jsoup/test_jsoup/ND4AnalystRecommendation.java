package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.nodes.Element;

public class ND4AnalystRecommendation extends ND3ROE{

	public ND4AnalystRecommendation(String tickerSymbol) throws IOException, InterruptedException {
		super(tickerSymbol);
		
	}
	
	public String getAnalystRecommendation() {
		Element analystRecommendationNode = mainDocument.getElementsByClass("analyst__rating").get(0).getElementsByClass("active").get(0);
		String analystRecommendation = analystRecommendationNode.text().replaceAll("[()]\"", "").toUpperCase(); //Active value may have double quotes around it.  
		return analystRecommendation;
	}

}
