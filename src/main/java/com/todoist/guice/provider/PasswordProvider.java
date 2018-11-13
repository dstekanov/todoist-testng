package com.todoist.guice.provider;

import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Provider;

public class PasswordProvider implements Provider<String> {

    @Override
    public String get() {
        return RandomStringUtils.randomAlphanumeric(10) + 0;
    }
}
