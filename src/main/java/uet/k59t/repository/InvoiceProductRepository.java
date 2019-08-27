package uet.k59t.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.InvoiceProduct;

public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct, Long> {
}
