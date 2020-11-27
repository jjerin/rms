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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${deliveryPerson != null}">
                            <form action="deliveryPersons?action=update" method="post">
                        </c:if>
                        <c:if test="${deliveryPerson == null}">
                            <form action="deliveryPersons?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${deliveryPerson != null}">
                                    Edit Delivery Person
                                </c:if>
                                <c:if test="${deliveryPerson == null}">
                                    Add New Delivery Person
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${deliveryPerson != null}">
                            <input type="hidden" name="id" value="<c:out value='${deliveryPerson.deliveryPersonID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>First Name</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonFirstName}' />" class="form-control" name="deliveryPersonFirstName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Last Name</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonLastName}' />" class="form-control" name="deliveryPersonLastName">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Street 1</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonStreet1}' />" class="form-control" name="deliveryPersonStreet1">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Street 2</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonStreet2}' />" class="form-control" name="deliveryPersonStreet2" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>City</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonCity}' />" class="form-control" name="deliveryPersonCity">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>State</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonState}' />" class="form-control" name="deliveryPersonState">
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Zip</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonZip}' />" class="form-control" name="deliveryPersonZip" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Phone</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryPersonPhone}' />" class="form-control" name="deliveryPersonPhone">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Delivery Area ID</label> <input type="text" value="<c:out value='${deliveryPerson.deliveryAreaID}' />" class="form-control" name="deliveryAreaID">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>