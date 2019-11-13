package com.gmail.silverleaf.annn;

public class Client {
    private int id;
    private String login;
    private String phone;

    public Client(int id, String login, String phone) {
        super();
        this.id = id;
        this.login = login;
        this.phone = phone;
    }

    public Client() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
