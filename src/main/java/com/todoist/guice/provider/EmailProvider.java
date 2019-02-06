package com.todoist.guice.provider;

import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Provider;

public class EmailProvider implements Provider<String> {

    @Override
    public String get() {
        return "testUser" + "_" + RandomStringUtils.randomAlphanumeric(10) + "@myadt.com";
    }
}
