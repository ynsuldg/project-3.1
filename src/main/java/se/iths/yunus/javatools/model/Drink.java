package se.iths.yunus.javatools.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "drink")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private double price;
    private boolean sugar;
    @Column(name="best_before")
    private LocalDate bestBefore;
    private int quantity;

    public Drink() {
    }

    public Drink(Long id, String name, double price, boolean sugar, LocalDate bestBefore, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sugar = sugar;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getSugar() {
        return sugar;
    }

    public void setSugar(boolean sugar) {
        this.sugar = sugar;
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
        return "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", Sugar=" + sugar +
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

    public boolean isShortDays(int thresholdDays){
        long days = ChronoUnit.DAYS.between(LocalDate.now(), bestBefore);
        return days >= 0 && days <= thresholdDays;
    }

    public boolean hasExpired(){
        return bestBefore.isBefore(LocalDate.now());
    }

    public long daysBeforeExpiry(){
        return ChronoUnit.DAYS.between(LocalDate.now(), bestBefore);
    }

}
