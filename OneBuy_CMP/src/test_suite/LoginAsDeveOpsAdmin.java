package test_suite;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginAsDeveOpsAdmin 
{
	private WebDriver driver;
	@BeforeTest
	 public void NavigateToCMP() throws Exception
	 {
		System.setProperty("webdriver.chrome.driver", "C:\\Irshad\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://dashboard-dev.smartshifttech.com/accounts/login/");
		Thread.sleep(2000);
	 }		
	@Test(priority=1) 
	 public void LoginToCMP() throws Exception
	 {
	 	driver.findElement(By.id("id_username")).clear(); 
	    driver.findElement(By.id("id_password")).clear();
	    Thread.sleep(1000);
	    driver.findElement(By.name("username")).sendKeys("irsad.far@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Manzil@642");
		driver.findElement(By.xpath("//button[@type='submit']")).click();  
		Thread.sleep(2000);
		if (driver.getPageSource().contains("DevOps Admin"))
		  {
		  System.out.println("Logged in as Admin  ");
		  }
		else
		{
			System.out.println("Logged in As DevOps Admin");
		}
		
	 }
	
	@Test(priority=2)
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
  
  @Test(priority=3)
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
  
  @Test(priority=4)
  public void Groups() throws Exception
  {
	  Thread.sleep(1000);
	  driver.findElement(By.linkText("Groups")).click();
  }
  
}
