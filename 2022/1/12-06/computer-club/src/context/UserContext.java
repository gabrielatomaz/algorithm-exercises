package context;

import java.util.Objects;

import entities.User;

public class UserContext {
    
    private static volatile UserContext INSTANCE;

    private volatile User user;

    private UserContext() {}

    public static UserContext getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new UserContext();

        return INSTANCE;
    }

    public synchronized void destroy() {
        user = new User();
    }

    public synchronized User getUser() {
        return user;
    }

    public synchronized void setUser(User user) {
        this.user = user;
    }
}
