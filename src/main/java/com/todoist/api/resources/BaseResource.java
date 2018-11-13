package com.todoist.api.resources;

import com.google.inject.Provider;
import io.restassured.specification.RequestSpecification;

import javax.inject.Inject;
import javax.inject.Named;

public class BaseResource {

    @Inject
    @Named("TodoistRest")
    private Provider<RequestSpecification> requestProvider;

    /**
     * @return new todoist request specification
     */
    protected RequestSpecification newRequest() {
        return requestProvider.get();
    }

}
