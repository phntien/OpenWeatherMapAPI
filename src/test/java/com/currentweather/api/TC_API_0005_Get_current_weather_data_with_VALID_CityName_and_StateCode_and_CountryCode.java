package com.currentweather.api;

import com.currentweather.pages.BaseTest;
import com.currentweather.pages.DataProviderFactory;
import com.currentweather.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class TC_API_0005_Get_current_weather_data_with_VALID_CityName_and_StateCode_and_CountryCode extends BaseTest {

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderFactory.class)
    public void TC_API_0005_Get_current_weather_data_with_VALID_CityName_and_StateCode_and_CountryCode(Map<String, String> data) {

        String cityName = data.get("CityName");
        String sateName = data.get("StateCode");
        String countryName = data.get("CountryCode");
        String endPoint = Constants.DEFAULT_API_URL + cityName + "," + sateName + "," + countryName + Constants.DEFAULT_API_KEY;

        logger.info("1. Send Get request to the server with city = " + cityName + " and sate name = " + sateName + "and country code = " + countryName);
        Response response = RestAssured.get(endPoint);

        logger.info("2. Get content type of api");
        String responseContentType = response.contentType();

        logger.info("*Validation Point*: Verify content type is default JSON ");
        Assert.assertEquals(ContentType.JSON.withCharset("utf-8"), responseContentType);

        logger.info("3. Get the response status code");
        int responseStatusCode = response.getStatusCode();

        logger.info("*Validation Point*: Verify response status code is 200");
        Assert.assertEquals(Constants.STATUS_CODE_200, responseStatusCode);

        logger.info("4. Get response body");
        JsonPath jsonPathEvaluator = response.jsonPath();

        logger.info("*Validation Point*: Verify response body returns correctly longitude");
        Assert.assertEquals(data.get("ExpectedLon"), jsonPathEvaluator.getString("coord.lon"));
        logger.info("*Validation Point*: Verify response body returns correctly latitude");
        Assert.assertEquals(data.get("ExpectedLat"), jsonPathEvaluator.getString("coord.lat"));
        logger.info("*Validation Point*: Verify response body returns correctly location id");
        Assert.assertEquals(data.get("ExpectedID"), jsonPathEvaluator.getString("id"));
        logger.info("*Validation Point*: Verify response body returns correctly location name");
        Assert.assertEquals(data.get("ExpectedName"), jsonPathEvaluator.getString("name"));
        logger.info("*Validation Point*: Verify response body returns correctly location cod");
        Assert.assertEquals(data.get("ExpectedCOD"), jsonPathEvaluator.getString("cod"));
        logger.info("*Validation Point*: Verify response body returns correctly country");
        Assert.assertEquals(data.get("ExpectedCountry"), jsonPathEvaluator.getString("sys.country"));

    }

}
