package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Lead;

import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {
    Page<Lead> findAllByDeletedIsFalse(Pageable pageable);

    Optional<Lead> findById(Long id);
}
