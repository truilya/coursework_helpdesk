package coursework_helpdesk.repository;

import coursework_helpdesk.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<User, Integer> {
}
