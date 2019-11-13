package com.gmail.silverleaf.annn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private Double total = 0.0;
    private Client client;
    private List<Good> goodList = new ArrayList<>();

    public Order(int id, Date date, Double total, Client client, List<Good> goodList) {
        super();
        this.id = id;
        this.date = date;
        this.total = total;
        this.client = client;
        this.goodList = goodList;
    }

    public Order(int id, String date, Double total, int client_id, String login, String phone, List<Good> goodList) throws ParseException {
        super();
        this.id = id;
        this.client = new Client(client_id, login, phone);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.parse(date);
        this.total = total;
        this.goodList = goodList;
    }

    public Order() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Good> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<Good> goodList) {
        this.total = 0.0;
        for (Good element: goodList) {
            this.total += element.getPrice();
        }
        this.goodList = goodList;
    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(this.date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", total=" + total +
                ", client=" + client +
                ", goodList=" + goodList +
                '}';
    }
}
