package coursework_helpdesk.repository;

import coursework_helpdesk.model.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Integer> {
}
