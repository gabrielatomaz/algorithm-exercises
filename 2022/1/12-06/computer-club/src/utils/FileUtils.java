package utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import entities.*;
import javafx.scene.control.Alert.AlertType;

public class FileUtils {

    public static void addUser(User user) {
        try {
            addUser(user, Boolean.TRUE);

            AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.CREATED_USER_SUCCESS);
        } catch (Exception e) {
            AlertUtils.setAlert(AlertType.ERROR, Constants.AlertConstants.CREATED_USER_ERROR);
        }
    }

    public static void updateUser(List<User> users) {
        try {
            Boolean isFirst = Boolean.FALSE;
            for (User user : users) {
                addUser(user, isFirst);

                if (!isFirst)
                    isFirst = Boolean.TRUE;
            }

            AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.UPDATED_USER_SUCCESS);
        } catch (Exception e) {
            AlertUtils.setAlert(AlertType.ERROR, Constants.AlertConstants.UPDATED_USER_ERROR);
        }
    }

    private static void addUser(User user, Boolean shouldAppend) throws IOException {
        var fileOutputStream = new FileOutputStream(Constants.FileConstants.USERS_FILE, shouldAppend);
        var objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static void addPost(Post post) {
        try {
            var fileOutputStream = new FileOutputStream(Constants.FileConstants.POSTS_FILE, Boolean.TRUE);
            var objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(post);
            objectOutputStream.flush();
            objectOutputStream.close();

            AlertUtils.setAlert(AlertType.INFORMATION, Constants.AlertConstants.CREATED_POST_SUCCESS);
        } catch (Exception e) {
            AlertUtils.setAlert(AlertType.ERROR, Constants.AlertConstants.CREATED_POST_ERROR);
        }
    }

    public static Long getNexUserId() {
        Long idCount = 1L;
        try {
            var fileIn = new FileInputStream(Constants.FileConstants.USERS_FILE);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    idCount += 1L;

                    objectIn.readObject();
                    objectIn = new ObjectInputStream(fileIn);
                }

                objectIn.close();

                return idCount;
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();

                return idCount;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return idCount;
    }

    public static List<User> getAllUsersFromFile() {
        var users = new ArrayList<User>();
        try {
            var fileIn = new FileInputStream(Constants.FileConstants.USERS_FILE);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    var user = (User) objectIn.readObject();
                    users.add(user);

                    objectIn = new ObjectInputStream(fileIn);
                }

                objectIn.close();

                return users;
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();

                return users;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public static List<Post> getAllPostsFromFile() {
        var posts = new ArrayList<Post>();
        try {
            var fileIn = new FileInputStream(Constants.FileConstants.POSTS_FILE);
            var objectIn = new ObjectInputStream(fileIn);
            var keepReading = Boolean.TRUE;
            try {
                while (keepReading) {
                    var post = (Post) objectIn.readObject();
                    posts.add(post);

                    objectIn = new ObjectInputStream(fileIn);
                }

                objectIn.close();

                return posts;
            } catch (EOFException e) {
                keepReading = false;
                objectIn.close();

                return posts;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return posts;
    }
}
