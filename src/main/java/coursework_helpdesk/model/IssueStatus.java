package coursework_helpdesk.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="d_status")
public class IssueStatus extends BaseEntity {

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "status_roles", joinColumns = @JoinColumn(name = "status_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public IssueStatus() {
    }

    public IssueStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Role getRole() {
        return (Role)roles.toArray()[0];
    }

    public void setRole(Role role) {
        this.roles.clear();
        this.roles.add(role);
    }

    public boolean isRoleEmpty(){
        return roles.isEmpty();
    }
}
