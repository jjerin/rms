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
                         <form action="delivery?action=deliveryAddressSave" method="post">


                        <caption>
                            <h2>
                                 Enter Delivery Address
                            </h2>
                        </caption>

                        <c:if test="${order != null}">
                            <input type="hidden" name="id" value="<c:out value='${order.orderID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Street 1</label> <input type="text" value="<c:out value='${deliveryAddress.deliveryAddressStreet1}' />" class="form-control" name="deliveryAddressStreet1" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Street 2</label> <input type="text" value="<c:out value='${deliveryAddress.deliveryAddressStreet2}' />" class="form-control" name="deliveryAddressStreet2">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>City</label> <input type="text" value="<c:out value='${deliveryAddress.deliveryAddressCity}' />" class="form-control" name="deliveryAddressCity">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>State</label> <input type="text" value="<c:out value='${deliveryAddress.deliveryAddressState}' />" class="form-control" name="deliveryAddressState" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Zip</label> <input type="text" value="<c:out value='${deliveryAddress.deliveryAddressZip}' />" class="form-control" name="deliveryAddressZip">
                        </fieldset>
                        
                        
                        <fieldset class="form-group">
                            <label >Delivery Area</label>
							<select disabled="true" name="deliveryAreaSelect" class="form-control">
								<c:forEach items="${deliveryAreaSelectList}" var="deliveryAreaSelect">
									<option value="${deliveryAreaSelect.deliveryAreaId}"
									<c:if test="${deliveryAreaSelect.deliveryAreaId eq selectedDeliveryArea}">selected="selected"</c:if>
									>
										${deliveryAreaSelect.deliveryAreaDisplayName}
									</option>
								</c:forEach>
							</select>
						</fieldset>
						
						
                        <fieldset class="form-group">
                            <label >Delivery Person</label>
							<select name="deliveryPersonSelect" class="form-control">
								<c:forEach items="${deliveryPersonSelectList}" var="deliveryPersonSelect">
									<option value="${deliveryPersonSelect.deliveryPersonId}">
										${deliveryPersonSelect.deliveryPersonDisplayName}
									</option>
								</c:forEach>
							</select>
						</fieldset>						
						
						

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>