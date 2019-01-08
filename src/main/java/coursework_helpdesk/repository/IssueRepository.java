package coursework_helpdesk.repository;

import coursework_helpdesk.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IssueRepository
extends JpaRepository<Issue,Integer> {

    @Query(value = "select iss.* from issue iss" +
            " where iss.date_created between coalesce(to_date(:start_date,'yyyy-mm-dd'),to_date('01.01.2000','dd.mm.yyyy')) and coalesce(to_date(:end_date,'yyyy-mm-dd'),to_date('01.01.2099','dd.mm.yyyy'))" +
            " and (iss.status_id in :statuses or -1 in :statuses)" +
            " and (iss.priority_id in :priorities or -1 in :priorities)" +
            " and (iss.creator_id in :creators or -1 in :creators)" +
            " and (iss.engineer_id in :engineers or -1 in :engineers)",
    nativeQuery = true)
    List<Issue> findByFilter(@Param("start_date") String startDateCreate,
                             @Param("end_date") String endDateCreate,
                             @Param("statuses") List<Integer> statuses,
                             @Param("priorities") List<Integer> priorities,
                             @Param("creators") List<Integer> creators,
                             @Param("engineers") List<Integer> engineers);

}
