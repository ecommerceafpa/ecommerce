<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des editeurs</h1><!--TODO-->
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Editeur <!--TODO-->
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Editor?action=add">Nouveau</a>
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
                        <!--TODO-->
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTable">
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Crée le</th>
                                        <th>Modifié le</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="editor" items="${editors}">
                                        <tr class="odd gradeX">
                                            <td>${editor.name}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${editor.created}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${editor.updated}" /></td>
                                            <td>
                                                <a href="Editor?action=edit&id=${editor.id}" class="btn btn-default btn-circle"><i class="fa fa-edit"></i></a>
                                                <a href="Editor?action=delete&id=${editor.id}" class="btn btn-default btn-circle" onclick="return confirm('êtes-vous sûr de vouloir supprimer ${editor.name} ?');"><i class="fa fa-trash"></i></a>
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
