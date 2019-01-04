package coursework_helpdesk.repository;

import coursework_helpdesk.model.IssuePriority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuePriorityRepository
extends JpaRepository<IssuePriority,Integer> {
}
