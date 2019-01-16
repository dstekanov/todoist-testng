package com.todoist.guice.module;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.todoist.guice.provider.TodoistRequestSpecificationProvider;
import io.restassured.specification.RequestSpecification;

public class TodoistRestModule extends AbstractModule {

    private final ObjectMapper restApiMapper;

    public TodoistRestModule() {
        this(null);
    }

    public TodoistRestModule(ObjectMapper mapper) {
        if (mapper != null) {
            this.restApiMapper = mapper;
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
        //objectMapper.registerModule(new Jdk8Module());
        objectMapper.findAndRegisterModules();
//        objectMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        AnnotationIntrospector json = new JacksonAnnotationIntrospector();
//        AnnotationIntrospector xml = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
//        AnnotationIntrospector pair = new AnnotationIntrospectorPair(json, xml);

        // make and serializer deserializer use jackson and JAXB annotations
        objectMapper.setAnnotationIntrospector(json);

        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        restApiMapper = objectMapper;
    }

    @Override
    protected void configure() {
        bind(ObjectMapper.class).annotatedWith(Names.named("REST"))
                .toInstance(restApiMapper);

        bind(RequestSpecification.class).annotatedWith(Names.named("TodoistRest"))
                .toProvider(TodoistRequestSpecificationProvider.class);
    }
}
