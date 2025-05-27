package techflare.swag;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreport_manager {
	    private static ExtentReports extent;

	    public static ExtentReports getReportInstance() {
	        if (extent == null) {
	            String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
	            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	            reporter.config().setReportName("SauceDemo Automation Report");
	            reporter.config().setDocumentTitle("Test Results");

	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	            extent.setSystemInfo("Tester", "Divya");
	        }
	        return extent;
	    }
	}

