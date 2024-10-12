
<%--home.jsp--%>
<%@taglib prefix="sec" uri="http://alamin.com/functions" %>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/navigation.jsp"%>

<div class="container mb-5">
    <div class="bg-light py-5 mb-4">
        <c:if test="${sec:isAuthenticated(pageContext.request)}">
            <h1>Hello <c:out value="${sec:getCurrentUser(pageContext.request).firstName}"/> </h1>
        </c:if>
        <h1>Welcome to e-Shoppers!</h1>
        <img src="<c:url value="/images/cart.png" />" style="height: 200px" alt="" />
    </div>

    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-sm-4">
                <div class="card h-100 mb-4">
                    <div class="card-body">
                        <h5 class="card-title">
                            <c:out value="${product.name}"/>
                        </h5>
                        <p class="card-text">
                            <c:out value="${product.description}"/>
                        </p>
                        <p class="card-text">
                            Price: $
                            <c:out value="${product.price}"/>
                        </p>
                        <a href="#" class="card-link btn btn-outline-info">
                            Add to Cart
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@ include file="includes/footer.jsp"%>
