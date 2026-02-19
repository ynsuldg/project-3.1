package se.iths.yunus.javatools.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="has_lactose")
    private boolean hasLactose;

    @Column(name="has_seafood")
    private boolean hasSeafood;


    private String barcode;

    @Column(nullable = false)
    private double price;

    @Column(name ="best_before")
    private LocalDate bestBefore;

    private int quantity;

    public Food() {
    }

    public Food(String name, boolean hasLactose, boolean hasSeafood, String barcode, double price, LocalDate bestBefore, int quantity) {
        this.name = name;
        this.hasLactose = hasLactose;
        this.hasSeafood = hasSeafood;
        this.barcode = barcode;
        this.price = price;
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

    public boolean getHasLactose() {
        return hasLactose;
    }

    public void setHasLactose(boolean hasLactose) {
        this.hasLactose = hasLactose;
    }

    public boolean getHasSeafood() {
        return hasSeafood;
    }

    public void setHasSeafood(boolean hasSeafood) {
        this.hasSeafood = hasSeafood;
    }

    public String getBarcode(){
        return barcode;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
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
                ", hasLactose=" + hasLactose +
                ", hasSeafood=" + hasSeafood +
                ", barcode='" + barcode + '\'' +
                ", price=" + price +
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
