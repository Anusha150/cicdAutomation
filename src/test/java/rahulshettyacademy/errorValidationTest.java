package rahulshettyacademy;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class errorValidationTest extends BaseTest {
	
	@Test(groups= {"errorHandling"}, retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)
	public void LoginErrorValidtion() throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		String productName ="ZARA COAT 3";
		login.loginApplication("anusha1233@gmail.com", "Anush@123");
		Assert.assertEquals("Incorrect email or password.", login.getErrorMessage());
		
	}
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName ="ZARA COAT 3";
		ProductCatalogue productscatalogue =login.loginApplication("anusha123@gmail.com", "Anush@123");
		List <WebElement> products = productscatalogue.getProductList();
		productscatalogue.addProductToCart(productName);
		CartPage cartPage = productscatalogue.goToCartPage();
		Boolean match =cartPage.VerifyProductsDisplay(productName);
		Assert.assertTrue(match);		
	}
	

}