package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		//@FindBy to know about driver
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmails = driver.findElement(By.id("userEmail"));
//	Instead of above line we use PAGEACTORY
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String pass){
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		login.click();
		ProductCatalogue productscatalogue = new ProductCatalogue(driver);
		return productscatalogue;

	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		}
	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
