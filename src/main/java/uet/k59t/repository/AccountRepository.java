package uet.k59t.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Account;
import uet.k59t.model.Lead;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getAccountByDeletedAndLead(boolean deleted, Lead lead);
}
