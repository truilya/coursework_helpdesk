package coursework_helpdesk.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="issue")
public class Issue extends BaseEntity {

    @Column(name="date_created")
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name="engineer_id")
    private User engineer;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="status_id")
    private IssueStatus issueStatus;

    @ManyToOne
    @JoinColumn(name="priority_id")
    private IssuePriority issuePriority;

    @OneToMany(mappedBy = "issue")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<IssueHistory> issueHistory;

    public Issue() {
    }

   public Issue(Integer id, LocalDateTime dateCreated, User creator, User engineer, String name, String description, IssueStatus issueStatus, IssuePriority issuePriority) {
        super(id);
        this.dateCreated = dateCreated;
        this.creator = creator;
        this.engineer = engineer;
        this.name = name;
        this.description = description;
        this.issueStatus = issueStatus;
        this.issuePriority = issuePriority;
    }

    public Issue(Issue i){
        this(i.getId(),i.getDateCreated(),i.getCreator(),i.getEngineer(),i.getName(),i.getDescription(),i.getIssueStatus(),i.getIssuePriority());
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getEngineer() {
        return engineer;
    }

    public void setEngineer(User engineer) {
        this.engineer = engineer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public IssuePriority getIssuePriority() {
        return issuePriority;
    }

    public void setIssuePriority(IssuePriority issuePriority) {
        this.issuePriority = issuePriority;
    }

   /* public Set<IssueHistory> getIssueHistory() {
        return issueHistory;
    }

    public void setIssueHistory(Set<IssueHistory> issueHistory) {
        this.issueHistory = issueHistory;
    }

    public List<IssueHistory> getIssueHistoryList(){
        return this.issueHistory.stream().collect(Collectors.toList());
    }*/
}
