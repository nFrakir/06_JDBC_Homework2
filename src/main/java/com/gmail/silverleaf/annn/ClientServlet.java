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

@WebServlet(name="ClientServlet", urlPatterns = "/add_client")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbController controller = new DbController();
        try {
            getClientList(controller, req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbController controller = new DbController();
        try {
            String login = req.getParameter("login");
            String phone = req.getParameter("phone");
            controller.insertClient(login, phone);
            getClientList(controller, req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getClientList(DbController controller, HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArrayList<Client> clients = (ArrayList<Client>) controller.requestClientList();
        req.setAttribute("clients", clients);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/client.jsp");
        rd.forward(req, resp);
    }
}
