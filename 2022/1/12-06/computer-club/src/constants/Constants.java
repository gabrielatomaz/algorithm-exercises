package constants;

public final class Constants {

    public static final class ApplicationConstants {
        public static String STAGE_TITLE = "Clubinho da Computação";

        public static int ZERO_VALUE = 0;
    }

    public static final class FileConstants {
        public static String USERS_FILE = "src/users.txt",
                POSTS_FILE = "src/posts.txt";
    }

    public static final class AlertConstants {
        public static String UPDATED_USER_SUCCESS = "Usuário atualizado com sucesso!",
                UPDATED_USER_ERROR = "Erro ao atualizar usuário!",

                CREATED_POST_SUCCESS = "Postagem criada com sucesso!",
                CREATED_POST_ERROR = "Erro ao criar postagem!",

                CREATED_USER_SUCCESS = "Usuário criado com sucesso!",
                CREATED_USER_ERROR = "Erro ao criar usuário!",

                USERS_NOT_FOUND = "Nenhum usuário encontrado!",

                FOLLOWERS_NOT_FOUND = "Você não possui nenhum seguidor!",
                FOLLOWINGS_NOT_FOUND = "Você não segue nenhum usuário!",

                INVALID_CONTENT = "Conteúdo não pode ser vazio.",

                FIELDS_CANNOT_BE_EMPTY = "Campos de texto não devem estar em branco!",

                YOU_CAN_NOT_FOLLOW_YOURSELF = "Você não pode se seguir",
                FOLLOWED_USER_SUCCESS = "Usuário seguido com sucesso!",
                FOLLOWED_USER_ERROR = "Não foi possível seguir esse usuário!",

                POSTS_NOT_FOUND = "Nenhuma postagem foi encontrada!",
                
                UNFOLLOWED_USER_SUCCESS = "Você deixou de seguir esse usuário com sucesso!",
                UNFOLLOWED_USER_ERROR = "Não foi possível deixar de seguir esse usuário!";
    }

    public static final class ViewConstants {
        public static String USER_STRUCTURE = "{0} - Nome: {1} \n Interesses: {2}",
                POST_STRUCTURE = "{0} @{1} - {2} \n {3}",
                FOLLOWS_STRUCTURE = "{0} - {1} @{2}";
    }
}
