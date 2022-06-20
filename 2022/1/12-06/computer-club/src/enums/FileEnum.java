package enums;

public enum FileEnum {
    POSTS("src/files/posts.txt"),
    USERS("src/files/users.txt");

    private String path;

    private FileEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
