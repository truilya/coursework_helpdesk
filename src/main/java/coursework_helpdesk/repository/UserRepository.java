package coursework_helpdesk.repository;

import coursework_helpdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository
        extends JpaRepository<User, Integer> {

    User findByLogin(String login);

    @Query(value = "select du.* from d_users du" +
            " join user_roles ur" +
            " on ur.user_id=du.id" +
            " where ur.role=:role",
    nativeQuery = true)
    List<User> findAllUserByRole(@Param("role") String role);
}
