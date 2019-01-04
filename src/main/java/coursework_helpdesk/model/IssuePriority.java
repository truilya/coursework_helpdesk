package coursework_helpdesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="d_priority")
public class IssuePriority extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name="ident")
    private String ident;

    public IssuePriority() {
    }

    public IssuePriority(String name, String ident) {
        this.name = name;
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        return name;
    }
}
