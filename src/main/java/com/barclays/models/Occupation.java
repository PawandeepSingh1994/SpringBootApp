package com.barclays.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "Occupation")
public class Occupation {

    @Id
    @Column(name = "OccupationId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "occupationId")
    @SequenceGenerator(name = "occupationId", allocationSize = 1, sequenceName = "pOccupationId")
    private int id;

    @Column(name = "position")
    private String position;

    @Column(name = "location")
    private String location;

    @Column(name = "salary")
    private Double salary;

    public Occupation() {
        super();
    }

    public Occupation(String position, String location, Double salary) {
        super();
        this.position = position;
        this.location = location;
        this.salary = salary;
    }

    public Occupation(int id, String position, String location, Double salary) {
        super();
        this.id = id;
        this.position = position;
        this.location = location;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Occupation{" +
                "position='" + position + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                '}';
    }
}
