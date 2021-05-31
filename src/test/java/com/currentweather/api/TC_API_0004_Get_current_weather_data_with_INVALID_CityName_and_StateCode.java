package com.currentweather.api;

import com.currentweather.pages.BaseTest;
import com.currentweather.pages.DataProviderFactory;
import com.currentweather.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class TC_API_0004_Get_current_weather_data_with_INVALID_CityName_and_StateCode extends BaseTest {

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderFactory.class)
    public void TC_API_0004_Get_current_weather_data_with_INVALID_CityName_and_StateCode(Map<String, String> data) {

        String cityName = data.get("InvalidCityName");
        String sateName = data.get("InvalidStateCode");
        String endPoint = Constants.DEFAULT_API_URL + cityName + "," + sateName + Constants.DEFAULT_API_KEY;

        logger.info("1. Send Get request to the server with city = " + cityName + " and sate name = " + sateName);
        Response response = RestAssured.get(endPoint);

        logger.info("2. Get content type of api");
        String responseContentType = response.contentType();

        logger.info("*Validation Point*: Verify content type is default JSON ");
        Assert.assertEquals(ContentType.JSON.withCharset("utf-8"), responseContentType);

        logger.info("3. Get the response status code");
        int responseStatusCode = response.getStatusCode();

        logger.info("*Validation Point*: Verify response status code is 200");
        Assert.assertEquals(Constants.STATUS_CODE_404, responseStatusCode);

    }

}
