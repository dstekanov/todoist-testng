package com.todoist.guice.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Provider;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.*;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import javax.inject.Named;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.*;

public class TodoistRequestSpecificationProvider implements Provider<RequestSpecification> {

    @Inject
    @Named("todoist_api_token")
    private String apiToken;
    @Inject
    @Named("todoist_base_url")
    private String baseUrl;

    private final ObjectMapper mapper;
    private final String defaultProtocol = "https";

    @Inject
    public TodoistRequestSpecificationProvider(@Named("REST") ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public RequestSpecification get() {
        return createRequest("API", "v8");
    }

    private RequestSpecification createRequest(String apiName, String apiVersion) {

        final HttpClientConfig HTTP_CLIENT_CONFIG =
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", //CoreConnectionPNames.CONNECTION_TIMEOUT,
                                Integer.getInteger("rest.http.connection.timeout", 60000))
                        .setParam("http.socket.timeout",     //CoreConnectionPNames.SO_TIMEOUT,
                                Integer.getInteger("rest.http.socket.timeout", 60000));

        final ObjectMapperConfig OM_CONFIG =
                ObjectMapperConfig.objectMapperConfig()
                        .defaultObjectMapperType(ObjectMapperType.JACKSON_2)
                        .jackson2ObjectMapperFactory((aClass, charset) -> mapper);

        final RestAssuredConfig CONFIG =
                RestAssuredConfig.config()
                        .encoderConfig(new EncoderConfig().defaultContentCharset(StandardCharsets.UTF_8))
                        .decoderConfig(new DecoderConfig().defaultContentCharset(StandardCharsets.UTF_8))
                        .objectMapperConfig(OM_CONFIG)
                        .headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Authorization"))
                        .httpClient(HTTP_CLIENT_CONFIG);

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder
                .expectStatusCode(allOf(greaterThanOrEqualTo(200), lessThan(300)));
        ResponseSpecification responseSpec = responseSpecBuilder.build();

        RequestSpecification specification = RestAssured.given()
                .baseUri(String.format("%s://%s", defaultProtocol, baseUrl))
                .basePath(String.format("/%s/%s", apiName, apiVersion))
                .auth().preemptive().oauth2(apiToken)
                .config(CONFIG)
                .contentType("application/json;charset=UTF-8")
                .filter(new AllureRestAssured()) // Attach logs to allure report
                .log().ifValidationFails()
                .then()
                .log().ifError()
                .spec(responseSpec)
                .request();

        return specification;
    }

}
