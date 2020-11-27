<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Employee Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <%-- <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="<%=request.getContextPath()%>" class="navbar-brand"> Employee Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/employees?action=list" class="nav-link">Employees</a></li>
                    </ul>
                </nav>
            </header> --%>
            
            <jsp:include page="menu-header.jsp" />
            
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Employees</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/employees?action=new" class="btn btn-success">Add
     New Employee</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>EmployeeID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Street1</th>
                                <th>Street2</th>
                                <th>City</th>
                                <th>State</th>
                                <th>Zip</th>
                                <th>Phone</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="employee" items="${listEmployee}">

                                <tr>
                                    <td>
                                        <c:out value="${employee.employeeID}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeFirstName}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeLastName}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeStreet1}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeStreet2}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeCity}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeState}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeeZip}" />
                                    </td>
                                    <td>
                                        <c:out value="${employee.employeePhone}" />
                                    </td>
                                    <td><a href="employees?action=edit&id=<c:out value='${employee.employeeID}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="employees?action=delete&id=<c:out value='${employee.employeeID}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>