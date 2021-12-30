package Test;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Pages.homePage;
import config.propertiesConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import utiles.dataProvider;

/*
 * @author hanen.hedfi
 */
public class GeneralTest {
	static Logger Logger = LogManager.getLogger();
	private static WebDriver driver = null;
	public static String browserName = null;
	public static String url = null;
	private static WebDriverWait wait = null;
	homePage homePage;
	dataProvider dataProvider;

	/*
	 * / ***************SetUp
	 */
	@Parameters("browserName")
	@BeforeTest
	public void SetUP(String browserName) {
		// Set the driver
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		Logger.info("Browser started");
	}
	/*
	 * / ***************DataProvider
	 */

	@DataProvider(name = "testdata1")
	public Object[][] getData1() throws Throwable {

		Object data[][] = utiles.dataProvider.Data(System.getProperty("user.dir") + "/files/data.xlsx", "Sheet1");

		return data;
	}

	@DataProvider(name = "testdata2")
	public Object[][] getData2() throws Throwable {

		Object data[][] = utiles.dataProvider.Data(System.getProperty("user.dir") + "/files/data.xlsx", "Sheet2");

		return data;
	}
	/*
	 * *********Verify Information
	 */

	@Test(dataProvider = "testdata2", priority = 4)
	public void ProfilInformations(String email, String LastName, String FirstName, String tel) throws Throwable {

		homePage = new homePage(driver);

		homePage.ProfilInformations(email, LastName, FirstName, tel);
		Logger.info("Profil Informations verified");
	}

	/*
	 * *********Accept Cookies
	 */

	@Test(priority = 2)
	public void AcceptCookies() throws Throwable {

		homePage = new homePage(driver);
		homePage.AcceptCookies();
		Logger.info("Cookies Accepted");
	}
	/*
	 * *********Login
	 */

	@Test(dataProvider = "testdata1", priority = 3)
	public void login(String email, String pwd) throws Throwable {

		homePage = new homePage(driver);
		homePage.login(email, pwd);
		Logger.info("Success Login");
	}

	/*
	 ******** Open the application
	 */
	@Test(priority = 1)
	public void OpenApplication() throws Throwable {
		
		OpenTheApplication();
		Logger.info("Application opened");
	}

	/*
	 ******** Method to open the application
	 */

	public static void OpenTheApplication() {
		// Open the application
		propertiesConfig.getProp();
		wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		driver.get(url);
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.titleIs("Book the best restaurants in Europe - TheFork"));
	}

	/*
	 ********** TearDown Method
	 */

	@AfterTest
	public void TearDown() {
		// close browser
		driver.close();

	}

}
