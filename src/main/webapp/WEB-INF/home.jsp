
<%--home.jsp--%>
<%@taglib prefix="sec" uri="http://alamin.com/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/navigation.jsp"%>

<div class="container mb-5">
    <div class="row bg-light py-5 mb-4">
        <div class="col-6">
            <c:if test="${sec:isAuthenticated(pageContext.request)}">
                <h4>Hello <c:out value="${sec:getCurrentUser(pageContext.request).firstName}"/> </h4>
            </c:if>
            <h4>Welcome to e-Shoppers!</h4>
            <img class="card shadow-sm p-3 mb-5 bg-white" src="<c:url value="/images/cart.png" />" style="height: 200px" alt="" />
        </div>
        <div class="col-6 mb-5">
            <c:if
                test="${cart != null && cart.cartItems.size() > 0}">
                    <div class="card shadow-sm p-3 mb-5 bg-white">
                        <div class="card-header">
                            <h4>Your Cart</h4>
                        </div>

                        <div class="card-body">
                            <p>Total Item:
                                <span class="badge bg-success rounded-pill">
                                    <c:out value="${cart.totalItem}"/>
                                </span>
                            </p>
                            <p>Total Price: $ <c:out value="${cart.totalPrice}"/> </p>
                            <p>
                                <a class="btn btn-outline-info" href="/checkout">Checkout</a>
                            </p>
                        </div>
                    </div>
            </c:if>
        </div>

    </div>


    <div class="row mt-4">
        <c:if test="${message != null}">
            <div class="alert alert-success">
                    ${message}
            </div>
        </c:if>
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
                        <a href="#" class="card-link btn btn-outline-info"
                                onclick="addToCart(${product.id})">
                            Add to Cart
                        </a>

                        <form
                                style="visibility: hidden"
                                id="addToCart_${product.id}"
                                action="<c:url value="/add-to-cart?productId=${product.id}"/> "
                                method="POST">
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    function addToCart(productId) {
        let form = document.getElementById(
            "addToCart_" + productId
        )
        form.submit();
    }
</script>

<%@ include file="includes/footer.jsp"%>
