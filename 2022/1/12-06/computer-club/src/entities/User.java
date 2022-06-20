package entities;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;

import utils.StringUtils;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String avatar;
    private String user;
    private String password;
    private String email;
    private String address;
    private String cellPhone;
    private String telephone;
    private String socialMedia;
    private ArrayList<String> interests;
    private String studies;
    private Boolean isAdmin;
    private ArrayList<Post> posts;
    private ArrayList<User> followers;
    private ArrayList<User> followings;

    public User() {
    }

    public User(String name, String user, String password, String email, String address, String cellPhone,
            String telephone, String socialMedia, ArrayList<String> interests, String studies, Boolean isAdmin,
            ArrayList<Post> posts, ArrayList<User> followers, ArrayList<User> followings, String avatar, Long id) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.email = email;
        this.address = address;
        this.cellPhone = cellPhone;
        this.telephone = telephone;
        this.socialMedia = socialMedia;
        this.interests = interests;
        this.studies = studies;
        this.isAdmin = isAdmin;
        this.posts = posts;
        this.followers = followers;
        this.followings = followings;
        this.avatar = avatar;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdmin() {
        return this.isAdmin;
    }

    public void isAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
        this.socialMedia = socialMedia;
    }

    public ArrayList<Post> getPosts() {
        return this.posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<User> getFollowers() {
        return this.followers;
    }

    public void setFollower(User follower) {
        this.followers.add(follower);
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }


    public ArrayList<User> getFollowings() {
        return this.followings;
    }

    public void setFollowing(User following) {
        this.followings.add(following);
    }

    public void setFollowings(ArrayList<User> followings) {
        this.followings = followings;
    }

    @Override
    public String toString() {
        var interests = StringUtils.joinWithSemicolonDelimitter(this.interests);
        var followings = StringUtils.joinWithSemicolonDelimitter(this.followings);
        var followers = StringUtils.joinWithSemicolonDelimitter(this.followers);
        var posts = StringUtils.joinWithSemicolonDelimitter(this.posts);

        return MessageFormat.format("{0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{11},{12},{13},{14},{15}",
                this.name,
                this.address,
                this.cellPhone,
                this.email,
                this.telephone,
                this.isAdmin,
                this.password,
                this.user,
                this.socialMedia,
                this.studies,
                interests,
                followings,
                followers,
                posts,
                this.avatar,
                this.id);
    }
}
