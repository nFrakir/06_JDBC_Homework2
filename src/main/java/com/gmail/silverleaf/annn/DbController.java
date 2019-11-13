package com.gmail.silverleaf.annn;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DbController {
    private final String GET_ORDERLIST = "SELECT * FROM orders_info";
    private final String GET_ORDER_GOODLIST = "SELECT * FROM single_order_info WHERE order_id = ?";
    private final String GET_GOODLIST = "SELECT * FROM goods";
    private final String GET_CLIENTLIST = "SELECT * FROM clients";
    private final String INSERT_CLIENT = "INSERT INTO clients VALUES(DEFAULT, ?, ?)";
    private final String INSERT_GOOD = "INSERT INTO goods VALUES(DEFAULT, ?, ?)";
    private final String INSERT_ORDER = "INSERT INTO orders (client_id) VALUES(?)";
    private final String GET_ORDER_ID = "SELECT LAST_INSERT_ID() AS order_id";
    private final String INSERT_ORDER_GOODS = "INSERT INTO goods_orders VALUES(DEFAULT, ?, ?)";

    private String url;
    private String login;
    private String password;

    public DbController() {
        super();
        DbProperties properties = new DbProperties();
        this.url = properties.getUrl();
        this.login = properties.getUser();
        this.password = properties.getPassword();
    }

    public List<Order> requestOrderList() throws SQLException, ParseException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            Statement orderStatement = connection.createStatement();
            ResultSet rs = orderStatement.executeQuery(GET_ORDERLIST);

            for(;rs.next();) {
                List<Good> goods = requestGoodList(rs.getInt("order_id"));
                Order order = new Order(rs.getInt("order_id"),
                        rs.getString("order_date"),
                        rs.getDouble("total"),
                        rs.getInt("client_id"),
                        rs.getString("login"),
                        rs.getString("phone"),
                        goods);
                orders.add(order);
            }
        }
        return orders;
    }

    public List<Client> requestClientList() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            Statement clientsStatement = connection.createStatement();
            ResultSet rs = clientsStatement.executeQuery(GET_CLIENTLIST);
            for(;rs.next();) {
                Client client = new Client(rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("phone"));
                clients.add(client);
            }
        }
        return clients;
    }

    public List<Good> requestGoodList() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        List<Good> goods = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            Statement goodsStatement = connection.createStatement();
            ResultSet rs = goodsStatement.executeQuery(GET_GOODLIST);
            for(;rs.next();) {
                Good good = new Good(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDouble("price"));
                goods.add(good);
            }
        }
        return goods;
    }

    public List<Good> requestGoodList(int order_id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        List<Good> goods = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            PreparedStatement orderGoodsStatement = connection.prepareStatement(GET_ORDER_GOODLIST);
            orderGoodsStatement.setInt(1, order_id);
            ResultSet rs = orderGoodsStatement.executeQuery();
            for(;rs.next();) {
                Good good = new Good(rs.getInt("goods_id"),
                        rs.getString("title"),
                        rs.getDouble("price"));
                goods.add(good);
            }
        }
        return goods;
    }

    public void insertClient(String login, String phone) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, this.login, password)) {
            PreparedStatement ps = connection.prepareStatement(INSERT_CLIENT);
            ps.setString(1, login);
            ps.setString(2, phone);
            ps.execute();
        }
    }

    public void insertGood(String title, Double price) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            PreparedStatement ps = connection.prepareStatement(INSERT_GOOD);
            ps.setString(1, title);
            ps.setDouble(2, price);
            ps.execute();
        }
    }

    public int insertOrder(int client_id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        int order_id = 0;
        try (Connection connection = DriverManager.getConnection(url, this.login, password)) {
            PreparedStatement ps = connection.prepareStatement(INSERT_ORDER);
            ps.setInt(1, client_id);
            ps.execute();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ORDER_ID);
            for(;rs.next();) {
                order_id = rs.getInt("order_id");
            }
        }
        return order_id;
    }

    public void insertGoodToOrder(int good_id, int order_id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, this.login, password)) {
            PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_GOODS);
            ps.setInt(1, order_id);
            ps.setInt(2, good_id);
            ps.execute();
        }
    }

}
