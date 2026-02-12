package se.iths.yunus.javatools.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spirit")
public class Spirit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spiritid")
    private Long id;
    private String type;
    @Column(name = "name")
    private String title;
    private double apv;
    private int ageInMonth;
    private double prise;

    public Spirit() {
    }

    public Spirit(Long id, String type, String title, double apv, int ageInMonth, double prise) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.apv = apv;
        this.ageInMonth = ageInMonth;
        this.prise = prise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getApv() {
        return apv;
    }

    public void setApv(double apv) {
        this.apv = apv;
    }

    public int getAgeInMonth() {
        return ageInMonth;
    }

    public void setAgeInMonth(int ageInMonth) {
        this.ageInMonth = ageInMonth;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }
}
