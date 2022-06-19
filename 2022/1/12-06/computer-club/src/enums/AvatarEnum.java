package enums;

import java.util.Arrays;

public enum AvatarEnum {
    DEFAULT("Neutro", "/assets/default.png"),
    FEMALE("Feminino", "/assets/female.png"),
    MALE("Masculino", "/assets/male.png");

    private String name;
    private String path;

    private AvatarEnum(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public static AvatarEnum findAvatarEnum(String text) {
        return Arrays.stream(AvatarEnum.values())
                .filter(avatar -> avatar.name.equals(text))
                .findAny()
                .orElse(DEFAULT);
    }
}