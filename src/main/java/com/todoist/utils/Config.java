package com.todoist.utils;

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

    public static String getSiteUrl() {
        return PROPERTIES.getProperty("site_url");
    }

}
