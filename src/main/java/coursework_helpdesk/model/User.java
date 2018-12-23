package coursework_helpdesk.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="d_users")
public class User extends BaseEntity{

    @Column(name="login", nullable = false,unique = true)
    private String login;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(User u){
        this(u.getId(),u.getLogin(),u.getPassword(),u.getRoles());
    }

    public User(Integer id, String login, String password, Set<Role> roles) {
        super(id);
        this.login = login;
        this.password = password;
        this.roles = roles;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
