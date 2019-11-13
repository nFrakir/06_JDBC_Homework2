package com.gmail.silverleaf.annn;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start database controller debug");
        DbController controller = new DbController();
        try {
            List<Order> orders = controller.requestOrderList();
            System.out.println(orders);

            List<Good> goods = controller.requestGoodList();
            System.out.println(goods);

            List<Client> clients = controller.requestClientList();
            System.out.println(clients);

            controller.insertClient("Test","1212");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
