package test_jsoup.test_jsoup;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

import org.junit.Test;

//Can test with MSFT, SQ, SNAP, SONOS, HUBB, LCNB

@RunWith(Suite.class)
@Suite.SuiteClasses({
	NDCoreTest.class,
    NDWrapperTest.class,
    ND1RevenueTest.class,
    ND2EPSTest.class,
    ND3ROETest.class,
    ND4AnalystRecommendationTest.class,
    ND9IndustryTest.class,
    ND11InsiderTest.class
})

public class NDAllTests {

}
