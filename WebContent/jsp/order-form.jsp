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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${order != null}">
                            <form action="orders?action=update" method="post">
                        </c:if>
                        <c:if test="${order == null}">
                            <form action="orders?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${order != null}">
                                    Edit Order
                                </c:if>
                                <c:if test="${order == null}">
                                    Add New Order
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${order != null}">
                            <input type="hidden" name="id" value="<c:out value='${order.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Order Name</label> <input type="text" value="<c:out value='${order.name}' />" class="form-control" name="name" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Order Email</label> <input type="text" value="<c:out value='${order.email}' />" class="form-control" name="email">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Order Country</label> <input type="text" value="<c:out value='${order.country}' />" class="form-control" name="country">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>