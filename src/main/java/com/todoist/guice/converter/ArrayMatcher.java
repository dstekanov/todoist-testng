package com.todoist.guice.converter;

import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class ArrayMatcher extends AbstractMatcher<TypeLiteral<?>> {

    private final Class<?> arrayClass;

    public ArrayMatcher(Class<?> arrayClass) {
        this.arrayClass = arrayClass;
    }

    @Override
    public boolean matches(TypeLiteral<?> typeLiteral) {
        final Type type = typeLiteral.getType();
        return type instanceof GenericArrayType
                &&
                ((GenericArrayType) type).getGenericComponentType().equals(arrayClass);
    }

    @Override
    public String toString() {
        return String.format("ArrayMatcher{%s}", arrayClass.getName());
    }
}
