package com.todoist.guice.converter;

import com.google.inject.spi.TypeConverter;

public final class ArrayConverter {

    public static final TypeConverter STRING_ARRAY_CONVERTER = (value, toType) -> parseValue(value);

    public static final TypeConverter INT_ARRAY_CONVERTER = (value, toType) -> {
        final String[] values = parseValue(value);
        final Integer[] res = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Integer.valueOf(values[i]);
        }
        return res;
    };

    public static final TypeConverter BOOLEAN_ARRAY_CONVERTER = (value, toType) -> {
        final String[] values = parseValue(value);
        final Boolean[] res = new Boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Boolean.valueOf(values[i]);
        }
        return res;
    };

    public static final TypeConverter DOUBLE_ARRAY_CONVERTER = (value, toType) -> {
        final String[] values = parseValue(value);
        final Double[] res = new Double[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Double.valueOf(values[i]);
        }
        return res;
    };

    public static final TypeConverter iNT_ARRAY_CONVERTER = (value, toType) -> {
        final String[] values = parseValue(value);
        final int[] res = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Integer.valueOf(values[i]);
        }
        return res;
    };

    public static final TypeConverter bOOLEAN_ARRAY_CONVERTER = (value, toType) -> {
        final String[] values = parseValue(value);
        final boolean[] res = new boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Boolean.valueOf(values[i]);
        }
        return res;
    };

    public static final TypeConverter dOUBLE_ARRAY_CONVERTER = (value, toType) -> {
        final String[] values = parseValue(value);
        final double[] res = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Double.valueOf(values[i]);
        }
        return res;
    };

    private ArrayConverter() {

    }

    private static String[] parseValue(String value) {
        final String[] strings = value.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        return strings;
    }
}
