package com.unt.csce5350.rms.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unt.csce5350.rms.dao.OrderDAO;
import com.unt.csce5350.rms.dao.PaymentDAO;
import com.unt.csce5350.rms.updated.model.Order;
import com.unt.csce5350.rms.updated.model.Payment;
import com.unt.csce5350.rms.utils.AppUtils;


@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDAO paymentDAO;
    private OrderDAO orderDAO;

    public void init() {
        paymentDAO = new PaymentDAO();
        orderDAO = new OrderDAO();
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
                    insertPayment(request, response);
                    break;
                case "delete":
                    deletePayment(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updatePayment(request, response);
                    break;
                case "orderPayment":
                	showOrderPaymentForm(request, response);
                    break;
                default:
                    listPayment(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPayment(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Payment > listPayment = paymentDAO.selectAllPayments();
        request.setAttribute("listPayment", listPayment);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/payment-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/payment-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {

    	int id = Integer.parseInt(request.getParameter("id"));
        Payment existingPayment = paymentDAO.selectPayment(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/payment-form.jsp");
        request.setAttribute("payment", existingPayment);
        dispatcher.forward(request, response);

    }
    
    private void showOrderPaymentForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        
        int orderId = 0;
        if(!AppUtils.isEmpty(request.getParameter("orderID"))) {
        	orderId = Integer.parseInt(request.getParameter("orderID"));
        	Order order = orderDAO.selectOrder(orderId);
        	if(order!=null) {
        		BigDecimal orderTotal = order.getOrderTotalCost();
        		
        		Payment payment = new Payment();
        		payment.setPaymentTotalPaid(orderTotal);
        		payment.setOrderID(orderId);
        		payment.setPaymentID(0);
        		
                RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/payment-form.jsp");
                request.setAttribute("payment", payment);
                dispatcher.forward(request, response);
                
                return;

        	}
        }

		/*int id = Integer.parseInt(request.getParameter("id"));
		Payment existingPayment = paymentDAO.selectPayment(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/payment-form.jsp");
		request.setAttribute("payment", existingPayment);
		dispatcher.forward(request, response);*/

    }

    private void insertPayment(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String paymentType = request.getParameter("paymentType");
        String paymentDateStr = request.getParameter("paymentDate");
        BigDecimal paymentTotalPaid = new BigDecimal(request.getParameter("paymentTotalPaid"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        
        java.sql.Date paymentDate = new java.sql.Date(System.currentTimeMillis()); //Fix the date?  or should we?, this is current date.
        
        
        Payment newPayment = new Payment(orderID, paymentDate, paymentTotalPaid, paymentType);
        paymentDAO.insertPayment(newPayment);
        response.sendRedirect("payments?action=list");
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String paymentType = request.getParameter("paymentType");
        String paymentDateStr = request.getParameter("paymentDate");
        BigDecimal paymentTotalPaid = new BigDecimal(request.getParameter("paymentTotalPaid"));
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        
        java.sql.Date paymentDate = new java.sql.Date(System.currentTimeMillis()); //Fix the date?  or should we?, this is current date.
        
        
        Payment book = new Payment(id, orderID, paymentDate, paymentTotalPaid, paymentType);

        paymentDAO.updatePayment(book);
        response.sendRedirect("payments?action=list");
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        paymentDAO.deletePayment(id);
        response.sendRedirect("payments?action=list");

    }
}