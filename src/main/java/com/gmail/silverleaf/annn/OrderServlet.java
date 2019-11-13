package com.gmail.silverleaf.annn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@WebServlet(name = "OrderServlet", urlPatterns = "/add_order")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbController controller = new DbController();
        try {
            ArrayList<Good> goods = (ArrayList<Good>) controller.requestGoodList();
            req.setAttribute("goods", goods);
            ArrayList<Client> clients = (ArrayList<Client>) controller.requestClientList();
            req.setAttribute("clients", clients);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/order.jsp");
            rd.forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbController controller = new DbController();
        try {
            int client_id = Integer.parseInt(req.getParameter("client"));
            String[] goods = req.getParameterValues("good");
            int order_id = controller.insertOrder(client_id);
            for(int i = 0; i < goods.length; i+=1) {
                controller.insertGoodToOrder(Integer.parseInt(goods[i]), order_id);
            }
            resp.sendRedirect("/");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
