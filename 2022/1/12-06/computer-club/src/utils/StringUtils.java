package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import constants.Constants;

public class StringUtils {
    public static String EMPTY = "";

    public static String joinWithCommaDelimiter(List<?> list) {
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(DelimiterEnum.COMMA.getValue()));
    }

    public static String getFirstItemByDashDelimiter(String value) {
        return value.split(DelimiterEnum.DASH.getValue())[Constants.ApplicationConstants.ZERO_VALUE].trim();
    }

    public static List<String> splitByCommaDelimiter(String value) {
        var values = Arrays.stream(value.split(DelimiterEnum.COMMA.getValue()))
                .map(v -> v.trim())
                .collect(Collectors.toList());
        return values;
    }

    public static String joinWithSemicolonDelimitter(List<?> list) {
        return list.stream().map(Object::toString)
                .collect(Collectors.joining(DelimiterEnum.SEMICOLON.getValue()));
    }

    private enum DelimiterEnum {
        COMMA(","),
        SEMICOLON(";"),
        DASH("-");

        private String value;

        private DelimiterEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
