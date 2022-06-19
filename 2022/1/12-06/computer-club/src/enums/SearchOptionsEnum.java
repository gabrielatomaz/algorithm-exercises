package enums;

import java.util.Arrays;
import java.util.function.Predicate;

import entities.User;

public enum SearchOptionsEnum {
    NAME("Nome") {
        @Override
        public Predicate<User> searchBy(String value) {
            return user -> user.getName().equals(value);
        }
        
    },
    INTERESTS("Interesses") {
        @Override
        public Predicate<User> searchBy(String value) {
            return user -> user.getInterests().contains(value);
        }
    };

    private String name;

    private SearchOptionsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static SearchOptionsEnum findSearchOptionsEnum(String text) {
        return Arrays.stream(SearchOptionsEnum.values())
                .filter(option -> option.name.equals(text))
                .findAny()
                .orElse(NAME);
    }

    public abstract Predicate<User> searchBy(String value);
}
