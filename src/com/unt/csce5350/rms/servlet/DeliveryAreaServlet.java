package com.unt.csce5350.rms.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unt.csce5350.rms.dao.DeliveryAreaDAO;
import com.unt.csce5350.rms.model.DeliveryArea;


@WebServlet("/deliveryAreas")
public class DeliveryAreaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryAreaDAO deliveryAreaDAO;

    public void init() {
        deliveryAreaDAO = new DeliveryAreaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	System.out.println("Inside do get");
        String action = request.getServletPath();
        System.out.println("getQueryString: "+request.getQueryString());
        System.out.println("request.getParameter: "+request.getParameter("action"));
    	System.out.println("action: "+action);
    	
    	action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertDeliveryArea(request, response);
                    break;
                case "delete":
                    deleteDeliveryArea(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateDeliveryArea(request, response);
                    break;
                default:
                    listDeliveryArea(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDeliveryArea(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < DeliveryArea > listDeliveryArea = deliveryAreaDAO.selectAllDeliveryAreas();
        request.setAttribute("listDeliveryArea", listDeliveryArea);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/deliveryArea-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/deliveryArea-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeliveryArea existingDeliveryArea = deliveryAreaDAO.selectDeliveryArea(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/deliveryArea-form.jsp");
        request.setAttribute("deliveryArea", existingDeliveryArea);
        dispatcher.forward(request, response);

    }

    private void insertDeliveryArea(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        DeliveryArea newDeliveryArea = new DeliveryArea(name, email, country);
        deliveryAreaDAO.insertDeliveryArea(newDeliveryArea);
        response.sendRedirect("deliveryAreas?action=list");
    }

    private void updateDeliveryArea(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        DeliveryArea book = new DeliveryArea(id, name, email, country);
        deliveryAreaDAO.updateDeliveryArea(book);
        response.sendRedirect("deliveryAreas?action=list");
    }

    private void deleteDeliveryArea(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        deliveryAreaDAO.deleteDeliveryArea(id);
        response.sendRedirect("deliveryAreas?action=list");

    }
}