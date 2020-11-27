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

import com.unt.csce5350.rms.dao.CustomerDAO;
import com.unt.csce5350.rms.dao.DeliveryAddressDAO;
import com.unt.csce5350.rms.dao.OrderDAO;
import com.unt.csce5350.rms.dao.OrderDeliveryDAO;
import com.unt.csce5350.rms.updated.model.Customer;
import com.unt.csce5350.rms.updated.model.Deliveryaddress;
import com.unt.csce5350.rms.updated.model.Order;
import com.unt.csce5350.rms.updated.select.DeliveryAreaSelect;
import com.unt.csce5350.rms.updated.select.DeliveryPersonSelect;
import com.unt.csce5350.rms.updated.select.ZipCodeSelect;
import com.unt.csce5350.rms.utils.SelectUtils;


@WebServlet("/delivery")
public class OrderDeliveryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;
    private OrderDeliveryDAO orderDeliveryDAO;
    private DeliveryAddressDAO deliveryAddressDAO;

    public void init() {
        customerDAO = new CustomerDAO();
        orderDeliveryDAO = new OrderDeliveryDAO();
        orderDAO = new OrderDAO();
        deliveryAddressDAO = new DeliveryAddressDAO();
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
                    //insertCustomer(request, response);
                    break;
                case "delete":
                    //deleteCustomer(request, response);
                    break;
                case "edit":
                    showAreaSelectForm(request, response);
                    break;
                case "deliveryAddressSave":
                    deliveryAddressSave(request, response);
                    break;
                case "areaSelect":
                	showDeliveryAddressForm(request, response);
                	break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Customer > listCustomer = customerDAO.selectAllCustomers();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showAreaSelectForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Order order = orderDAO.selectOrder(id);
        
        if(order.getDeliveryPersonID() ==0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/order-delivery-area-form.jsp");
            
            List<DeliveryAreaSelect> deliveryAreaSelectList = SelectUtils.getDeliveryAreaSelectList();
            request.setAttribute("deliveryAreaSelectList", deliveryAreaSelectList);
            
            request.setAttribute("order", order);
            dispatcher.forward(request, response);
            return;
       	
        }
        
		/*Customer existingCustomer = customerDAO.selectCustomer(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/customer-form.jsp");
		request.setAttribute("customer", existingCustomer);
		dispatcher.forward(request, response);*/

    }
    
    
    private void showDeliveryAddressForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int selectedDeliveryArea = Integer.parseInt(request.getParameter("deliveryAreaSelect"));

        
        Order order = orderDAO.selectOrder(id);

        List<DeliveryAreaSelect> deliveryAreaSelectList = SelectUtils.getDeliveryAreaSelectList();
        request.setAttribute("deliveryAreaSelectList", deliveryAreaSelectList);

        List<DeliveryPersonSelect> deliveryPersonSelectList = SelectUtils.getDeliveryPersonSelectList(selectedDeliveryArea);
        request.setAttribute("deliveryPersonSelectList", deliveryPersonSelectList);
        request.setAttribute("selectedDeliveryArea", selectedDeliveryArea);

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/order-delivery-address-form.jsp");
        request.setAttribute("order", order);

        dispatcher.forward(request, response);
    }
    
    
    private void deliveryAddressSave(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
 
        int id = Integer.parseInt(request.getParameter("id"));

    	
    	String deliveryAddressStreet1 = request.getParameter("deliveryAddressStreet1");
        String deliveryAddressStreet2 = request.getParameter("deliveryAddressStreet2");
        String deliveryAddressCity = request.getParameter("deliveryAddressCity");
        String deliveryAddressState = request.getParameter("deliveryAddressState");
        String deliveryAddressZip = request.getParameter("deliveryAddressZip");

        int deliveryPersonId = Integer.parseInt(request.getParameter("deliveryPersonSelect"));

        Deliveryaddress deliveryAddress = new Deliveryaddress(deliveryAddressCity, deliveryAddressState, deliveryAddressStreet1,
    			deliveryAddressStreet2, deliveryAddressZip);


        int deliveryAddressId = deliveryAddressDAO.insertDeliveryAddress(deliveryAddress);
        
        orderDAO.updateDeliveryDetails(id, deliveryPersonId, deliveryAddressId);
        //deliveryaddressDAO.insertCustomer(newCustomer);
        response.sendRedirect("orders?action=list");
    }


}