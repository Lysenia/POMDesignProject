package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProcessingOrderPage {
	
	public ProcessingOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="ctl00_MainContent_login_button")
	public WebElement loginButton;
	
	@FindBy(linkText="Order")
	public WebElement orderTab;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement product;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;
	
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
	public WebElement pricePerUnit;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
	public WebElement discount;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_txtTotal")
	public WebElement total;
	

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement submitButton;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_txtName']")
	public WebElement customerName;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox2']")
	public WebElement street;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox3']")
	public WebElement city;
	

	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox4']")
	public WebElement state;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox5']")
	public WebElement zip;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_cardList_0']")
	public WebElement cardType;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox6']")
	public WebElement cardNumber;
	
	@FindBy(xpath = "//input[@id='ctl00_MainContent_fmwOrder_TextBox1']")
	public WebElement expirationDate;
	
	@FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement processButton;
	
	@FindBy(linkText="View all orders")
	public WebElement viewAllOrders;
	
	
}
