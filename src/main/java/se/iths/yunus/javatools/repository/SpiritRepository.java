package se.iths.yunus.javatools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.yunus.javatools.model.Spirit;

@Repository
public interface SpiritRepository extends JpaRepository<Spirit, Long> {
}
