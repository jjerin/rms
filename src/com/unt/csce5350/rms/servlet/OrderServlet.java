package com.unt.csce5350.rms.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unt.csce5350.rms.dao.MenuDAO;
import com.unt.csce5350.rms.dao.OrderDAO;
import com.unt.csce5350.rms.updated.model.Menuitem;
import com.unt.csce5350.rms.updated.model.Order;
import com.unt.csce5350.rms.updated.model.Orderdetail;
import com.unt.csce5350.rms.updated.select.CustomerSelect;
import com.unt.csce5350.rms.updated.select.DeliveryPersonSelect;
import com.unt.csce5350.rms.updated.select.EmployeeSelect;
import com.unt.csce5350.rms.updated.select.MenuItemSelect;
import com.unt.csce5350.rms.updated.select.OrderTypeSelect;
import com.unt.csce5350.rms.utils.AppUtils;
import com.unt.csce5350.rms.utils.SelectUtils;


@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;
    private MenuDAO menuDAO;

    public void init() {
        orderDAO = new OrderDAO();
        menuDAO = new MenuDAO();
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
                    insertOrder(request, response);
                    break;
                case "delete":
                    deleteOrder(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateOrder(request, response);
                    break;
                case "view":
                	showViewForm(request, response);
                    break;
                default:
                    listOrder(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    


    private void listOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Order > listOrder = orderDAO.selectAllOrders();
        populateDisplayFields(listOrder);
        request.setAttribute("listOrder", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/order-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/order-form.jsp");
        
        List<EmployeeSelect> employeeSelectList = SelectUtils.getEmployeeSelectList();
        request.setAttribute("employeeSelectList", employeeSelectList);
        
        List<CustomerSelect> customerSelectList = SelectUtils.getCustomerSelectList();
        request.setAttribute("customerSelectList", customerSelectList);
        
        List<DeliveryPersonSelect> deliveryPersonSelectList = SelectUtils.getDeliveryPersonSelectList();
        request.setAttribute("deliveryPersonSelectList", deliveryPersonSelectList);
        
        List<OrderTypeSelect> orderTypeSelectList = SelectUtils.getOrderTypeSelectList();
        request.setAttribute("orderTypeSelectList", orderTypeSelectList);
       
        List<MenuItemSelect> menuItemSelectList = SelectUtils.getMenuItemSelectList();
        request.setAttribute("menuItemSelectList", menuItemSelectList);

        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Order existingOrder = orderDAO.selectOrder(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/order-form.jsp");
        List<EmployeeSelect> employeeSelectList = SelectUtils.getEmployeeSelectList();
        request.setAttribute("employeeSelectList", employeeSelectList);

        List<CustomerSelect> customerSelectList = SelectUtils.getCustomerSelectList();
        request.setAttribute("customerSelectList", customerSelectList);

        List<DeliveryPersonSelect> deliveryPersonSelectList = SelectUtils.getDeliveryPersonSelectList();
        request.setAttribute("deliveryPersonSelectList", deliveryPersonSelectList);
        
        List<OrderTypeSelect> orderTypeSelectList = SelectUtils.getOrderTypeSelectList();
        request.setAttribute("orderTypeSelectList", orderTypeSelectList);
        

        List<MenuItemSelect> menuItemSelectList = SelectUtils.getMenuItemSelectList();
        request.setAttribute("menuItemSelectList", menuItemSelectList);

        request.setAttribute("order", existingOrder);
        dispatcher.forward(request, response);

    }
    
    private void showViewForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
    	String idStr = request.getParameter("id");
    	System.out.println("Param id: "+idStr);
    	if(AppUtils.isEmpty(idStr)) {
    		idStr = (String)request.getAttribute("id");
    		System.out.println("Attribute id: "+idStr);
    	}
    	
    	
        int id = Integer.parseInt(idStr);
        Order existingOrder = orderDAO.selectOrder(id);
        populateDisplayFields(existingOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/order-view-form.jsp");
        List<EmployeeSelect> employeeSelectList = SelectUtils.getEmployeeSelectList();
        request.setAttribute("employeeSelectList", employeeSelectList);

        List<CustomerSelect> customerSelectList = SelectUtils.getCustomerSelectList();
        request.setAttribute("customerSelectList", customerSelectList);

        List<DeliveryPersonSelect> deliveryPersonSelectList = SelectUtils.getDeliveryPersonSelectList();
        request.setAttribute("deliveryPersonSelectList", deliveryPersonSelectList);
        
        List<OrderTypeSelect> orderTypeSelectList = SelectUtils.getOrderTypeSelectList();
        request.setAttribute("orderTypeSelectList", orderTypeSelectList);
        

        List<MenuItemSelect> menuItemSelectList = SelectUtils.getMenuItemSelectList();
        request.setAttribute("menuItemSelectList", menuItemSelectList);

        request.setAttribute("order", existingOrder);
        dispatcher.forward(request, response);

    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
       
    	int employeeID = Integer.parseInt(request.getParameter("employeeID"));
    	int deliveryPersonID = 0;
    	if(!AppUtils.isEmpty(request.getParameter("deliveryPersonID"))) {
    		deliveryPersonID = Integer.parseInt(request.getParameter("deliveryPersonID"));
    	}    	
    	int customerID = Integer.parseInt(request.getParameter("customerID"));
    	String orderType = request.getParameter("orderType");
    	BigDecimal orderTotalCost = new BigDecimal(0); //new BigDecimal(request.getParameter("orderTotalCost"));
    	int deliveryAddressID = 0;
    	
    	if(!AppUtils.isEmpty(request.getParameter("deliveryAddressID"))) {
    		deliveryAddressID = Integer.parseInt(request.getParameter("deliveryAddressID"));
    	}
    	String orderDateTimeStr = request.getParameter("orderDateTime");
        java.sql.Date orderDateTime = new java.sql.Date(System.currentTimeMillis()); //Fix the date?  or should we?, this is current date.
        
        List<Orderdetail> orderDetailList = new ArrayList<>();
        
        List<Menuitem> menuList = menuDAO.selectAllMenus();
        for(int i=0;i<5;i++) {
        	System.out.println("od"+i+"_menuItemId: "+request.getParameter("od"+i+"_menuItemId"));
        	System.out.println("od"+i+"_menuItemQuantity: "+request.getParameter("od"+i+"_menuItemQuantity"));
        	System.out.println("od"+i+"_orderDetailPrice: "+request.getParameter("od"+i+"_orderDetailPrice"));
        	System.out.println("od"+i+"_orderDetailsComments: "+request.getParameter("od"+i+"_orderDetailsComments"));
        	
        	String menuItemIdStr = request.getParameter("od"+i+"_menuItemId");
        	System.out.println("Row: "+i+"; menuItemIdStr: "+menuItemIdStr);
        	if(!AppUtils.isEmpty(menuItemIdStr) && !menuItemIdStr.equals("0")) {
	        	int menuItemId = Integer.parseInt(menuItemIdStr);
	        	int menuItemQuantity = Integer.parseInt(request.getParameter("od"+i+"_menuItemQuantity"));
	        	BigDecimal orderDetailPrice = getMenuPrice(menuItemId, menuList).multiply(new BigDecimal (menuItemQuantity));//new BigDecimal(request.getParameter("od"+i+"_orderDetailPrice"));
	        	orderTotalCost = orderTotalCost.add(orderDetailPrice);
	        	String orderDetailsComments = request.getParameter("od"+i+"_orderDetailsComments");
	        	
	        	Orderdetail od = new Orderdetail(menuItemId, menuItemQuantity, orderDetailPrice, orderDetailsComments);
	        	if(menuItemId !=0) {
	        		orderDetailList.add(od);
	        	}
        	}
        }
    	
        Order newOrder = new Order(orderDateTime, orderTotalCost, orderType, employeeID,
    			deliveryPersonID, customerID, deliveryAddressID);
        
        newOrder.setOrderDetailList(orderDetailList);
        
        int id = orderDAO.insertOrder(newOrder);
        response.sendRedirect("orders?action=view&id="+id);

        //response.sendRedirect("orders?action=list");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
    	int employeeID = Integer.parseInt(request.getParameter("employeeID"));
    	int deliveryPersonID = 0;
    	if(!AppUtils.isEmpty(request.getParameter("deliveryPersonID"))) {
    		deliveryPersonID = Integer.parseInt(request.getParameter("deliveryPersonID"));
    	}  
    	int customerID = Integer.parseInt(request.getParameter("customerID"));
    	String orderType = request.getParameter("orderType");
    	BigDecimal orderTotalCost = new BigDecimal(0); //new BigDecimal(request.getParameter("orderTotalCost"));
    	int deliveryAddressID = 0;
    	if(!AppUtils.isEmpty(request.getParameter("deliveryAddressID"))) {
    		deliveryAddressID = Integer.parseInt(request.getParameter("deliveryAddressID"));
    	}
    	String orderDateTimeStr = request.getParameter("orderDateTime");
        java.sql.Date orderDateTime = new java.sql.Date(System.currentTimeMillis()); //Fix the date?  or should we?, this is current date.
        
        List<Orderdetail> orderDetailList = new ArrayList<>();
        
        List<Menuitem> menuList = menuDAO.selectAllMenus();
        
        for(int i=0;i<5;i++) {
        	System.out.println("od"+i+"_menuItemId: "+request.getParameter("od"+i+"_menuItemId"));
        	System.out.println("od"+i+"_menuItemQuantity: "+request.getParameter("od"+i+"_menuItemQuantity"));
        	System.out.println("od"+i+"_orderDetailPrice: "+request.getParameter("od"+i+"_orderDetailPrice"));
        	System.out.println("od"+i+"_orderDetailsComments: "+request.getParameter("od"+i+"_orderDetailsComments"));
        	
        	String menuItemIdStr = request.getParameter("od"+i+"_menuItemId");
        	System.out.println("Row: "+i+"; menuItemIdStr: "+menuItemIdStr);
        	if(!AppUtils.isEmpty(menuItemIdStr) && !menuItemIdStr.equals("0")) {
	        	int menuItemId = Integer.parseInt(menuItemIdStr);
	        	int menuItemQuantity = Integer.parseInt(request.getParameter("od"+i+"_menuItemQuantity"));
	        	BigDecimal orderDetailPrice = getMenuPrice(menuItemId, menuList).multiply(new BigDecimal (menuItemQuantity));//new BigDecimal(request.getParameter("od"+i+"_orderDetailPrice"));
	        	orderTotalCost = orderTotalCost.add(orderDetailPrice);
	        	String orderDetailsComments = request.getParameter("od"+i+"_orderDetailsComments");
	        	
	        	Orderdetail od = new Orderdetail(menuItemId, menuItemQuantity, orderDetailPrice, orderDetailsComments);
	        	if(menuItemId !=0) {
	        		orderDetailList.add(od);
	        	}
        	}
        }


        Order order = new Order(id, orderDateTime, orderTotalCost, orderType, employeeID,
    			deliveryPersonID, customerID, deliveryAddressID);
        order.setOrderDetailList(orderDetailList);
        orderDAO.updateOrder(order);
        request.setAttribute("id", id);
        response.sendRedirect("orders?action=view&id="+id);
    }
    
    private BigDecimal getMenuPrice(int menutItemId, List<Menuitem> menuList) {
    	
    	for(Menuitem menuItem: menuList) {
    		if(menuItem.getMenuItemID() == menutItemId) {
    			return menuItem.getMenuItemPrice();
    		}
    	}
    	
    	return new BigDecimal(0);
    	
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderDAO.deleteOrder(id);
        response.sendRedirect("orders?action=list");

    }
    
    
    public void populateDisplayFields(List<Order> orderList) {
    	
    	List<CustomerSelect> customerSelectList = SelectUtils.getCustomerSelectList();
    	List<EmployeeSelect> employeeSelectList = SelectUtils.getEmployeeSelectList();
    	List<DeliveryPersonSelect> deliveryPersonSelectList = SelectUtils.getDeliveryPersonSelectList();
    	Map<Integer, String> deliveryAddressMap = SelectUtils.getDeliveryAddressMap();
    	
    	for(Order order: orderList) {
    		
    		int customerId = order.getCustomerID();
    		
    		for(CustomerSelect cs: customerSelectList) {
    			if(customerId == cs.getCustomerId()) {
    				order.setCustomerDisplay(cs.getCustomerDisplayName());
    			}
    		}
    		
    		int employeeId = order.getEmployeeID();
    		for(EmployeeSelect cs: employeeSelectList) {
    			if(employeeId == cs.getEmployeeId()) {
    				order.setEmployeeDisplay(cs.getEmployeeDisplayName());
    			}
    		}
    		
    		int deliveryPersonId = order.getDeliveryPersonID();
    		for(DeliveryPersonSelect cs: deliveryPersonSelectList) {
    			if(deliveryPersonId == cs.getDeliveryPersonId()) {
    				order.setDeliveryPersonDisplay(cs.getDeliveryPersonDisplayName());
    			}
    		}
    		
    		order.setDeliveryAddressDisplay(deliveryAddressMap.get(order.getDeliveryAddressID()));
    		
    		
    	}
    }
    
    
    public void populateDisplayFields(Order order) {
    	
    	List<CustomerSelect> customerSelectList = SelectUtils.getCustomerSelectList();
    	List<EmployeeSelect> employeeSelectList = SelectUtils.getEmployeeSelectList();
    	List<DeliveryPersonSelect> deliveryPersonSelectList = SelectUtils.getDeliveryPersonSelectList();
    	Map<Integer, String> deliveryAddressMap = SelectUtils.getDeliveryAddressMap();
    	
    		
		int customerId = order.getCustomerID();
		
		for(CustomerSelect cs: customerSelectList) {
			if(customerId == cs.getCustomerId()) {
				order.setCustomerDisplay(cs.getCustomerDisplayName());
			}
		}
		
		int employeeId = order.getEmployeeID();
		for(EmployeeSelect cs: employeeSelectList) {
			if(employeeId == cs.getEmployeeId()) {
				order.setEmployeeDisplay(cs.getEmployeeDisplayName());
			}
		}
		
		int deliveryPersonId = order.getDeliveryPersonID();
		for(DeliveryPersonSelect cs: deliveryPersonSelectList) {
			if(deliveryPersonId == cs.getDeliveryPersonId()) {
				order.setDeliveryPersonDisplay(cs.getDeliveryPersonDisplayName());
			}
		}
		
		order.setDeliveryAddressDisplay(deliveryAddressMap.get(order.getDeliveryAddressID()));
    }
}