package com.decathlon.trossi;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Fruit extends PanacheEntity {

    public String name;
    public String description;
    public int price;

    public Fruit() {
    }

    public Fruit(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}