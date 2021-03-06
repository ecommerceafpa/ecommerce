<jsp:include page="Header.jsp"/>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-2">
        </div>

        <div class="col-md-8">

            <div class="thumbnail">
                <img class="img-responsive" src="http://placehold.it/800x300" alt="">
                <div class="caption-full">
                    <h4 class="pull-right">Prix: <fmt:formatNumber type="currency" currencySymbol="&#8364;" value="${book.price}" /></h4><br>     
                    <h3>${book.title}</h3>
                    <h4>${book.subtitle}</h4> 

                    <h5 >Auteur(s):  
                        <c:forEach var="author" items="${authors}">
                            <a href="Author?id=${author.id}" >${author.firstName}   ${author.lastName} </a>
                        </c:forEach>
                    </h5>

                    <h5 >Langue: ${book.languageName} </h5>

                    <h5 >Editeur: <a href="Editor?id=${editor.id}" >${book.editorName} </h5> </a>

                    <h5 >Num�ro ISBN: ${book.isbn} </h5>

                    <fmt:setLocale value="fr"/>
                    <h5 >Date d'�dition: <fmt:formatDate  pattern="dd MMMM yyyy" value="${book.releaseDate}"  /> </h5>

                    <h5 >Nombre de pages: ${book.nbPage} </h5>

                    <p>${book.summary}</p>
                </div>

                <div class="ratings">
                    <p class="pull-right">3 reviews</p>
                    <p>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        4.0 stars
                    </p>
                </div>
            </div>

            <div class="well">

                <div class="text-right">
                    <a class="btn btn-success">Leave a Review</a>
                </div>

                <hr>

                <div class="row">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">10 days ago</span>
                        <p>This product was great in terms of quality. I would definitely buy another!</p>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">12 days ago</span>
                        <p>I've alredy ordered another one!</p>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-md-12">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                        Anonymous
                        <span class="pull-right">15 days ago</span>
                        <p>I've seen some better than this, but not at this price. I definitely recommend this item.</p>
                    </div>
                </div>

            </div>

        </div>

    </div>

</div>
<!-- /.container -->
<hr>

<jsp:include page="Footer.jsp"/>