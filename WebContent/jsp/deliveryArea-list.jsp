<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>DeliveryArea Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>
            <jsp:include page="menu-header.jsp" />
            
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Delivery Areas</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/deliveryAreas?action=new" class="btn btn-success">Add New Delivery Area</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Delivery Area ID</th>
                                <th>Delivery Area Name</th>
                                <th>Delivery Area Zip</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="deliveryArea" items="${listDeliveryArea}">

                                <tr>
                                    <td>
                                        <c:out value="${deliveryArea.deliveryAreaID}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryArea.deliveryAreaName}" />
                                    </td>
                                    <td>
                                        <c:out value="${deliveryArea.deliveryAreaZip}" />
                                    </td>

                                    <td><a href="deliveryAreas?action=edit&id=<c:out value='${deliveryArea.deliveryAreaID}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deliveryAreas?action=delete&id=<c:out value='${deliveryArea.deliveryAreaID}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>