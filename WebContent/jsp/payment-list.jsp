<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Payment Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
            <jsp:include page="menu-header.jsp" />
            
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Payments</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/payments?action=new" class="btn btn-success">Add
     New Payment</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Payment Type</th>
                                <th>Date</th>
                                <th>TotalPaid</th>
                                <th>OrderID</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="payment" items="${listPayment}">

                                <tr>
                                    <td>
                                        <c:out value="${payment.paymentID}" />
                                    </td>
                                    <td>
                                        <c:out value="${payment.paymentType}" />
                                    </td>
                                    <td>
                                        <c:out value="${payment.paymentDate}" />
                                    </td>
                                    <td>
                                        <c:out value="${payment.paymentTotalPaid}" />
                                    </td>
                                    <td>
                                        <c:out value="${payment.orderID}" />
                                    </td>
                                    <td><a href="payments?action=edit&id=<c:out value='${payment.paymentID}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="payments?action=delete&id=<c:out value='${payment.paymentID}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>