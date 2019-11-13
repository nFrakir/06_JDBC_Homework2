package com.gmail.silverleaf.annn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GoodServlet", urlPatterns = "/add_good")
public class GoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbController controller = new DbController();
        try {
            getGoodList(controller, req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbController controller = new DbController();
        try {
            String title = req.getParameter("title");
            Double price = Double.parseDouble(req.getParameter("price"));
            controller.insertGood(title, price);
            getGoodList(controller, req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getGoodList(DbController controller, HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArrayList<Good> goods = (ArrayList<Good>) controller.requestGoodList();
        req.setAttribute("goods", goods);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/good.jsp");
        rd.forward(req, resp);
    }
}
