package com.currentweather.pages;

import com.currentweather.utils.Constants;
import com.currentweather.utils.LogHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class BaseTest {

    protected static final LogHelper logger = new LogHelper();
    public static JSONObject data;
    public static JSONObject testData;
    public String testCaseId;

    @BeforeClass
    public void beforeSuite(ITestContext context) throws IOException, ParseException {
        Reader reader = new FileReader(Constants.DEFAULT_TESTDATA_PATH);
        JSONParser parser = new JSONParser();
        testData = (JSONObject) parser.parse(reader);
        if (testData == null) {
            logger.error("Test Data is null!!!");
        }
    }
}
