package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findAllByDeletedIsFalse(Pageable pageable);
}
