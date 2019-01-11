package com.sda.project.bookingapp.utility;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> splitStringByComma(final String param) {

        if (param == null) {
            throw new IllegalStateException("Param should not be empty");
        }

        String[] params = param.split(",");
        return Arrays.asList(params);
    }
}
