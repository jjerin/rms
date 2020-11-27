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

import com.unt.csce5350.rms.dao.DeliveryPersonDAO;
import com.unt.csce5350.rms.updated.model.Deliveryperson;


@WebServlet("/deliveryPersons")
public class DeliveryPersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryPersonDAO deliveryPersonDAO;

    public void init() {
        deliveryPersonDAO = new DeliveryPersonDAO();
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
                    insertDeliveryPerson(request, response);
                    break;
                case "delete":
                    deleteDeliveryPerson(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateDeliveryPerson(request, response);
                    break;
                default:
                    listDeliveryPerson(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDeliveryPerson(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Deliveryperson > listDeliveryPerson = deliveryPersonDAO.selectAllDeliveryPersons();
        request.setAttribute("listDeliveryPerson", listDeliveryPerson);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/deliveryPerson-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/deliveryPerson-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Deliveryperson existingDeliveryPerson = deliveryPersonDAO.selectDeliveryPerson(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/deliveryPerson-form.jsp");
        request.setAttribute("deliveryPerson", existingDeliveryPerson);
        dispatcher.forward(request, response);

    }

    private void insertDeliveryPerson(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	String deliveryPersonFirstName = request.getParameter("deliveryPersonFirstName");
    	String deliveryPersonLastName = request.getParameter("deliveryPersonLastName");
    	String deliveryPersonStreet1 = request.getParameter("deliveryPersonStreet1");
    	String deliveryPersonStreet2 = request.getParameter("deliveryPersonStreet2");
    	String deliveryPersonCity = request.getParameter("deliveryPersonCity");
    	String deliveryPersonState = request.getParameter("deliveryPersonState");
    	String deliveryPersonZip = request.getParameter("deliveryPersonZip");
    	String deliveryPersonPhone = request.getParameter("deliveryPersonPhone");
    	int deliveryAreaID = Integer.parseInt(request.getParameter("deliveryAreaID"));                
        Deliveryperson newDeliveryPerson = new Deliveryperson(deliveryPersonCity, deliveryPersonFirstName, deliveryPersonLastName,
    			deliveryPersonPhone, deliveryPersonState, deliveryPersonStreet1,
    			deliveryPersonStreet2, deliveryPersonZip, deliveryAreaID);
        deliveryPersonDAO.insertDeliveryPerson(newDeliveryPerson);
        response.sendRedirect("deliveryPersons?action=list");
    }

    private void updateDeliveryPerson(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
    	String deliveryPersonFirstName = request.getParameter("deliveryPersonFirstName");
    	String deliveryPersonLastName = request.getParameter("deliveryPersonLastName");
    	String deliveryPersonStreet1 = request.getParameter("deliveryPersonStreet1");
    	String deliveryPersonStreet2 = request.getParameter("deliveryPersonStreet2");
    	String deliveryPersonCity = request.getParameter("deliveryPersonCity");
    	String deliveryPersonState = request.getParameter("deliveryPersonState");
    	String deliveryPersonZip = request.getParameter("deliveryPersonZip");
    	String deliveryPersonPhone = request.getParameter("deliveryPersonPhone");
    	int deliveryAreaID = Integer.parseInt(request.getParameter("deliveryAreaID"));                

        Deliveryperson book = new Deliveryperson(id, deliveryPersonCity, deliveryPersonFirstName, deliveryPersonLastName,
    			deliveryPersonPhone, deliveryPersonState, deliveryPersonStreet1,
    			deliveryPersonStreet2, deliveryPersonZip, deliveryAreaID);
        deliveryPersonDAO.updateDeliveryPerson(book);
        response.sendRedirect("deliveryPersons?action=list");
    }

    private void deleteDeliveryPerson(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        deliveryPersonDAO.deleteDeliveryPerson(id);
        response.sendRedirect("deliveryPersons?action=list");

    }
}