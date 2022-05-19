import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MathSignEnum {
    PLUS("+") {
        @Override
        public String doMath(String... values) {

            return convertValuesToStreamDouble(values)
                    .reduce((value1, value2) -> value1 + value2)
                    .map(result -> result.toString())
                    .get();
        }

    },
    MINUS("-") {
        @Override
        public String doMath(String... values) {
            return convertValuesToStreamDouble(values)
                    .reduce((value1, value2) -> value1 - value2)
                    .map(result -> result.toString())
                    .get();
        }
    },
    TIMES("X") {
        @Override
        public String doMath(String... values) {
            return convertValuesToStreamDouble(values)
                    .reduce((value1, value2) -> value1 * value2)
                    .map(result -> result.toString())
                    .get();
        }
    },
    DIVISION("/") {
        @Override
        public String doMath(String... values) {
            return convertValuesToStreamDouble(values)
                    .reduce((value1, value2) -> value1 / value2)
                    .map(result -> result.toString())
                    .get();
        }
    };

    private static final String TIMES_STRING = "*";

    private String value;

    private MathSignEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public abstract String doMath(String... values);

    public static MathSignEnum findMathSingEnum(String mathSign) {
        return Arrays.stream(MathSignEnum.values())
                .filter(mathSigEnum -> mathSigEnum.value.equalsIgnoreCase(mathSign))
                .findAny()
                .map(mathSigEnum -> {
                    if (MathSignEnum.TIMES.equals(mathSigEnum))
                        mathSigEnum.value = TIMES_STRING;

                    return mathSigEnum;
                }).get();
    }

    private static Stream<Double> convertValuesToStreamDouble(String[] values) {
        return Arrays.stream(values)
                .map(value -> Double.parseDouble(value))
                .collect(Collectors.toList())
                .stream();
    }
}
