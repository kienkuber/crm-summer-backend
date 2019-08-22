package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Opportunity;

import java.util.Optional;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    Page<Opportunity> findAllByDeletedIsFalseAndAccountId(Long accountId);
    Optional<Opportunity> findByDeletedIsFalseAndId(Long id);
}
