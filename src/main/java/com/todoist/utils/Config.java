package com.todoist.utils;

import com.todoist.webdriver.Browser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private static final Properties PROPERTIES;

    static {
        PROPERTIES = PropertyLoader.loadAll();
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static Properties getProperties() {
        return PROPERTIES;
    }

    // --------- assistant methods ---------

    public static Path getJsonTestDataFolder() {
        return Paths.get(PROPERTIES.getProperty("test_data_folder_json"));
    }


    public static Browser getBrowser() {
        String browser = PROPERTIES.getProperty("browser").toUpperCase();
        return Browser.valueOf(browser);
    }

    public static boolean isGridUse() {
        return Boolean.valueOf(PROPERTIES.getProperty("grid_use"));
    }

    public static String getGridHost() {
        return PROPERTIES.getProperty("grid_host");
    }

    public static Integer getGridPort() {
        return Integer.valueOf(PROPERTIES.getProperty("grid_port"));
    }

    public static String getSiteUrl() {
        return PROPERTIES.getProperty("site_url");
    }

}
