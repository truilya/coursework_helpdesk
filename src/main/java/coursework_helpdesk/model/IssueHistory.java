package coursework_helpdesk.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="issue_history")
public class IssueHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Column(name="date_change")
    private LocalDateTime dateChanged;

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

    @Column(name="comment_txt")
    private String comment;

    @ManyToOne
    @JoinColumn(name="changer_id")
    private User changer;

    public IssueHistory() {
    }

    public IssueHistory(Integer id, Issue issue, LocalDateTime dateChanged, User creator, User engineer, String name, String description, IssueStatus issueStatus, IssuePriority issuePriority, String comment, User changer) {
        super(id);
        this.issue = issue;
        this.dateChanged = dateChanged;
        this.creator = creator;
        this.engineer = engineer;
        this.name = name;
        this.description = description;
        this.issueStatus = issueStatus;
        this.issuePriority = issuePriority;
        this.comment = comment;
        this.changer = changer;
    }

    public IssueHistory(IssueHistory issueHistory){
        this(issueHistory.getId(),
                issueHistory.getIssue(),
                issueHistory.getDateChanged(),
                issueHistory.getCreator(),
                issueHistory.getEngineer(),
                issueHistory.getName(),
                issueHistory.getDescription(),
                issueHistory.getIssueStatus(),
                issueHistory.getIssuePriority(),
                issueHistory.getComment(),
                issueHistory.getChanger());
    }

    public IssueHistory(Issue issue, String comment, User changer){
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        this.issue = issue;
        this.dateChanged =currentLocalDateTime;
        this.creator = issue.getCreator();
        this.engineer = issue.getEngineer();
        this.name = issue.getName();
        this.description = issue.getDescription();
        this.issueStatus = issue.getIssueStatus();
        this.issuePriority = issue.getIssuePriority();
        this.comment = comment;
        this.changer = changer;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public LocalDateTime getDateChanged() {
        return dateChanged;
    }

    public void setDateChanged(LocalDateTime dateCreated) {
        this.dateChanged = dateCreated;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getChanger() {
        return changer;
    }

    public void setChanger(User changer) {
        this.changer = changer;
    }
}
