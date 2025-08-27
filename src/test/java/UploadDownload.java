import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class UploadDownload {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String filePath = "C:\\Users\\rnith\\Downloads\\download.xlsx";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
        String updatedvalue = "300";
        String fruit = "Papaya";
        //Download
        driver.findElement(By.id("downloadButton")).click();

        //edit the excel
        int col = getColmIndex(filePath, "price");
        int row = getRowIndex(filePath, fruit);
        Assert.assertTrue(updateExcel(filePath, row, col, updatedvalue));

        //Upload
        WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
        upload.sendKeys(filePath);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By toastmassage = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastmassage));
        String text = driver.findElement(toastmassage).getText();
        System.out.println(text);
        Assert.assertEquals(text, "Updated Excel Data Successfully.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastmassage));

        //check the updated value
        String index = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
        String updated = driver.findElement(By.xpath("//div[text()='" + fruit + "']/parent::div/parent::div/div[@id='cell-" + index + "-undefined']")).getText();
        Assert.assertEquals(updated, updatedvalue);
        //driver.quit();
    }

    private static int getColmIndex(String filePath, String price) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int colreq = 0;
        int row = sheet.getPhysicalNumberOfRows();
        int col = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int j = 0; j < col; j++) {
            Row r = sheet.getRow(0);
            if (r.getCell(j).getStringCellValue().equalsIgnoreCase(price)) {
                colreq = j;
                return j;
            }
        }
        return colreq;
    }

    private static int getRowIndex(String filePath, String fruit) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int rowreq = 0;
        int row = sheet.getPhysicalNumberOfRows();
        int col = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < row; i++) {
            Row r = sheet.getRow(i);
            if (r.getCell(1).getStringCellValue().equals(fruit)) {
                rowreq = i;
                return i;
            }
        }
        return rowreq;
    }

    private static boolean updateExcel(String filePath, int row, int col, String updatedValue) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        Cell cell = sheet.getRow(row).getCell(col);
        cell.setCellValue(updatedValue);
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
        return true;
    }

}
