import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class AutoITUpload {
    @Test
    public void ITUpload() throws Exception {
        String downloadpath = System.getProperty("user.dir");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadpath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.ilovepdf.com/pdf_to_word");
        driver.findElement(By.id("pickfiles")).click();
        Runtime.getRuntime().exec("C:\\Users\\rnith\\Downloads\\Auto IT\\upload.exe");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[id='processTask']")));
        driver.findElement(By.cssSelector("button[id='processTask']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[id='pickfiles']")));
        driver.findElement(By.cssSelector("a[id='pickfiles']")).click();
        Thread.sleep(5000);
        File f = new File(downloadpath + "\\Resume");
        if (f.exists()) {
            System.out.println("File downloaded");
            if (f.delete()) {
                System.out.println("File deleted");
            }
        }
        driver.quit();
    }
}

