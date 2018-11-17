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

    /**
     * Get enum from string. Default value CHROME if browser name is not matched.
     */
    public static Browser fromString(String browser) {
        for (Browser b : values()) {
            if (b.driverType.equalsIgnoreCase(browser)) {
                return b;
            }
        }
        return CHROME;
    }

    public String getName() {
        return driverType;
    }

}
