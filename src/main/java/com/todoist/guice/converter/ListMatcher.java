package com.todoist.guice.converter;

import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListMatcher extends AbstractMatcher<TypeLiteral<?>> {

    private final Class<?> listElementClass;


    public ListMatcher(Class<?> listElementClass) {
        this.listElementClass = listElementClass;
    }


    @Override
    public boolean matches(TypeLiteral<?> typeLiteral) {
        Type type = typeLiteral.getType();
        if (!(type instanceof ParameterizedType)) return false;

        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getRawType() != List.class) return false;

        Class<?> paramClass = (Class) parameterizedType.getActualTypeArguments()[0];

        return paramClass.isAssignableFrom(listElementClass);

    }


    @Override
    public String toString() {
        return String.format("ListMatcher{%s}", listElementClass.getName());
    }
}
