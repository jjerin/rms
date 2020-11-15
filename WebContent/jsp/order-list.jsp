<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Order Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
            <jsp:include page="menu-header.jsp" />
            
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Orders</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/orders?action=new" class="btn btn-success">Add
     New Order</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Country</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="order" items="${listOrder}">

                                <tr>
                                    <td>
                                        <c:out value="${order.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${order.country}" />
                                    </td>
                                    <td><a href="orders?action=edit&id=<c:out value='${order.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="orders?action=delete&id=<c:out value='${order.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>