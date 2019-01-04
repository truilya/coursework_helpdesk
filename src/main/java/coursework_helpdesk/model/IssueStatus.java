package coursework_helpdesk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="d_status")
public class IssueStatus extends BaseEntity {

    @Column(name="name")
    private String name;

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
}
