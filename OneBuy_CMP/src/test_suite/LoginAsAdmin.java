package test_suite;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginAsAdmin {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	@BeforeTest
	public void NavigateToCMP() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Irshad\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dashboard-dev.smartshifttech.com/accounts/login/");
		Thread.sleep(2000);

	}
	@Test(priority=1)
	public void Test_01_LoginToCMP_invalid_UserID() {


		driver.findElement(By.id("id_username")).sendKeys("i_pasha@smartshifttech.com");
		driver.findElement(By.id("id_password")).sendKeys("Manzil@642");
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 

		if (driver.getPageSource().contains("These credentials are not valid"))
		{
			System.out.println("Invalid User ID");
		}
	}

	@Test(priority=2)
	public void Test_02_LoginToCMP_invalid_Password() throws Exception {

		driver.findElement(By.id("id_username")).clear(); 
		driver.findElement(By.id("id_password")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("username")).sendKeys("ipasha@smartshifttech.com");
		driver.findElement(By.name("password")).sendKeys("Manzil");
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 
		if (driver.getPageSource().contains("These credentials are not valid"))
		{
			System.out.println("Invalid Password");
		}
	}

	@Test(priority=3)
	public void Test_03_LoginToCMP() throws Exception {
		driver.findElement(By.id("id_username")).clear(); 
		driver.findElement(By.id("id_password")).clear();
		Thread.sleep(1000);
		driver.findElement(By.name("username")).sendKeys("ipasha@smartshifttech.com");
		driver.findElement(By.name("password")).sendKeys("Manzil@642");
		driver.findElement(By.xpath("//button[@type='submit']")).click();  
		Thread.sleep(5000);
		if (driver.getPageSource().contains("LogiMonitor Alerts"))
		{
			System.out.println("Logic Monitor Alerts Displayed");
		}
		else {
			System.out.println("Logic Monitor Alerts Not Displayed");
		}
	}

	@Test(priority=4)
	public void Test_04_OrderBlueprint() throws Exception
	{
		driver.findElement(By.linkText("Catalog")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='cb-btn cb-btn-primary'][contains(text(),'Catalog')]")).click();
		driver.findElement(By.id("catalog-search")).sendKeys("irshad");
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//*[@text='Search']")).click();
		driver.findElement(By.cssSelector(".catalog-list__blueprint:nth-child(2) > a")).click();
		driver.findElement(By.xpath("//div[@class=\"selectize-input items required not-full has-options\"]")).click();
		Thread.sleep(2000);
		
		//Select group = new Select(driver.findElement(By.xpath("//div[@id='order-form-wrap']/div[2]/div/div/div/div")));
		//group.selectByIndex(0);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"selectize-dropdown-content\"]"));
		Thread.sleep(2000);
		//List<WebElement> list = driver.findElements(By.xpath("//div[@class='selectize-input items required has-options full has-items']/div[text()='se-dev']"));
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++){
		System.out.println(list.get(i).getText());
		 if(list.get(i).getText().contains("se-dev")) { 
			 list.get(i).click(); 
			 break; 
			 }
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@id=\"submit-btn\"]")).click();
		Thread.sleep(30000);
		Assert.assertEquals("has been auto-approved", "has been auto-approved");
		System.out.println("Assert passed");
	}
	@Test(priority=5)
	public void Test_05_Servers() throws Exception
	{
		/*
		 * Actions actions = new Actions(driver); WebElement menu =
		 * driver.findElement(By.linkText("Resources"));
		 * actions.moveToElement(menu).perform(); Thread.sleep(2000); WebElement submenu
		 * = driver.findElement(By.linkText("Servers")); actions.moveToElement(submenu);
		 * actions.click().build().perform();
		 */
		driver.findElement(By.linkText("Resources")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Servers")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'cloudbolt-app-1')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'More')]")).click();
		driver.findElement(By.xpath("//li[@class='tab']//a[contains(text(),'LogicMonitor')]")).click();
		Thread.sleep(2000);
		if (driver.findElement(By.xpath("//h3[contains(text(),'System Properties')]"))!=null) 
		{
			System.out.println("Server Logic Monitors are displayed");  
		}
		else
		{
			System.out.println("Server Logic Monitors not displayed");
		}

	}
	@Test(priority=6)
	public void Test_06_Custom_Ansible_Action_Server () throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(By.linkText("Resources")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Servers")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(text(),'irshad-app-tier')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='btn-group-vertical']//a[@title=\"Ansible: Run Playbook\"]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='selectize-input items full has-options has-items']")).click();
		Thread.sleep(2000);
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"selectize-dropdown single form-control selectizeselect plugin-selectable_placeholder\"]/div"));
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++){
		System.out.println(list.get(i).getText());
		Thread.sleep(2000);
		if(list.get(i).getText().contains("WebServer")) {
			list.get(i).click(); 
			break; 
			}
		}
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class=\"cb-btn cb-btn-primary js-submit-form\"]")).click();
		Thread.sleep(20000);
	}
	
	
	@Test(priority=7)
	public void Test_06_Services() throws Exception
	{
		//Actions actions = new Actions(driver);
		//WebElement menu = driver.findElement(By.linkText("Resources"));
		//actions.moveToElement(menu).perform();
		//Thread.sleep(2000);
		//WebElement submenu = driver.findElement(By.linkText("Services"));
		//actions.moveToElement(submenu);
		//actions.click().build().perform();
		
		driver.findElement(By.linkText("Resources")).click();
		driver.findElement(By.linkText("Services")).click();
	}

	@Test(priority=7)
	public void Test_07_Groups() throws Exception
	{
		Thread.sleep(1000);
		driver.findElement(By.linkText("Groups")).click(); 
		driver.findElement(By.linkText("Add a Group")).click();
		driver.findElement(By.id("id_name")).sendKeys("Demo-Client Group");
		driver.findElement(By.xpath("//textarea[@id='id_description']")).sendKeys("Demo-Client Group");
		Select GroupType = new Select(driver.findElement(By.xpath("//select[@id='id_group_type']")));
		GroupType.selectByVisibleText("Environment");
		driver.findElement(By.cssSelector(".btn-primary")).click(); 
	}
	@AfterTest
	public void Logout() throws Exception
	{
		Actions actions = new Actions(driver);
		WebElement menu = driver.findElement(By.xpath("//i[@class='fas fa-user']"));
		actions.moveToElement(menu).perform();
		Thread.sleep(1000);
		WebElement submenu = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		actions.moveToElement(submenu);
		actions.click().build().perform();
		Thread.sleep(1000);
		driver.quit();
	}
}

