package m2dl.ivvq.sortircesoir.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    private String login;

    @NotNull
    @Size(min = 1)
    @Email
    private String email;

    @NotNull
    @Size(min = 1)
    private String password;

    private String token;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Comment> comments;

    public User() {
        this.comments = new ArrayList<Comment>();
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.comments = new ArrayList<Comment>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
