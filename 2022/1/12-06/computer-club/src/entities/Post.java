package entities;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDate;

public class Post implements Serializable {

    private static final long serialVersionUID = 2L;

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

    @Override
    public String toString() {
        return MessageFormat.format("{0} - {1}\n {2} \n{3}",
                this.autor,
                this.timestamp,
                this.content,
                this.visibility);
    }

}
