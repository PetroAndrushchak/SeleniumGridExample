import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TestClass {

    WebDriver driver;

    @Parameters( {"Port"})
    @BeforeClass
    public void initiateDriver(String Port) throws MalformedURLException {

        if (Port.equalsIgnoreCase("9001")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), new ChromeOptions());
            driver.manage().window().maximize();
        } else if (Port.equalsIgnoreCase("9002")) {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), new FirefoxOptions());
            driver.manage().window().maximize();
        }
    }

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com.ua/");
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("test.png");
        try {
            FileUtils.copyFile(SrcFile, DestFile);
            DestFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void after(){
        driver.quit();
    }
}
