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
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <form action="delivery?action=areaSelect" method="post">
                        
                        <caption>
                            <h2>
                              Select Delivery Area
                           </h2>
                        </caption>

                        <c:if test="${order != null}">
                            <input type="hidden" name="id" value="<c:out value='${order.orderID}' />" />
                        </c:if>
                        
                        
                        <fieldset class="form-group">
                            <label >Delivery Area</label>
							<select name="deliveryAreaSelect" class="form-control">
								<c:forEach items="${deliveryAreaSelectList}" var="deliveryAreaSelect">
									<option value="${deliveryAreaSelect.deliveryAreaId}">
										${deliveryAreaSelect.deliveryAreaDisplayName}
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