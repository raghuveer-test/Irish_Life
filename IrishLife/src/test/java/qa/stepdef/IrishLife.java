package qa.stepdef;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IrishLife {
	
	WebDriver driver = null;
	
	By bot = By.xpath("//div[@id='CybotCookiebotDialog']");
	By allow = By.xpath("//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']");
	By contactUS = By.xpath("//div[@id='gatsby-focus-wrapper']/div/div[2]//a[@href='/contact-us']/button/span[1]");
	By findOutMore = By.xpath("//a[@data-testid='Link']//span[text()='Find out more']");
	By iWantToCover = By.xpath("//div[@id='step-1']//div[@class='step']/div[1]");
	
	@Before
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
		
	@Given("that I am on the Irish Life website homepage")
	public void openHomePage() {
		driver.get("https://www.irishlife.ie/");

		if (driver.findElement(bot).isDisplayed() )
			driver.findElement(allow).click();
	}

	@When("I click on the Contact link in then top navigation")
	public void clickOnContactLink() {
	    driver.findElement(contactUS).click();
	}
	
	@Then("a new page loads which contains a contact form")
	public void verifyContactForm() {
		assertTrue("Not in contact-us page", driver.getCurrentUrl().contains("/contact-us"));
	}
	
	@When("I click on the Find out more link")
	public void clickOnFindoutMoreLink() {
		driver.findElement(findOutMore).click();
	}
	
	@Then("I see a form which contains the text I want to cover")
	public void checkForTextIWantToCover() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bot));
		if (driver.findElement(bot).isDisplayed() )
			driver.findElement(allow).click();
		assertTrue("I want to cover text not found", driver.findElement(iWantToCover).getText().contains("I want to cover"));
		
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
