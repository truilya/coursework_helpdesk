package coursework_helpdesk.repository;

import coursework_helpdesk.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueStatusRepository
extends JpaRepository<IssueStatus, Integer> {
}
