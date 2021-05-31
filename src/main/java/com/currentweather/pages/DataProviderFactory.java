package com.currentweather.pages;

import com.currentweather.utils.ExcelHelper;
import com.currentweather.utils.ExceptionHelper;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProviderFactory extends BaseTest {

    @DataProvider(name = "excelData")
    public Object[][] dataProviderMethod(Method method) {
        Object[][] excelData = null;
        try {
            logger.info("Parsing the test data...");
            String testCaseName = method.getName();
            String[] tcParts = testCaseName.split("_");
            testCaseId = tcParts[0] + "_" + tcParts[1] + "_" + tcParts[2];
            data = (JSONObject) testData.get(testCaseId);
            if (data.isEmpty()) {
                throw new IllegalArgumentException("The test data of the testcase Id " + testCaseId + " is empty");
            }
            logger.info("The test data of the testcase Id {} is loaded. The data is: {}", testCaseId, data.toString());
            String xlFilePath = data.get("xlFilePath").toString();
            String sheetName = data.get("sheetName").toString();
            String specificRows = data.get("specificRows").toString();
            if (specificRows.isEmpty()) {
                excelData = getTestData(xlFilePath, sheetName);
            } else {
                if (specificRows.contains("-")) {
                    excelData = getTestDataBySpecificRange(xlFilePath, sheetName, specificRows);
                } else {
                    excelData = getTestDataBySpecificRows(xlFilePath, sheetName, specificRows);
                }
            }
        } catch (NullPointerException e) {
            logger.error("Not found the testcase Id {} in the testdata", testCaseId);
            ExceptionHelper.rethrow(e);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("The format of the testcase is not correct. Please help to check.");
            ExceptionHelper.rethrow(e);
        } catch (Exception e) {
            ExceptionHelper.rethrow(e);
        }
        return excelData;
    }


    private Object[][] getTestData(String xlFilePath, String sheetName) throws IOException {
        ExcelHelper excelHelper = new ExcelHelper(xlFilePath);

        int rows = excelHelper.getRowCount(sheetName);

        int columns = excelHelper.getColumnCount(sheetName);

        Object[][] excelData = new Object[rows - 1][1];

        List<String> columnNames = excelHelper.getColumnNames(sheetName, columns);

        for (int i = 1; i < rows; i++) {
            Map<String, String> rowDataMap = new HashMap<>();
            for (String columnName : columnNames) {
                String cellData = excelHelper.getCellData(sheetName, columnName, i);
                rowDataMap.put(columnName, cellData);
            }
            excelData[i - 1][0] = rowDataMap;
        }

        return excelData;
    }

    private Object[][] getTestDataBySpecificRows(String xlFilePath, String sheetName, String specificRows) throws IOException {

        Object[][] excelData = this.getTestData(xlFilePath, sheetName);

        List<String> listSpecificRows = Arrays.asList(specificRows.split("\\s*,\\s*"));

        Object[][] newExcelData = new Object[listSpecificRows.size()][1];

        for (int i = 0; i < listSpecificRows.size(); i++) {
            int specificRow = Integer.valueOf(listSpecificRows.get(i)) - 1;
            newExcelData[i][0] = excelData[specificRow][0];
        }
        return newExcelData;
    }

    private Object[][] getTestDataBySpecificRange(String xlFilePath, String sheetName, String range) throws IOException {

        Object[][] excelData = this.getTestData(xlFilePath, sheetName);

        List<String> listRange = Arrays.asList(range.split("\\s*-\\s*"));

        int fromRow = Integer.valueOf(listRange.get(0));
        int toRow = Integer.valueOf(listRange.get(1));


        Object[][] newExcelData = new Object[toRow - fromRow + 1][1];

        for (int i = fromRow; i < toRow; i++) {
            newExcelData[i - fromRow][0] = excelData[i - 1][0];
        }
        logger.info(String.format("Test case is run from data row %s to row %s", fromRow, toRow));
        return newExcelData;
    }

}
