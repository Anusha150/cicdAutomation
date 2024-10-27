package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//To  create report in HTML format
public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		//ExtentReport, ExtentSparkReporter
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Web Automation");
		reporter.config().setDocumentTitle("Test Results");
		
		//ExtentReports
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Anusha");
		return extent;
	}

}
