<%--
  Created by IntelliJ IDEA.
  User: hp-alamincsme
  Date: 10/12/2024
  Time: 10:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%--signup.jsp--%>
<%@include file="includes/header.jsp"%>
<%@include file="includes/navigation.jsp"%>

<div class="container col-sm-8 col-md-4 mb-5">
    <br/>
    <h2 class="h2 mb-4">Sign Up</h2>
    <hr class="mb-4">
    <form action="<c:url value="/signup"/>" class="form mb-5" role="form"  method="POST">
        <div class="form-group mb-4">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="${userDto.username}" placeholder="Enter username">
            <c:if test="${errors.username != null}">
                <small class="text-danger">${errors.username}</small>
            </c:if>
        </div>

        <div class="form-group mb-4">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${userDto.email}" placeholder="example@gmail.com">
            <c:if test="${errors.email != null}">
                <small class="text-danger">${errors.email}</small>
            </c:if>
        </div>

        <div class="form-group mb-4">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" value="${userDto.password}" placeholder="Enter password">
            <c:if test="${errors.password != null}">
                <small class="text-danger">${errors.password}</small>
            </c:if>
        </div>

        <div class="form-group mb-4">
            <label for="passwordConfirmed">Password Confirmed</label>
            <input type="password" class="form-control" id="passwordConfirmed" name="passwordConfirmed" value="${userDto.passwordConfirmed}" placeholder="Enter confirmed password">
            <c:if test="${errors.passwordConfirmed != null}">
                <small class="text-danger">${errors.passwordConfirmed}</small>
            </c:if>
        </div>

        <div class="form-group mb-4">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${userDto.firstName}" placeholder="Enter your first name">
            <c:if test="${errors.firstName != null}">
                <small class="text-danger">${errors.firstName}</small>
            </c:if>
        </div>

        <div class="form-group mb-4">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${userDto.lastName}" placeholder="Enter your last name">
            <c:if test="${errors.username != null}">
                <small class="text-danger">${errors.lastName}</small>
            </c:if>
        </div>

        <hr class="mb-4">
        <div class="form-group" >
            <button class="btn btn-primary btn-lg" onclick="return validPassword()" type="submit">Signup</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    function validPassword() {
        var password = document.getElementById("password").value;
        var passwordConfirmed = document.getElementById("passwordConfirmed").value;

        if (password !== passwordConfirmed) {
            alert("Password do not match!");
            return false ;
        }
        return  true ;
    }
</script>

<%@include file="includes/footer.jsp"%>


