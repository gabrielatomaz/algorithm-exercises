package utils;

import java.util.List;
import java.util.Objects;

public class ObjectUtils {
    
    public static Boolean isListValid(List<?> list) {
        return !(Objects.isNull(list) || list.isEmpty());
    }
}
