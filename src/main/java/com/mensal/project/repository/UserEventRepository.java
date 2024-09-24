package com.mensal.project.repository;

import com.mensal.project.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    @Query(value = "SELECT COUNT(*) FROM users_events_tb WHERE event_id = :eventId AND participant_status = :status", nativeQuery = true)
    long countByEventIdAndStatus(@Param("eventId") Long eventId, @Param("status") String status);

    @Query(value = "SELECT COUNT(*) FROM users_events_tb WHERE event_id = :id", nativeQuery = true)
    long countById(@Param("id") Long id);
}
