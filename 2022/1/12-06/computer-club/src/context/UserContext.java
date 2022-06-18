package context;

import java.util.Objects;

import entities.User;

public class UserContext {
    
    private static UserContext INSTANCE;

    private User user;

    private UserContext() {}

    public static UserContext getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new UserContext();

        return INSTANCE;
    }

    public static void destroy() {
        INSTANCE = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
