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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${payment.paymentID != 0}">
                            <form action="payments?action=update" method="post">
                        </c:if>
                        <c:if test="${payment.paymentID == 0}">
                            <form action="payments?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${payment.paymentID != 0}">
                                    Edit Payment
                                </c:if>
                                <c:if test="${payment.paymentID == 0}">
                                    Add New Payment
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${payment != null}">
                            <input type="hidden" name="id" value="<c:out value='${payment.paymentID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Payment Type</label> <input type="text" value="<c:out value='${payment.paymentType}' />" class="form-control" name="paymentType" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Payment Date</label> <input type="text" value="<c:out value='${payment.paymentDate}' />" class="form-control" name="paymentDate">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Payment Total Paid</label> <input type="text" value="<c:out value='${payment.paymentTotalPaid}' />" class="form-control" name="paymentTotalPaid">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Payment Order Id</label> <input type="text" value="<c:out value='${payment.orderID}' />" class="form-control" name="orderID">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>