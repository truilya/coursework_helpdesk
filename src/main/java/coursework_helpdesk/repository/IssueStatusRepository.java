package coursework_helpdesk.repository;

import coursework_helpdesk.model.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueStatusRepository
extends JpaRepository<IssueStatus, Integer> {

    @Query(value = "select ds.* from d_status ds" +
            " left join status_roles sr" +
            " on sr.status_id=ds.id" +
            " where ds.id=:current_status_id or sr.role in (:roles)",
    nativeQuery = true)
    List<IssueStatus> findAllByRoles(@Param("current_status_id") Integer currentStatusId, @Param("roles") List<String> roles);
}
