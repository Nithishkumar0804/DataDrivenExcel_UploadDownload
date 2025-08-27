import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    public ArrayList<String> getData(String testcaseName) throws IOException {
        FileInputStream fis = new FileInputStream("E:\\Projects & Workspace\\DataDriven & DownloadUpload Functionalities\\src\\main\\resources\\DataDrivenExcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        ArrayList<String> data = new ArrayList<>();

        XSSFSheet sheet = null;
        int sheetcount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetcount; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("DataDriven")) {
                sheet = workbook.getSheetAt(i);
                Iterator<Row> row = sheet.iterator();
                Row firstrow = row.next();
                Iterator<Cell> ce = firstrow.cellIterator();
                int f = 0, column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        column = f;
                    }
                    f++;
                }
                while (row.hasNext()) {
                    Row r = row.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {
                                String cellvalue = c.getStringCellValue();
                                data.add(cellvalue);
                            } else {
                                data.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return data;
    }
}
