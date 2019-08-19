package uet.k59t.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uet.k59t.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(Long id);
}
