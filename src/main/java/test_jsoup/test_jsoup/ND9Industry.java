package test_jsoup.test_jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ND9Industry extends ND4AnalystRecommendation
{

	public ND9Industry(String tickerSymbol) throws IOException, InterruptedException
	{
		super(tickerSymbol);
	}
	
	/** **************************** **/
	/** ND #9 Industry Section START **/
	/** **************************** **/
	
	//These functions only take care of getting the industry of the stock. Calculations for average industry values will be done after stock is stored in database. 
	public String getIndustry() throws InterruptedException {
		Element industryNode = profileDocument.select("div.twowide.addgutter").get(0).select("div.section").get(1).select("p").get(1);
		String industry = industryNode.text();
		return industry;
	}
	
	public String getSector() throws InterruptedException {
		Element sectorNode = profileDocument.select("div.twowide.addgutter").get(0).select("div.section").get(2).select("p").get(1);
		String sector = sectorNode.text();
		return sector;
	}
	
	/** ************************** **/
	/** ND #9 Industry Section END **/
	/** ************************** **/
}
