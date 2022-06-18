package entities;

import java.time.LocalDate;

public class Post {

    private String content;
    private User autor;
    private LocalDate timestamp;
    private Boolean visibility;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAutor() {
        return this.autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getVisibility() {
        return this.visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Post(String content, User autor, LocalDate timestamp, Boolean visibility) {
        this.content = content;
        this.autor = autor;
        this.timestamp = timestamp;
        this.visibility = visibility;
    }

}
