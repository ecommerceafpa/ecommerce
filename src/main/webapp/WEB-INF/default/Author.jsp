<jsp:include page="Header.jsp"/>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Page Content -->
<div class="container">

    <header class="jumbotron hero-spacer">
        <h1>${author.firstName} ${author.lastName}</h1>
        <p>${author.portrait}</p>
    </header>

    <div class="row text-center">
        <c:forEach var="book" items="${books}">
            <div class="col-md-3 col-sm-6 hero-feature">
                <div class="thumbnail">
                    <img src="http://placehold.it/800x500" alt="">
                    <div class="caption">
                        <h3>${book.title}</h3>
                        <p>${book.subtitle}</p>
                        <p>
                            <a href="#" class="btn btn-primary">Buy Now!</a> 
                            <a href="Book?id=${book.id}" class="btn btn-default">Plus d'infos</a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <hr>

    <jsp:include page="Footer.jsp"/>
