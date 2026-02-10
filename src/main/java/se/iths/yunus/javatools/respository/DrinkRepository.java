package se.iths.yunus.javatools.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.yunus.javatools.model.Drink;
import se.iths.yunus.javatools.model.Food;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink>findByNameContainingIgnoreCase(String name);
    List<Drink>findByNameStartingWithIgnoreCase(String name);
    List<Drink>findDrinkByHasSugar(boolean hasSugar);
    List<Drink> findByQuantityLessThanEqual(int quantity);
    List<Drink> findByBestBeforeBefore(LocalDate date);
    List<Drink> findByBestBeforeBetween(LocalDate start, LocalDate end);
}
