package coursework_helpdesk.repository;

import coursework_helpdesk.model.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory, Integer> {

    List<IssueHistory> findByIssueIdOrderByIdDesc(Integer issueId);

}
