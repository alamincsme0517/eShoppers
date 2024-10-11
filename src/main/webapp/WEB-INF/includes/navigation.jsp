<%--
  Created by IntelliJ IDEA.
  User: hp-alamincsme
  Date: 10/10/2024
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="<c:url value='/' />">e-Shoppers</a>
        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive"
                aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/"/> "/>Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/> "/>About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/> "/>Contact</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
