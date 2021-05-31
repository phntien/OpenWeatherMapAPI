package com.currentweather.utils;

import java.io.File;

public class Constants {

    public static final String DEFAULT_TESTDATA_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data" + File.separator + "TestData.json";
    public static final String DEFAULT_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String DEFAULT_API_KEY = "&appid=f4282cd01e76cbac40f989bb76c811c3";
    public static final int STATUS_CODE_200 = 200;
    public static final int STATUS_CODE_404 = 404;
}
