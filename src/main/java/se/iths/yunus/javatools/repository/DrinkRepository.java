package se.iths.yunus.javatools.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iths.yunus.javatools.model.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    // Interface
}