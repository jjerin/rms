<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>DeliveryPerson Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
            <jsp:include page="menu-header.jsp" />
            
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Delivery Persons</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/deliveryPersons?action=new" class="btn btn-success">Add New Delivery Person</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>FirstName</th>
                                <th>LastName</th>
                                <th>Street1</th>
                                <th>Street2</th>
                                <th>City</th>
                                <th>State</th>
                                <th>Zip</th>
                                <th>Phone</th>
                                <th>Delivery Area ID</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="deliveryPerson" items="${listDeliveryPerson}">

                                <tr>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonID}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonFirstName}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonLastName}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonStreet1}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonStreet2}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonCity}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonState}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonZip}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryPersonPhone}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryPerson.deliveryAreaID}" />
                                    </td>
                                    <td><a href="deliveryPersons?action=edit&id=<c:out value='${deliveryPerson.deliveryPersonID}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deliveryPersons?action=delete&id=<c:out value='${deliveryPerson.deliveryPersonID}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>