package test_suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginAsDeveloper 
{
	private WebDriver driver;
	 @BeforeTest
	  public void NavigateToCMP() throws Exception
	  {
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
	 public void LoginToCMP() throws Exception
	 {
	 	driver.findElement(By.id("id_username")).clear(); 
	    driver.findElement(By.id("id_password")).clear();
	    Thread.sleep(1000);
	    driver.findElement(By.name("username")).sendKeys("irshad.psh@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Admin@642");
		driver.findElement(By.xpath("//button[@type='submit']")).click();  
		Thread.sleep(4000);
		if (driver.getPageSource().contains("Developer"))
		  {
		  System.out.println("Logged in as Admin  ");
		  }
		else
		{
			System.out.println("Logged in As Developer");
		}
		
	 }
	 @Test(priority=4)
	  public void Servers() throws Exception
	  {
		 
		  driver.findElement(By.xpath("//img[@class='hamburger']")).click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//li[@title=\"Catalog\"]")).click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//li[@title=\"Catalog\"]//a[@href=\"/catalog/\"]")).click();
		  Thread.sleep(5000);
	  }
	  
	  @Test(priority=5)
	  public void Services() throws Exception
	  {
		  driver.findElement(By.xpath("//img[@class='hamburger']")).click();
		  Thread.sleep(2000);
		  
	  }
	  
	  @Test(priority=6)
	  public void Groups() throws Exception
	  {
		  driver.findElement(By.xpath("//img[@class='hamburger']")).click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//span[contains(text(),'Groups')]")).click();
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//a[contains(text(), 'All Groups')]")).click();
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
