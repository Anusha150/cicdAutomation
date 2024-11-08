package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LoginPage;

public class BaseTest{
	public WebDriver driver;
	public LoginPage login;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Dell\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis); 
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser") : prop.getProperty("browser");
//		String browserName = prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
			}
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//Firefox
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
			
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//Edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
		//JSON to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
		
	}
	
	//Taking Screenshot and saving in a file
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" +testcaseName+ ".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//" +testcaseName+ ".png";		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.GoTo();
		return login;
	}
	@AfterMethod(alwaysRun=true)
	public  void tearDown() {
		driver.close();
	}
}
