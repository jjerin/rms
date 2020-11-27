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

import com.unt.csce5350.rms.dao.MenuDAO;
import com.unt.csce5350.rms.updated.model.Menuitem;


@WebServlet("/menus")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MenuDAO menuDAO;

    public void init() {
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
                    insertMenu(request, response);
                    break;
                case "delete":
                    deleteMenu(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateMenu(request, response);
                    break;
                default:
                    listMenu(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMenu(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Menuitem > listMenu = menuDAO.selectAllMenus();
        request.setAttribute("listMenu", listMenu);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/menu-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/menu-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Menuitem existingMenu = menuDAO.selectMenu(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/menu-form.jsp");
        request.setAttribute("menu", existingMenu);
        dispatcher.forward(request, response);

    }

    private void insertMenu(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String menuItemName = request.getParameter("menuItemName");
        String menuItemDescription = request.getParameter("menuItemDescription");
        String menuItemType = request.getParameter("menuItemType");
        BigDecimal menuItemPrice = new BigDecimal(request.getParameter("menuItemPrice"));

        Menuitem newMenu = new Menuitem(menuItemDescription, menuItemName, menuItemPrice, menuItemType);
        menuDAO.insertMenu(newMenu);
        response.sendRedirect("menus?action=list");
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String menuItemName = request.getParameter("menuItemName");
        String menuItemDescription = request.getParameter("menuItemDescription");
        String menuItemType = request.getParameter("menuItemType");
        BigDecimal menuItemPrice = new BigDecimal(request.getParameter("menuItemPrice"));

        
        Menuitem book = new Menuitem(id, menuItemDescription, menuItemName, menuItemPrice, menuItemType);
        menuDAO.updateMenu(book);
        response.sendRedirect("menus?action=list");
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        menuDAO.deleteMenu(id);
        response.sendRedirect("menus?action=list");

    }
}