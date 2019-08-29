package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Event;
import org.springframework.data.domain.Pageable;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByAccountId(Long accountId, Pageable pageable);
}
