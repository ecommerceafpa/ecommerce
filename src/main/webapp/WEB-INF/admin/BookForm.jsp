<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des livres</h1><!--TODO-->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Livre<!--TODO-->
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Book">List des livres</a><!--TODO-->
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <c:if test="${alert != null}">
                                <div class="alert ${alert.alertStyle.style} alert-dismissable">
                                    <c:if test="${alert.showButton}">
                                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                    </c:if>
                                    ${alert.message}
                                </div>
                            </c:if>
                            <form role="form" action="Book" method="POST">
                                <div class="col-lg-6">
                                    <div class="form-group ${errors['isbn'] != null ? 'has-error':''}">
                                        <label for="isbn">ISBN *</label>
                                        <input class="form-control" placeholder="ISBN" id="isbn" name="isbn" value="${book.isbn}" maxlength="13">
                                        <p class="help-block">${errors['isbn']}</p>
                                    </div>

                                    <div class="form-group ${errors['title'] != null ? 'has-error':''}">
                                        <label for="title">Titre *</label>
                                        <input class="form-control"  placeholder="Titre" id="title" name="title" value="${book.title}">
                                        <p class="help-block">${errors['title']}</p>
                                    </div>

                                    <div class="form-group ${errors['subtitle'] != null ? 'has-error':''}">
                                        <label for="subtitle">Sous titre </label>
                                        <input class="form-control"  placeholder="Sous titre" id="subtitle" name="subtitle" value="${book.subtitle}">
                                        <p class="help-block">${errors['subtitle']}</p>
                                    </div>                                      

                                    <div class="form-group">
                                        <label for="editorId">Editeur *</label>
                                        <select id="editorId" name="editorId" class="form-control">
                                            <c:forEach var="e" items="${editors}">
                                                <option value="${e.id}" ${e.id == book.editorId ? "selected" : ""}>${e.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="languageId">Langue *</label>
                                        <select id="languageId" name="languageId" class="form-control">
                                            <c:forEach var="l" items="${languages}">
                                                <option value="${l.id}" ${l.id == book.languageId ? "selected" : ""}>${l.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group ${errors['authorIds'] != null ? 'has-error':''}" >
                                        <label for="authorIds">Auteur(s) *</label>
                                        <select id="authorIds" name="authorIds" class="form-control" multiple="multiple">
                                            <c:forEach var="a" items="${authors}">
                                                <c:forEach var="bookAuthor" items="${bookAuthors}">
                                                    <c:if test="${a.id == bookAuthor.authorId}">
                                                        <c:set var="isSelected" value="${true}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <option value="${a.id}" ${isSelected ? "selected" : "" }>${a.firstName} ${a.lastName}</option>
                                                <c:set var="isSelected" value="${false}"/>
                                            </c:forEach>
                                        </select>
                                        <p class="help-block">${errors['authorIds']}</p>
                                    </div>

                                </div>

                                <div class="col-lg-6">

                                    <div class="form-group ${errors['releaseDate'] != null ? 'has-error':''}">
                                        <label for="releaseDate">Date de parution *</label>
                                        <input class="form-control" placeholder="dd/MM/yyyy" id="releaseDate" name="releaseDate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${book.releaseDate}"/>">
                                        <p class="help-block">${errors['releaseDate']}</p>
                                    </div>

                                    <div class="form-group ${errors['nbPage'] != null ? 'has-error':''}">
                                        <label for="nbPage">Nombre de page *</label>
                                        <input class="form-control"  placeholder="Nombre de page " id="nbPage" name="nbPage" value="${book.nbPage}">
                                        <p class="help-block">${errors['nbPage']}</p>
                                    </div>  

                                    <div class="form-group ${errors['edition'] != null ? 'has-error':''}">
                                        <label for="edition">Edition *</label>
                                        <input class="form-control"  placeholder="Edition " id="edition" name="edition" value="${book.edition}">
                                        <p class="help-block">${errors['edition']}</p>
                                    </div>  

                                    <div class="form-group">
                                        <label for="taxId">Tax *</label>
                                        <select id="taxId" name="taxId" class="form-control">
                                            <c:forEach var="t" items="${taxes}">
                                                <option value="${t.id}" ${t.id == book.taxId ? "selected" : ""}>${t.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group ${errors['price'] != null ? 'has-error':''}">
                                        <label for="price">Prix *</label>
                                        <input class="form-control"  placeholder="Prix " id="price" name="price" value="${book.price}">
                                        <p class="help-block">${errors['price']}</p>
                                    </div>  

                                    <div class="form-group ${errors['categoryIds'] != null ? 'has-error':''}" >
                                        <label for="categoryIds">Categories(s) *</label>
                                        <select id="categoryIds" name="categoryIds" class="form-control" multiple="multiple">
                                            <c:forEach var="c" items="${categories}">
                                                <c:forEach var="bookCategory" items="${bookCategories}">
                                                    <c:if test="${c.id == bookCategory.categoryId}">
                                                        <c:set var="isSelected" value="${true}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <option value="${c.id}" ${isSelected ? "selected" : "" }>${c.name}</option>
                                                <c:set var="isSelected" value="${false}"/>
                                            </c:forEach>
                                        </select>
                                        <p class="help-block">${errors['categoryIds']}</p>
                                    </div>

                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group ${errors['summary'] != null ? 'has-error':''}">
                                        <label for="summary">Résumé </label>
                                        <textarea class="form-control" rows="5" id="summary" name="summary" >${book.summary}</textarea>
                                        <p class="help-block">${errors['summary']}</p>
                                    </div>
                                    <input type="hidden" name="id" value="${book.id}">
                                    <button type="submit" class="btn btn-default pull-right">Envoyer</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../jspf/Footer.jsp"%>
