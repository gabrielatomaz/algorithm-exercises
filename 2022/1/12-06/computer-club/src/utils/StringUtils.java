package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import constants.Constants;

public class StringUtils {
    public static String EMPTY = "";

    public static String joinWithCommaSpaceDelimiter(List<?> list) {
        return list.stream().map(Object::toString)
                    .collect(Collectors.joining(DelimiterEnum.COMMA_SPACE.getValue()));
    }

    public static String getFirstItemByDashDelimiter(String value) {
        return value.split(DelimiterEnum.DASH.getValue())[Constants.ApplicationConstants.ZERO_VALUE].trim();
    }

    public static List<String> splitByCommaDelimiter(String value) {
        return Arrays.asList(value.split(DelimiterEnum.COMMA.getValue()));
    }

    private enum DelimiterEnum {
        COMMA(","),
        COMMA_SPACE(", "),
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
