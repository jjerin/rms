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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${employee != null}">
                            <form action="employees?action=update" method="post">
                        </c:if>
                        <c:if test="${employee == null}">
                            <form action="employees?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${employee != null}">
                                    Edit Employee
                                </c:if>
                                <c:if test="${employee == null}">
                                    Add New Employee
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${employee != null}">
                            <input type="hidden" name="id" value="<c:out value='${employee.employeeID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>First Name</label> <input type="text" value="<c:out value='${employee.employeeFirstName}' />" class="form-control" name="employeeFirstName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Last Name</label> <input type="text" value="<c:out value='${employee.employeeLastName}' />" class="form-control" name="employeeLastName">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Street 1</label> <input type="text" value="<c:out value='${employee.employeeStreet1}' />" class="form-control" name="employeeStreet1">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Street 2</label> <input type="text" value="<c:out value='${employee.employeeStreet2}' />" class="form-control" name="employeeStreet2" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>City</label> <input type="text" value="<c:out value='${employee.employeeCity}' />" class="form-control" name="employeeCity">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>State</label> <input type="text" value="<c:out value='${employee.employeeState}' />" class="form-control" name="employeeState">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Zip</label> <input type="text" value="<c:out value='${employee.employeeZip}' />" class="form-control" name="employeeZip" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Phone</label> <input type="text" value="<c:out value='${employee.employeePhone}' />" class="form-control" name="employeePhone">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>