package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import pages.AllOrdersPage;
import pages.ProcessingOrderPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTest {

	WebDriver driver;
	WebOrdersLoginPage loginPage;
	AllOrdersPage allOrdersPage;
	ProductsPage productsPage;
	String userId = "Tester";
	String password = "test";
	ProcessingOrderPage processingOrder;
	Select select;
	
	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		System.setProperty("webdriver.chrome.driver",
				"/Users/lesia/Documents/selenium dependencies/drivers/chromedriver");
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
	}
	
	@Ignore
	@Test(description="Verify labels and tab links are displayed",priority=1)
	public void labelsVerication() {
		assertEquals(driver.getTitle(),"Web Orders Login","LoginPage is not displayed. Application is down.");
		/*  loginPage.userName.sendKeys(userId);
			loginPage.password.sendKeys(password);
		    loginPage.loginButton.click();
		*/
		loginPage.login(userId, password);
		
		allOrdersPage = new AllOrdersPage(driver);
		assertTrue(allOrdersPage.webOrders.isDisplayed(),"Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(),"List Of All Orders label is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""),"Welcome, " + userId + "!");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(),"viewAllOrders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(),"orderTab is not displayed");
	}
	
	/*
	 *  Step 1. Navigate to loginpage
		Step 2. Assert that you are on loginpage
		Step 3. Login using valid credentials
		Step 4. Click on view all products
		Step 5. Verify following products are displayed:
				MyMoney
				FamilyAlbum
				ScreenSaver
		Step 6. Verify prices and discounts :
				MyMoney	    $100 8%
				FamilyAlbum	$80	15%
				ScreenSaver	$20	10%
	 */
	
	@Ignore
	@Test(description="Verify default Products and prices")
	public void availableProductsTest() {
		assertEquals(driver.getTitle(),"Web Orders Login","LoginPage is not displayed. Application is down.");
		loginPage.login(userId, password);
		allOrdersPage = new AllOrdersPage(driver);
		allOrdersPage.viewAllProducts.click();
		productsPage = new ProductsPage(driver);
		List<String> expProducts = Arrays.asList("MyMoney","FamilyAlbum","ScreenSaver");
		List<String> actProducts = new ArrayList<>();		
		
//		productsPage.productNames.forEach(
//				elem -> actProducts.add(elem.getText())
//		);
		for(WebElement prod : productsPage.productNames) {
			actProducts.add(prod.getText());
		}
		assertEquals(actProducts,expProducts);
		
		for (WebElement row : productsPage.productsRows) {

			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch(prodData[0]) {
				case "MyMoney":
					assertEquals(prodData[1],"$100");
					assertEquals(prodData[2],"8%");
					break;
				case "FamilyAlbum":
					assertEquals(prodData[1],"$80");
					assertEquals(prodData[2],"15%");
					break;
				case "ScreenSaver":
					assertEquals(prodData[1],"$20");
					assertEquals(prodData[2],"10%");
					break;		
			}
			
		}
	
		
	} 
	
	@Test(description="Verify order processing")
	
	public void processingOrder() throws InterruptedException {
		loginPage.login(userId, password);
		//Thread.sleep(1000);
		//loginPage.loginButton.click();
		//Thread.sleep(1000);
		processingOrder=new ProcessingOrderPage(driver);
		//Thread.sleep(1000);
		processingOrder.orderTab.click();
		//Thread.sleep(1000);
		select=new Select(processingOrder.product);
		select.getFirstSelectedOption();
		processingOrder.quantity.clear();
		processingOrder.quantity.sendKeys("5");
		processingOrder.processButton.click();
		String name="Jonny Depp";
		processingOrder.customerName.sendKeys(name);
		processingOrder.street.sendKeys("4563 MonnRiver St.");
		processingOrder.city.sendKeys("Annapolis");
		processingOrder.state.sendKeys("Maryland");
		processingOrder.zip.sendKeys("42345");
		processingOrder.cardType.click();
		processingOrder.cardNumber.sendKeys("1234567891011");
		processingOrder.expirationDate.sendKeys("11/20");
		processingOrder.processButton.click();
		assertTrue(driver.findElement(By.tagName("strong")).isDisplayed(),"New order has been successfully added. is not displayed");
		processingOrder.viewAllOrders.click();
		allOrdersPage=new AllOrdersPage(driver);
		System.out.println(allOrdersPage.listNameOrder.getText());
		assertEquals(allOrdersPage.listNameOrder.getText(),name);
		
	}
	
	//logout after each test
	@AfterMethod
	public void logout() {
		allOrdersPage.logout();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}