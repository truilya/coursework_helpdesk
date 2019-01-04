package coursework_helpdesk.repository;

import coursework_helpdesk.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository
extends JpaRepository<Issue,Integer> {
}
