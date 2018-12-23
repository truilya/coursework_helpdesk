package coursework_helpdesk.repository;

import coursework_helpdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository
        extends JpaRepository<User, Integer> {
}
