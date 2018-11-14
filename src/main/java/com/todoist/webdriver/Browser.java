package com.todoist.webdriver;

public enum Browser {

    EDGE("edge"),
    IE("ie"),
    CHROME("chrome"),
    FIREFOX("firefox");

    private String driverType;

    Browser(String driverType) {
        this.driverType = driverType;
    }

    public String getName() {
        return driverType;
    }

}
