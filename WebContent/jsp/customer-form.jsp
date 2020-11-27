<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Restaurant Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <jsp:include page="menu-header.jsp" />
            
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${customer != null}">
                            <form action="customers?action=update" method="post">
                        </c:if>
                        <c:if test="${customer == null}">
                            <form action="customers?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${customer != null}">
                                    Edit Customer
                                </c:if>
                                <c:if test="${customer == null}">
                                    Add New Customer
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${customer != null}">
                            <input type="hidden" name="customerID" value="<c:out value='${customer.customerID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Customer First Name</label> <input type="text" value="<c:out value='${customer.customerFirstName}' />" class="form-control" name="customerFirstName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Last Name</label> <input type="text" value="<c:out value='${customer.customerLastName}' />" class="form-control" name="customerLastName">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Street 1</label> <input type="text" value="<c:out value='${customer.customerStreet1}' />" class="form-control" name="customerStreet1">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Street 2</label> <input type="text" value="<c:out value='${customer.customerStreet2}' />" class="form-control" name="customerStreet2" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer City</label> <input type="text" value="<c:out value='${customer.customerCity}' />" class="form-control" name="customerCity">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer State</label> <input type="text" value="<c:out value='${customer.customerState}' />" class="form-control" name="customerState">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Zip</label> <input type="text" value="<c:out value='${customer.customerZip}' />" class="form-control" name="customerZip" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Phone</label> <input type="text" value="<c:out value='${customer.customerPhone}' />" class="form-control" name="customerPhone">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Email</label> <input type="text" value="<c:out value='${customer.customerEmail}' />" class="form-control" name="customerEmail">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Orders</label> <input type="text" value="<c:out value='${customer.customerOrders}' />" class="form-control" name="customerOrders">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Current Discount</label> <input type="text" value="<c:out value='${customer.customerCurrentDiscount}' />" class="form-control" name="customerCurrentDiscount">
                        </fieldset>


                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>