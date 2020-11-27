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
                        <c:if test="${deliveryArea != null}">
                            <form action="deliveryAreas?action=update" method="post">
                        </c:if>
                        <c:if test="${deliveryArea == null}">
                            <form action="deliveryAreas?action=insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${deliveryArea != null}">
                                    Edit Delivery Area
                                </c:if>
                                <c:if test="${deliveryArea == null}">
                                    Add New Delivery Area
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${deliveryArea != null}">
                            <input type="hidden" name="id" value="<c:out value='${deliveryArea.deliveryAreaID}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Delivery Area Name</label> <input type="text" value="<c:out value='${deliveryArea.deliveryAreaName}' />" class="form-control" name="deliveryAreaName" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Delivery Area Zip</label> <input type="text" value="<c:out value='${deliveryArea.deliveryAreaZip}' />" class="form-control" name="deliveryAreaZip" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>