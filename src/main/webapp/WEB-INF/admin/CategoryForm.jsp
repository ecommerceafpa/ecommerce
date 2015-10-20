<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des categories</h1><!--TODO-->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Categorie <!--TODO--> 
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Category">List des categories</a><!--TODO-->
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-3"></div>
                            <div class="col-lg-6">
                                <c:if test="${alert != null}">
                                    <div class="alert ${alert.alertStyle.style} alert-dismissable">
                                        <c:if test="${alert.showButton}">
                                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                        </c:if>
                                        ${alert.message}
                                    </div>
                                </c:if>
                                <!--TODO-->
                                <form role="form" action="Category" method="POST"> 

                                    <div class="form-group ${errors['name'] != null ? 'has-error':''}">
                                        <label for="name">Nom *</label>
                                        <input class="form-control" placeholder="Nom" id="name" name="name" value="${category.name}">
                                        <p class="help-block">${errors['name']}</p>
                                    </div>

                                    <div class="form-group">
                                        <label for="parentCategory">Categorie parente</label>
                                        <select id="parentCategory" name="parent_category_id" class="form-control">
                                            <option value=""></option>
                                            <c:forEach var="c" items="${categories}">
                                                <option value="${c.id}" ${c.id == category.parentCategoryId ? "selected" : ""}>${c.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <input type="hidden" name="id" value="${category.id}">
                                    <button type="submit" class="btn btn-default pull-right">Envoyer</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../jspf/Footer.jsp"%>
