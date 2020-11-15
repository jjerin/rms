<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Menu Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <%-- <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="<%=request.getContextPath()%>" class="navbar-brand"> Menu Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/menus?action=list" class="nav-link">Menus</a></li>
                    </ul>
                </nav>
            </header> --%>
            
            <jsp:include page="menu-header.jsp" />
            
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Menus</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/menus?action=new" class="btn btn-success">Add
     New Menu</a>
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
                            <c:forEach var="menu" items="${listMenu}">

                                <tr>
                                    <td>
                                        <c:out value="${menu.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${menu.country}" />
                                    </td>
                                    <td><a href="menus?action=edit&id=<c:out value='${menu.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="menus?action=delete&id=<c:out value='${menu.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>