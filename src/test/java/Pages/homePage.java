package Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * @author hanen.hedfi
 */

public class homePage {
	private static WebDriver driver = null;
	private static WebDriverWait wait = null;

	public homePage(WebDriver driver) {
		homePage.driver = driver;
		PageFactory.initElements( driver, this);
	}

	@FindBy(id = "_evidon-accept-button")
	private WebElement AcceptCookies;
	@FindBy(xpath = "//*[@id=\"root\"]/div/header/div/div[3]/button/span")
	private WebElement Login;
	@FindBy(xpath = "//*[@id=\"identification_email\"]")
	private WebElement email1;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_FIRST_PANEL\"]/div[2]/div[1]/div/div[3]/form[1]/div[2]/button/span")
	private WebElement Continue;
	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement psw;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_FIRST_PANEL\"]/div[2]/div[2]/div/div[2]/form/div[2]/button/span")
	private WebElement Continue1;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_FIRST_PANEL\"]/ul/li[1]/div/button")
	private WebElement profil;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_FIRST_PANEL\"]/div[2]/div[1]/div/div[4]/a/span")
	private WebElement register;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_SECOND_PANEL\"]/div[2]/section[1]/form/div[2]/div[1]/label/input")
	private WebElement firstName;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_SECOND_PANEL\"]/div[2]/section[1]/form/div[2]/div[2]/label/input")
	private WebElement lastName;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_SECOND_PANEL\"]/div[2]/section[1]/form/fieldset[1]/div/div[2]/label/input")
	private WebElement tel1;
	@FindBy(xpath = "//*[@id=\"USER_SPACE_SECOND_PANEL\"]/div[2]/section[4]/form/div[1]/div/label/input")
	private WebElement mail;

	/*
	 * *************Accept all Cookies
	 */
	public void AcceptCookies() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(AcceptCookies));
		AcceptCookies.click();

	}
	/*
	 * *************Login with valid credentials
	 */

	public void login(String email, String pwd) throws Throwable {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(Login));
		Login.click();
		wait.until(ExpectedConditions.visibilityOf(email1));
	//	wait.until(ExpectedConditions.elementToBeClickable(register));
		email1.sendKeys(email);
		assertTrue(Continue.isEnabled());
		Continue.click();
		wait.until(ExpectedConditions.visibilityOf(psw));
		psw.sendKeys(pwd);
		assertTrue(Continue1.isEnabled());
		Continue1.click();

	}
	/*
	 * *************Verify Personal Informations
	 */

	public void ProfilInformations(String email, String LastName, String FirstName, String tel) throws Throwable {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOf(profil));
		wait.until(ExpectedConditions.elementToBeClickable(profil));
		profil.click();
		assertEquals(firstName.getAttribute("value"), FirstName);
		assertEquals(lastName.getAttribute("value"), LastName);
		assertEquals(tel1.getAttribute("value"), tel);
		assertEquals(mail.getAttribute("value"), email);

	}
}
