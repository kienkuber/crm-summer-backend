package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Opportunity;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    Page<Opportunity> findAllByDeletedIsFalseAndAccountId(Long accountId, Pageable pageable);
    Optional<Opportunity> findByDeletedIsFalseAndId(Long id);
}
