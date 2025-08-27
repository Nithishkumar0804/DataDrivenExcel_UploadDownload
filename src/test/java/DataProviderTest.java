import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class DataProviderTest {
    @Test(dataProvider = "loginData")
    public void login(String userName, String pasword, String res) {
        System.out.println(userName + " " + pasword + " " + res);

    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        DataFormatter formatter = new DataFormatter();
        FileInputStream fis = new FileInputStream("src/main/resources/DataProviderExcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("TestData");
        int rcount = sheet.getPhysicalNumberOfRows();
        int ccount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rcount - 1][ccount - 1];

        for (int i = 1; i < rcount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 1; j < ccount; j++) {
                data[i - 1][j - 1] = formatter.formatCellValue(row.getCell(j));
            }
        }
        workbook.close();
        fis.close();
        return data;
    }

}
