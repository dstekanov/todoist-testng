package com.todoist.guice.converter;

import com.google.inject.spi.TypeConverter;

public class ArrayConverter {

    private ArrayConverter() {

    }

    private static String[] parseValue(String value) {
        String[] strings = value.split(",");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        return strings;
    }

    public static final TypeConverter STRING_ARRAY_CONVERTER = ((value, toType) -> {
        String[] strings = parseValue(value);
        return strings;
    });

    public static final TypeConverter INT_ARRAY_CONVERTER = ((value, toType) -> {
        String[] values = parseValue(value);
        Integer[] res = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Integer.valueOf(values[i]);
        }
        return res;
    });

    public static final TypeConverter BOOLEAN_ARRAY_CONVERTER = ((value, toType) -> {
        String[] values = parseValue(value);
        Boolean[] res = new Boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Boolean.valueOf(values[i]);
        }
        return res;
    });

    public static final TypeConverter DOUBLE_ARRAY_CONVERTER = ((value, toType) -> {
        String[] values = parseValue(value);
        Double[] res = new Double[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Double.valueOf(values[i]);
        }
        return res;
    });

    public static final TypeConverter iNT_ARRAY_CONVERTER = ((value, toType) -> {
        String[] values = parseValue(value);
        int[] res = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Integer.valueOf(values[i]);
        }
        return res;
    });

    public static final TypeConverter bOOLEAN_ARRAY_CONVERTER = ((value, toType) -> {
        String[] values = parseValue(value);
        boolean[] res = new boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Boolean.valueOf(values[i]);
        }
        return res;
    });

    public static final TypeConverter dOUBLE_ARRAY_CONVERTER = ((value, toType) -> {
        String[] values = parseValue(value);
        double[] res = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = Double.valueOf(values[i]);
        }
        return res;
    });
}
