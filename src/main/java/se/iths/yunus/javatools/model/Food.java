package se.iths.yunus.javatools.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean lactose;
    private boolean seafood;

    @Column(name ="best_before")
    private LocalDate bestBefore;
    private int quantity;

    public Food() {
    }

    public Food(Long id, String name, boolean lactose, boolean seafood, LocalDate bestBefore, int quantity) {
        this.id = id;
        this.name = name;
        this.seafood = seafood;
        this.bestBefore = bestBefore;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLactose() {
        return lactose;
    }

    public void setLactose(boolean lactose) {
        this.lactose = lactose;
    }

    public boolean isSeafood() {
        return seafood;
    }

    public void setSeafood(boolean seafood) {
        this.seafood = seafood;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Lactose=" + lactose +
                ", Seafood=" + seafood +
                ", bestBefore=" + bestBefore +
                ", quantity=" + quantity +
                '}';
    }

    public boolean isLowStock(int threshold) {
        return quantity < threshold;

    }

    public boolean outOfStock(){
        return quantity <=0;
    }

    public boolean isShortDays(int thresholdDays) {
        long days = ChronoUnit.DAYS.between(LocalDate.now(), bestBefore);
        return days >= 0 && days <= thresholdDays;
    }


    public boolean hasExpired() {
        return bestBefore.isBefore(LocalDate.now());
    }

    public long daysBeforeExpiry(){
        return ChronoUnit.DAYS.between(LocalDate.now(), bestBefore);
    }
}
