package com.todoist.guice.converter;

import com.google.common.base.Splitter;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeConverter;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListTypeConverter implements TypeConverter {

    private final String splitRegExp;

    public ListTypeConverter(String regex) {
        this.splitRegExp = regex;
    }

    public ListTypeConverter() {
        this("[,]");
    }

    @Override
    public Object convert(String property, TypeLiteral<?> typeLiteral) {
        List<String> props = Splitter.onPattern(splitRegExp).trimResults().splitToList(property);

        // todo: think about empty list (maybe {}? ) and list with 1 element
        List<String> res = (props.size() == 1 && props.get(0).isEmpty()) ?
                new ArrayList<>()
                : props;

        Class<?> rawType = (Class) ((ParameterizedType) typeLiteral.getType()).getActualTypeArguments()[0];

        Function<String, Object> converter;
        if (Double.class.isAssignableFrom(rawType))
            converter = s -> s.isEmpty() ? null : Double.valueOf(s);
        else if (Boolean.class.isAssignableFrom(rawType))
            converter = s -> s.isEmpty() ? null : Boolean.valueOf(s);
        else if (Integer.class.isAssignableFrom(rawType))
            converter = s -> s.isEmpty() ? null : Integer.valueOf(s);
        else if (String.class.isAssignableFrom(rawType)) {
            // for empty prop. return empty list
            if (props.size() == 1 && "".equals(props.get(0)))
                return new ArrayList<>();
            // for quoted empty strings return empty strings
            converter = s -> "\"\"".equals(s) ? "" : s;
        } else
            throw new RuntimeException("Unsupported type for ListTypeConverter");

        return res.stream().map(converter).collect(Collectors.toList());
    }
}
