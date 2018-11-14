package com.todoist.guice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.name.Names;
import com.todoist.guice.converter.ArrayConverter;
import com.todoist.guice.converter.ArrayMatcher;
import com.todoist.guice.converter.ListMatcher;
import com.todoist.guice.converter.ListTypeConverter;
import com.todoist.guice.module.JsonDataModule;
import com.todoist.guice.module.PropertiesModule;
import com.todoist.guice.module.TodoistRestModule;
import com.todoist.guice.module.WebDriverModule;
import com.todoist.guice.provider.EmailProvider;
import com.todoist.guice.provider.PasswordProvider;
import com.todoist.utils.Config;

public class MainModule extends AbstractModule {

    private final ObjectMapper objectMapper;

    public MainModule() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    protected void configure() {

        bind(ObjectMapper.class).toInstance(objectMapper);

        bind(String.class).annotatedWith(Names.named("random_email")).toProvider(EmailProvider.class);
        bind(String.class).annotatedWith(Names.named("random_password")).toProvider(PasswordProvider.class);

        install(new WebDriverModule());
        install(new TodoistRestModule());
        install(new PropertiesModule(Config.getProperties()));

        install(new JsonDataModule(objectMapper, Config.getJsonTestDataFolder()));

        addTypeConverters(binder());
    }

    private void addTypeConverters(Binder binder) {
        //array converters
        binder.convertToTypes(new ArrayMatcher(String.class), ArrayConverter.STRING_ARRAY_CONVERTER);
        binder.convertToTypes(new ArrayMatcher(Integer.class), ArrayConverter.INT_ARRAY_CONVERTER);
        binder.convertToTypes(new ArrayMatcher(int.class), ArrayConverter.iNT_ARRAY_CONVERTER);
        binder.convertToTypes(new ArrayMatcher(Boolean.class), ArrayConverter.BOOLEAN_ARRAY_CONVERTER);
        binder.convertToTypes(new ArrayMatcher(boolean.class), ArrayConverter.bOOLEAN_ARRAY_CONVERTER);
        binder.convertToTypes(new ArrayMatcher(Double.class), ArrayConverter.DOUBLE_ARRAY_CONVERTER);
        binder.convertToTypes(new ArrayMatcher(double.class), ArrayConverter.dOUBLE_ARRAY_CONVERTER);
        //List converters
        binder.convertToTypes(new ListMatcher(String.class), new ListTypeConverter());
        binder.convertToTypes(new ListMatcher(Integer.class), new ListTypeConverter());
        binder.convertToTypes(new ListMatcher(Boolean.class), new ListTypeConverter());
        binder.convertToTypes(new ListMatcher(Double.class), new ListTypeConverter());
    }
}
