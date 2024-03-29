package enums;

import java.util.Arrays;

public enum RouteEnum {
    FOLLOWERS("Seguidores", "/controllers/followers/layout-followers.fxml"),
    FOLLOWINGS("Seguidos", "/controllers/followings/layout-followings.fxml"),
    ADD_POST("Criar post", "/controllers/post/layout-add-post.fxml"),
    MENU("Voltar para o menu", "/controllers/menu/layout-menu.fxml"),
    ADD_USER("Criar usuarios", "/controllers/add_user/layout-add-user.fxml"),
    PROFILE("Perfil", "/controllers/profile/layout-profile.fxml"),
    FEED("Feed", "/controllers/feed/layout-feed.fxml"),
    SEARCH("Busca", "/controllers/search/layout-search.fxml"),
    LOGIN("Login", "/controllers/login/layout.fxml");

    private static RouteEnum[] PROFILE_ROUTE_ENUM = {
            FOLLOWERS,
            FOLLOWINGS,
            ADD_POST,
            MENU
    };

    private static RouteEnum[] MENU_ROUTE_ENUM = {
            PROFILE,
            ADD_USER,
            FEED,
            SEARCH,
            MENU
    };

    private String text;
    private String route;

    private RouteEnum(String text, String route) {
        this.text = text;
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }


    public String getText() {
        return this.text;
    }

    public static RouteEnum findRouteEnum(String text) {
        return Arrays.stream(RouteEnum.values())
                .filter(route -> route.text.equals(text))
                .findAny()
                .orElse(MENU);
    }

    public static RouteEnum findProfileRouteEnum(String text) {
        return Arrays.stream(PROFILE_ROUTE_ENUM)
                .filter(route -> route.text.equals(text))
                .findAny()
                .orElse(MENU);
    }

    public static RouteEnum findMenuRouteEnum(String text) {
        return Arrays.stream(MENU_ROUTE_ENUM)
                .filter(route -> route.text.equals(text))
                .findAny()
                .orElse(MENU);
    }
}
