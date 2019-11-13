package com.gmail.silverleaf.annn;

public class Good {
    private int id;
    private String title;
    private Double price;

    public Good(int id, String title, Double price) {
        super();
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Good() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
