package com.currentweather.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExcelHelper {

    public FileInputStream fis;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    private String excelPath;

    public ExcelHelper(String xlFilePath) throws IOException {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(xlFilePath)).getFile();
        excelPath = xlFilePath;
        fis = new FileInputStream(path.replaceAll("%20", " "));
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public int getRowCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum() + 1;
    }

    public int getColumnCount(String sheetName) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        return row.getLastCellNum();
    }

    public List<String> getColumnNames(String sheetName, int columns) {
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            columnNames.add(this.getCellData(sheetName, i, 0));
        }
        return columnNames;
    }

    public String getCellData(String sheetName, String colName, int rowNum) {
        int colNum = -1;
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim()))
                colNum = i;
        }

        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
            String cellValue = String.valueOf(cell.getNumericCellValue());
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Date date = cell.getDateCellValue();
                cellValue = df.format(date);
            }
            return cellValue;
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            return String.valueOf(cell.getBooleanCellValue());
        }
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
            String cellValue = String.valueOf(cell.getNumericCellValue());
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Date date = cell.getDateCellValue();
                cellValue = df.format(date);
            }
            return cellValue;
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else {
            return String.valueOf(cell.getBooleanCellValue());
        }
    }
}
