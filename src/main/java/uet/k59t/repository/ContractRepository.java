package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Contract;

import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Page<Contract> findAllByDeletedIsFalse(Pageable pageable);
    Optional<Contract> findById(Long id);
}
