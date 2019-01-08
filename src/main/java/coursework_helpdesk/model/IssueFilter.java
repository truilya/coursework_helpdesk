package coursework_helpdesk.model;

import java.util.ArrayList;
import java.util.List;

public class IssueFilter {

    private List<User> creators = new ArrayList<>();

    private List<User> engineers = new ArrayList<>();

    private List<IssueStatus> issueStatuses = new ArrayList<>();

    private List<IssuePriority> issuePriorities = new ArrayList<>();

    public IssueFilter() {
    }

    public IssueFilter(List<User> creators, List<User> engineers, List<IssueStatus> issueStatuses, List<IssuePriority> issuePriorities) {
        this.creators = creators;
        this.engineers = engineers;
        this.issueStatuses = issueStatuses;
        this.issuePriorities = issuePriorities;
    }

    public List<User> getCreators() {
        return creators;
    }

    public void setCreators(List<User> creators) {
        this.creators = creators;
    }

    public List<User> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<User> engineers) {
        this.engineers = engineers;
    }

    public List<IssueStatus> getIssueStatuses() {
        return issueStatuses;
    }

    public void setIssueStatuses(List<IssueStatus> issueStatuses) {
        this.issueStatuses = issueStatuses;
    }

    public List<IssuePriority> getIssuePriorities() {
        return issuePriorities;
    }

    public void setIssuePriorities(List<IssuePriority> issuePriorities) {
        this.issuePriorities = issuePriorities;
    }
}
