package techflare.swag;

import java.io.File;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManger {
	public static ExtentReports createInstance(String testclassName) {
		File reportDir=new File(System.getProperty("user.dir")+"/target/Report");
		if(reportDir.exists()) {
			for(File file:reportDir.listFiles()) {
				file.delete();
			}
		}
		
		String timetamp=new SimpleDateFormat("yyyy.mm.dd_HH.mm.SS").format(new Date());
		String reportpath=System.getProperty("user.dir")+"/target/Report/ExtentReport_"+testclassName +"_"+ timetamp +".html";
		
		ExtentSparkReporter htmlReporter=new ExtentSparkReporter(reportpath);
		htmlReporter.config().setDocumentTitle("Automatipon Report");
		htmlReporter.config().setReportName(testclassName + "Test Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester", "Divya");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows11");
		extent.setSystemInfo("Browser", "Google Chrome");
		 
		
		return extent;
		
	
}
}