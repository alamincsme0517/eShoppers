<%--
  Created by IntelliJ IDEA.
  User: hp-alamincsme
  Date: 10/12/2024
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%--login.jsp--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>

<div class="container col-sm-8 col-md-4">

    <div class="row">
        <c:if test="${message != null}">
            <div class="alert alert-success text-center">
                    ${message}
            </div>
        </c:if>
    </div>

    <h2 class="h2">Log In</h2>
    <hr class="mb-4">

    <form action="<c:url value="/login"/>" class="form mb-5" role="form"  method="POST">
        <div class="form-group mb-4">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username"  placeholder="Enter username">
            <c:if test="${errors.username != null}">
                <small class="text-danger">${errors.username}</small>
            </c:if>
        </div>

        <div class="form-group mb-4">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password"  placeholder="Enter password">
            <c:if test="${errors.password != null}">
                <small class="text-danger">${errors.password}</small>
            </c:if>
        </div>



        <hr class="mb-4">
        <div class="form-group" >
            <button class="btn btn-primary btn-lg" type="submit">Login</button>
        </div>
    </form>

    <span>
        Don't have a user  account?
        <a class="btn-link" href="<c:url value="/signup"/> "> SingUp</a>
    </span>
</div>

<%--footer--%>
<%@include file="includes/footer.jsp"%>