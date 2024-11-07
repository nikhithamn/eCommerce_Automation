package com.TechStory.eCommerce.eCommerce_Project.utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return cell.getStringCellValue();
    }
}

