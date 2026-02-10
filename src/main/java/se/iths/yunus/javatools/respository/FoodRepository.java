package se.iths.yunus.javatools.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.yunus.javatools.model.Food;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(String name);
    List<Food> findAllSeafood();
    List<Food> findAllLactoseFood();
    List<Food> findByNameContaining(String keyword);
    List<Food> findHasLactose();
    List<Food> findByQuantityLessThanEqual( int quantity);
    List<Food> findByBestBeforeBefore(LocalDate date);
    List<Food> findByBestBeforeBetween(LocalDate start, LocalDate end);

}
