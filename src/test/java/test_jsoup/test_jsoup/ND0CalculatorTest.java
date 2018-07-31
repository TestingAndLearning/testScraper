package test_jsoup.test_jsoup;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ND0CalculatorTest {
	String tickerSymbol = "MSFT";
	ND0Calculator ndCalc = new ND0Calculator(tickerSymbol);
	
	public ND0CalculatorTest() throws IOException, InterruptedException {
		
	}

	/** *************************** **/
	/** hasIncreasingRevenue Start: **/
	/** *************************** **/
	
	@Test
	public void testHasIncreasingRevenue_yearShouldNotBeNull() throws IOException, InterruptedException {
		boolean shouldNotBeNull = ndCalc.hasIncreasingRevenue(Period.YEAR);
		assertNotNull(shouldNotBeNull);
	}
	
	@Test
	public void testHasIncreasingRevenue_quarterShouldNotBeNull() throws IOException, InterruptedException {
		boolean shouldNotBeNull = ndCalc.hasIncreasingRevenue(Period.QUARTER);
		assertNotNull(shouldNotBeNull);
	}
	
	/** ******************************* **/
	/** hasIncreasingRevenue End: **/
	/** ******************************* **/
	
	/** *********************** **/
	/** hasIncreasingEPS Start: **/
	/** *********************** **/
	
	@Test
	public void testHasIncreasingEPS_yearShouldNotBeNull() throws IOException, InterruptedException {
		boolean shouldNotBeNull = ndCalc.hasIncreasingEPS(Period.YEAR);
		assertNotNull(shouldNotBeNull);
	}
	
	@Test
	public void testHasIncreasingEPS_quarterShouldNotBeNull() throws IOException, InterruptedException {
		boolean shouldNotBeNull = ndCalc.hasIncreasingEPS(Period.QUARTER);
		assertNotNull(shouldNotBeNull);
	}
	
	/** ********************* **/
	/** hasIncreasingEPS End: **/
	/** ********************* **/
	
	/** ***************************** **/
	/** revenuePercentIncrease Start: **/
	/** ***************************** **/
	@Test
	public void testRevenuePercentIncrease_year() throws IOException, InterruptedException {
		Double percentIncrease = ndCalc.revenuePercentIncrease(Period.YEAR);
		String regex = "\\d.*";
		if (!percentIncrease.toString().matches(regex)) {
			System.out.print("testRevenuePercentIncrease_year: ");
			System.out.println("Revenue increase/decrease percent for " + tickerSymbol +" is: "+ percentIncrease +" and does not match the regex: " + regex);
		}
		assertTrue(percentIncrease.toString().matches(regex));
	}
	
	@Test
	public void testRevenuePercentIncrease_quarter() throws IOException, InterruptedException {
		Double percentIncrease = ndCalc.revenuePercentIncrease(Period.QUARTER);
		String regex = "\\d.*";
		if (!percentIncrease.toString().matches(regex)) {
			System.out.print("testRevenuePercentIncrease_quarter: ");
			System.out.println("Revenue increase/decrease percent for " + tickerSymbol +" is: "+ percentIncrease +" and does not match the regex: " + regex);
		}
		assertTrue(percentIncrease.toString().matches(regex));
	}
	
	/** *************************** **/
	/** revenuePercentIncrease End: **/
	/** *************************** **/
}
