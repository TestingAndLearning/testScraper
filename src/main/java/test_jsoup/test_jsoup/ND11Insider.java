package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class ND11Insider extends ND4AnalystRecommendation {

	public ND11Insider(String tickerSymbol) throws IOException, InterruptedException {
		super(tickerSymbol);
		insiderDocument = Jsoup.connect(insiderUrl).get();
	}
	
	public int getSharesPurchasedInLastThreeMonths() {
		Element insiderTransactionNode = insiderDocument.getElementById("insiderTransactionSummary").getElementsByClass("shares").select("div").get(0);
		int sharesPurchasedInLastThreeMonths = Integer.parseInt(insiderTransactionNode.text().replace(",", "")); //Active value may have double quotes around it.  
		return sharesPurchasedInLastThreeMonths;
	}
	
	public int getSharesSoldInLastThreeMonths() {
		Element insiderTransactionNode = insiderDocument.getElementById("insiderTransactionSummary").getElementsByClass("shares").select("div").get(1);
		int sharesSoldInLastThreeMonths = Integer.parseInt(insiderTransactionNode.text().replace(",", "")); //Active value may have double quotes around it.  
		return sharesSoldInLastThreeMonths;
	}

}
