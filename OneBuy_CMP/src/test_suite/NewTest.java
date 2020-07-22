package test_suite;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTest {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
  @BeforeTest
  public void NavigateToCMP() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:\\Irshad\\Selenium\\chromedriver_win32\\chromedriver.exe");
	    driver=new ChromeDriver();
		driver.get("https://dashboard-dev.smartshifttech.com/accounts/login/");
		Thread.sleep(2000);
		
  }
  @Test(priority=1)
  public void a_LoginToCMP_invalid_UserID() {
	  
	  
	    driver.findElement(By.id("id_username")).sendKeys("i_pasha@smartshifttech.com");
		driver.findElement(By.id("id_password")).sendKeys("Manzil@642");
		driver.findElement(By.xpath("//button[@type='submit']")).click(); 
		
		  if (driver.getPageSource().contains("These credentials are not valid"))
		  {
		  System.out.println("Invalid User ID");
		  }
	}
  
  @Test(priority=2)
  public void b_LoginToCMP_invalid_Password() throws Exception {
	  
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
  public void LoginToCMP() throws Exception {
	    driver.findElement(By.id("id_username")).clear(); 
	    driver.findElement(By.id("id_password")).clear();
	    Thread.sleep(1000);
	    driver.findElement(By.name("username")).sendKeys("ipasha@smartshifttech.com");
		driver.findElement(By.name("password")).sendKeys("Manzil@642");
		driver.findElement(By.xpath("//button[@type='submit']")).click();  
		Thread.sleep(2000);
    }
  
  @Test(enabled=false)
   public void OrderBlueprint() throws Exception
   {
	  driver.findElement(By.linkText("Catalog")).click();
	  driver.findElement(By.id("catalog-search")).sendKeys("irshad");
	  Thread.sleep(2000);
	  //driver.findElement(By.xpath("//*[@text='Search']")).click();
	  driver.findElement(By.cssSelector(".catalog-list__blueprint:nth-child(2) > a")).click();
	  driver.findElement(By.xpath("//div[@id='order-form-wrap']/div[2]/div/div/div/div")).click();
	  Thread.sleep(2000);
	  //driver.findElement(By.xpath("//div[@class='selectize-input items required has-options full has-items']//input")).click();
	  Select group = new Select(driver.findElement(By.xpath("//div[@id='order-form-wrap']/div[2]/div/div/div/div")));
	  group.selectByIndex(0);
	  
   }
  @Test(priority=5)
  public void Servers() throws Exception
  {
	  Actions actions = new Actions(driver);
	  WebElement menu = driver.findElement(By.linkText("Resources"));
	  actions.moveToElement(menu).perform();
	  Thread.sleep(2000);
	  WebElement submenu = driver.findElement(By.linkText("Servers"));
	  actions.moveToElement(submenu);
	  actions.click().build().perform();
	  
  }
  
  @Test(priority=6)
  public void Services() throws Exception
  {
	  Actions actions = new Actions(driver);
	  WebElement menu = driver.findElement(By.linkText("Resources"));
	  actions.moveToElement(menu).perform();
	  Thread.sleep(2000);
	  WebElement submenu = driver.findElement(By.linkText("Services"));
	  actions.moveToElement(submenu);
	  actions.click().build().perform();
  }
  
  @Test(priority=7) //
  public void Groups() throws Exception
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

