<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des livres</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Livre
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Book?action=add">Nouveau</a>
                    </div>

                    <div class="panel-body">
                        <c:if test="${alert != null}">
                            <div class="alert ${alert.alertStyle.style} alert-dismissable">
                                <c:if test="${alert.showButton}">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                </c:if>
                                ${alert.message}
                            </div>
                        </c:if>
                        
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTable">
                                <thead>
                                    <tr>
                                        <th>ISBN</th>
                                        <th>Titre</th>
                                        <th>Sous titre</th>
                                        <th>Edition</th>
                                        <th>Prix</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="book" items="${books}">
                                        <tr class="odd gradeX">
                                            <td>${book.isbn}</td>
                                            <td>${book.title}</td>
                                            <td>${book.subtitle}</td>
                                            <td>${book.edition}</td>
                                            <td>${book.price}</td>
                                            <td>
                                                <a href="Book?action=edit&id=${book.id}" class="btn btn-default btn-circle"><i class="fa fa-edit"></i></a>
                                                <a href="Book?action=delete&id=${book.id}" class="btn btn-default btn-circle" onclick="return confirm('êtes-vous sûr de vouloir supprimer ${book.title} ?');"><i class="fa fa-trash"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../jspf/Footer.jsp"%>
