package rahulshettyacademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productsName ="ZARA COAT 3";
	@Test(dataProvider= "getData",groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		
		ProductCatalogue productscatalogue =login.loginApplication(input.get("email"), input.get("pass"));
		List <WebElement> products = productscatalogue.getProductList();
		productscatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productscatalogue.goToCartPage();
		Thread.sleep(3000);
		Boolean match =cartPage.VerifyProductsDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
	
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productscatalogue =login.loginApplication("anusha123@gmail.com", "Anush@123");
		OrderPage orderpage = productscatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productsName));
	}

	
	
	//Advanced Type3 
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String, String>> data = getJsonDataToMap("C://Users//Dell//eclipse-workspace//SeleniumFrameworkDesign//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	// Type 2
//	@DataProvider
//	public Object[][] getData(){
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email","anusha123@gmail.com");
//		map.put("pass","Anush@123");
//		map.put("productName","ZARA COAT 3");
//	
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("pass", "Iamking@000");
//		map1.put( "productName","ADIDAS ORIGINAL" );
//		return new Object[][] {{map},{map1}};
//		
//	}
	//Basic Type1
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"anusha123@gmail.com","Anush@123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}
	
	

}
