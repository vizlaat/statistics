package vzsolt.statistics.selenium;

import java.util.HashMap;
import java.util.Map;

public class TestData {

	public TestData() {
		// TODO Auto-generated constructor stub
	}
	
	public static final String CHROME_DRIVER =  "C:\\selenium\\chrome-driver\\chromedriver.exe" ;
	public static final String FIREFOX_DRIVER = "C:\\selenium\\firefox-driver\\geckodriver.exe";
	public static final String IE_DRIVER = "C:\\selenium\\IEDriverServer\\IEDriverServer.exe";
	public static final String EDGE_DRIVER = "C:\\selenium\\edge-driver\\MicrossoftWebDriver.exe";
	public static final String OPERA_DRIVER = "C:\\selenium\\opera-driver\\operadriver.exe";
	public static final String BASE_URL = "http://localhost:8090/";
	public static final String SAMPLE_DATA = "C:\\selenium\\sample-data.csv";
	public static final double DELTA = 0.001;
	public static final double UNFILTERED_CORRELATION1 = 0.748;
	public static final double WOMAN_CORRELATION1 = 0.882;
	public static final double FRANCE_CORRELATION1 = 0.758;
	public static final double HUNGARIAN_MAN_CORRELATION1 = 0.967;
	
	
	
	public static final Map<String,String> WOMAN_AVERAGES;
	static
	{
		WOMAN_AVERAGES = new HashMap<>();
		WOMAN_AVERAGES.put("average-0", "9.5");
		WOMAN_AVERAGES.put("average-2", "26.5");
		WOMAN_AVERAGES.put("average-3", "2350");
		WOMAN_AVERAGES.put("average-4", "894.833");
	}
	
	public static final Map<String,String> UNFILTERED_AVERAGES;
	static
	{
		UNFILTERED_AVERAGES = new HashMap<>();
		UNFILTERED_AVERAGES.put("average-0", "10");
		UNFILTERED_AVERAGES.put("average-2", "27");
		UNFILTERED_AVERAGES.put("average-3", "2800");
		UNFILTERED_AVERAGES.put("average-4", "1401.211");
	}
	
	public static final Map<String,String> FRANCE_AVERAGES;
	static
	{
		FRANCE_AVERAGES = new HashMap<>();
		FRANCE_AVERAGES.put("average-0", "11.6");
		FRANCE_AVERAGES.put("average-2", "28.6");
		FRANCE_AVERAGES.put("average-3", "3760");
		FRANCE_AVERAGES.put("average-4", "2485.6");
	}
	
	public static final Map<String,String> HUNGARIAN_MAN_AVERAGES;
	static
	{
		HUNGARIAN_MAN_AVERAGES = new HashMap<>();
		HUNGARIAN_MAN_AVERAGES.put("average-0", "7.8");
		HUNGARIAN_MAN_AVERAGES.put("average-2", "24.8");
		HUNGARIAN_MAN_AVERAGES.put("average-3", "2320");
		HUNGARIAN_MAN_AVERAGES.put("average-4", "1156.8");
	}
	
	
	
	
	
	
}
