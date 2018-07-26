package test_jsoup.test_jsoup;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

import org.junit.Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	NDScraperBaseTest.class,
    ND0CalculatorTest.class,
    ND1RevenueTest.class,
    ND2EPSTest.class,
    NDScraperBaseTest.class
})

public class NDAllTests {

}
