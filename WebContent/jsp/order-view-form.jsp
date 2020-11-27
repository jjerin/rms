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
            <div class="container col-md-4">
                <div class="card">
                
                    <div class="card-body">
                                               <div style="float: right;"><button class="btn btn-success" onclick="window.print()">Print Order</button></div>
                    
                        <c:if test="${order != null}">
                            <form action="orders?action=update" method="post">
                        </c:if>
                        <c:if test="${order == null}">
                            <form action="orders?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${order != null}">
                                    View Order
                                </c:if>
                                <c:if test="${order == null}">
                                    Add New Order
                                </c:if>
                                
                            </h2>
                            
                        </caption>

                        <c:if test="${order != null}">
                            <input type="hidden" name="id" value="<c:out value='${order.orderID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <%-- <label>Employee ID</label> <input type="text" value="<c:out value='${order.employeeID}' />" class="form-control" name="employeeID" required="required"> --%>
                            <label >Employee</label>
							<select disabled="true" name="employeeID" class="form-control">
								<c:forEach items="${employeeSelectList}" var="employee">
									<option value="${employee.employeeId}"
										<c:if test="${employee.employeeId eq order.employeeID}">selected="selected"</c:if>
										>
										${employee.employeeDisplayName}
									</option>
								</c:forEach>
							</select>
						</fieldset>

                        <%-- <fieldset class="form-group">
                            <label>Delivery Person ID</label> <input type="text" value="<c:out value='${order.deliveryPersonID}' />" class="form-control" name="deliveryPersonID">
                        </fieldset> --%>
                        <fieldset class="form-group">
                         	<label>DeliveryPerson</label>
							<select disabled="true" name="deliveryPersonID" class="form-control">
								<c:forEach items="${deliveryPersonSelectList}" var="deliveryPerson">
									<option value="${deliveryPerson.deliveryPersonId}"
										<c:if test="${deliveryPerson.deliveryPersonId eq order.deliveryPersonID}">selected="selected"</c:if>
										>
										${deliveryPerson.deliveryPersonDisplayName}
									</option>
								</c:forEach>
							</select>
						</fieldset>
                        

                        <%-- <fieldset class="form-group">
                            <label>Customer ID</label> <input type="text" value="<c:out value='${order.customerID}' />" class="form-control" name="customerID">
                        </fieldset> --%>
                        <fieldset class="form-group">
                            <%-- <label>Employee ID</label> <input type="text" value="<c:out value='${order.employeeID}' />" class="form-control" name="employeeID" required="required"> --%>
                            <label>Customer</label>
							<select  disabled="true" name="customerID" class="form-control">
								<c:forEach items="${customerSelectList}" var="customer">
									<option value="${customer.customerId}"
										<c:if test="${customer.customerId eq order.customerID}">selected="selected"</c:if>
										>
										${customer.customerDisplayName}
									</option>
								</c:forEach>
							</select>
						</fieldset>



                        <%-- <fieldset class="form-group">
                            <label>Order Type</label> <input type="text" value="<c:out value='${order.orderType}' />" class="form-control" name="orderType">
                        </fieldset> --%>
                        
						<fieldset class="form-group">
                         	<label>OrderType</label>
							<select  disabled="true" name="orderType" class="form-control">
								<c:forEach items="${orderTypeSelectList}" var="orderType">
									<option value="${orderType.orderTypeId}"
										<c:if test="${orderType.orderTypeId eq order.orderType}">selected="selected"</c:if>
										>
										${orderType.orderTypeName}
									</option>
								</c:forEach>
							</select>
						</fieldset>

                        <fieldset class="form-group">
                            <label>Order Total Cost</label> <input  readonly type="text" value="<c:out value='${order.orderTotalCost}' />" class="form-control" name="orderTotalCost">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Delivery Address ID</label> <input readonly type="text" value="<c:out value='${order.deliveryAddressID}' />" class="form-control" name="deliveryAddressID">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Order Date Time</label> <input readonly type="text" value="<c:out value='${order.orderDateTime}' />" class="form-control" name="orderDateTime">
                        </fieldset>
                        
                        <br/>
                        <br/>
                        
                        <table>
                        	<tr>
                        		<th>Menu Item ID</th>
                        		<th>Menu Item Quantity</th>
                        		<th>Price</th>
                        		<th>Comments</th>
                        	</tr>
                        	<tr>
                        		<td>
                        			<%-- <fieldset class="form-group">
			                            <input type="text" value="<c:out value='${order.orderDetailList[0].menuItemId}' />" class="form-control" name="od0_menuItemId">
			                        </fieldset> --%>
									<fieldset class="form-group">
										<select  disabled="true"  name="od0_menuItemId" class="form-control">
											<c:forEach items="${menuItemSelectList}" var="menuItem">
												<option value="${menuItem.menuItemId}"
													<c:if test="${menuItem.menuItemId eq order.orderDetailList[0].menuItemId}">selected="selected"</c:if>>
													${menuItem.menuItemDisplayName}</option>
											</c:forEach>
										</select>
									</fieldset>
								</td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input readonly type="text" value="<c:out value='${order.orderDetailList[0].menuItemQuantity}' />" class="form-control" name="od0_menuItemQuantity">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input readonly type="text" value="<c:out value='${order.orderDetailList[0].orderDetailPrice}' />" class="form-control" name="od0_orderDetailPrice">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input readonly type="text" value="<c:out value='${order.orderDetailList[0].orderDetailsComments}' />" class="form-control" name="od0_orderDetailsComments">
			                        </fieldset>
			                        			
                        		</td>
                        	</tr>


                        	<tr>
                        		<td>
                        			<%-- <fieldset class="form-group">
			                            <input type="text" value="<c:out value='${order.orderDetailList[1].menuItemId}' />" class="form-control" name="od1_menuItemId">
			                        </fieldset> --%>
			                        <fieldset class="form-group">
										<select  disabled="true" name="od1_menuItemId" class="form-control">
											<c:forEach items="${menuItemSelectList}" var="menuItem">
												<option value="${menuItem.menuItemId}"
													<c:if test="${menuItem.menuItemId eq order.orderDetailList[1].menuItemId}">selected="selected"</c:if>>
													${menuItem.menuItemDisplayName}</option>
											</c:forEach>
										</select>
									</fieldset>
			                        
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[1].menuItemQuantity}' />" class="form-control" name="od1_menuItemQuantity">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[1].orderDetailPrice}' />" class="form-control" name="od1_orderDetailPrice">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[1].orderDetailsComments}' />" class="form-control" name="od1_orderDetailsComments">
			                        </fieldset>
			                        			
                        		</td>
                        	</tr>


                        	<tr>
                        		<td>
                        			<%-- <fieldset class="form-group">
			                            <input type="text" value="<c:out value='${order.orderDetailList[2].menuItemId}' />" class="form-control" name="od2_menuItemId">
			                        </fieldset> --%>
									<fieldset class="form-group">
										<select  disabled="true" name="od2_menuItemId" class="form-control">
											<c:forEach items="${menuItemSelectList}" var="menuItem">
												<option value="${menuItem.menuItemId}"
													<c:if test="${menuItem.menuItemId eq order.orderDetailList[2].menuItemId}">selected="selected"</c:if>>
													${menuItem.menuItemDisplayName}</option>
											</c:forEach>
										</select>
									</fieldset>
			                        
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[2].menuItemQuantity}' />" class="form-control" name="od2_menuItemQuantity">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[2].orderDetailPrice}' />" class="form-control" name="od2_orderDetailPrice">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[2].orderDetailsComments}' />" class="form-control" name="od2_orderDetailsComments">
			                        </fieldset>
			                        			
                        		</td>
                        	</tr>



                        	<tr>
                        		<td>
                        			<%-- <fieldset class="form-group">
			                            <input type="text" value="<c:out value='${order.orderDetailList[3].menuItemId}' />" class="form-control" name="od3_menuItemId">
			                        </fieldset> --%>
									<fieldset class="form-group">
										<select  disabled="true" name="od3_menuItemId" class="form-control">
											<c:forEach items="${menuItemSelectList}" var="menuItem">
												<option value="${menuItem.menuItemId}"
													<c:if test="${menuItem.menuItemId eq order.orderDetailList[3].menuItemId}">selected="selected"</c:if>>
													${menuItem.menuItemDisplayName}</option>
											</c:forEach>
										</select>
									</fieldset>
			                        
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[3].menuItemQuantity}' />" class="form-control" name="od3_menuItemQuantity">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[3].orderDetailPrice}' />" class="form-control" name="od3_orderDetailPrice">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[3].orderDetailsComments}' />" class="form-control" name="od3_orderDetailsComments">
			                        </fieldset>
			                        			
                        		</td>
                        	</tr>

                        	<tr>
                        		<td>
                        			<%-- <fieldset class="form-group">
			                            <input type="text" value="<c:out value='${order.orderDetailList[4].menuItemId}' />" class="form-control" name="od4_menuItemId">
			                        </fieldset> --%>
									<fieldset class="form-group">
										<select  disabled="true" name="od4_menuItemId" class="form-control">
											<c:forEach items="${menuItemSelectList}" var="menuItem">
												<option value="${menuItem.menuItemId}"
													<c:if test="${menuItem.menuItemId eq order.orderDetailList[4].menuItemId}">selected="selected"</c:if>>
													${menuItem.menuItemDisplayName}</option>
											</c:forEach>
										</select>
									</fieldset>
			                        
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[4].menuItemQuantity}' />" class="form-control" name="od4_menuItemQuantity">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[4].orderDetailPrice}' />" class="form-control" name="od4_orderDetailPrice">
			                        </fieldset>
		                        </td>
		                        <td>
                        			<fieldset class="form-group">
			                            <input type="text" readonly value="<c:out value='${order.orderDetailList[4].orderDetailsComments}' />" class="form-control" name="od4_orderDetailsComments">
			                        </fieldset>
			                        			
                        		</td>
                        	</tr>

                        	
                        
                        </table>

		                <!-- <button type="submit" class="btn btn-success">Save</button> -->
		
						<table>
							<tr>
								<td><a href="payments?action=orderPayment&orderID=<c:out value='${order.orderID}' />">Process Payment</a> &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td><a
									href="delivery?action=edit&id=<c:out value='${order.orderID}' />">Setup Delivery</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>
						</form>
                    </div>
                </div>
            </div>

        
        
        </body>

        </html>