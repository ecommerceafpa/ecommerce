<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des auteurs</h1><!--TODO-->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Auteur<!--TODO-->
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Author">List des auteurs</a><!--TODO-->
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
                                <form role="form" action="Author" method="POST">
                                    <div class="form-group ${errors['firstNameError'] != null ? 'has-error':''}">
                                        <label for="firstName">Nom *</label>
                                        <input class="form-control" placeholder="Nom" id="firstName" name="firstName" value="${author.firstName}">
                                        <p class="help-block">${errors['firstNameError']}</p>
                                    </div>
                                    <div class="form-group ${errors['lastNameError'] != null ? 'has-error':''}">
                                        <label for="lastName">Prénom *</label>
                                        <input class="form-control"  placeholder="Prénom" id="lastName" name="lastName" value="${author.lastName}">
                                        <p class="help-block">${errors['lastNameError']}</p>
                                    </div>
                                    <div class="form-group ${errors['portraitError'] != null ? 'has-error':''}">
                                        <label for="portrait">Portrait *</label>
                                        <textarea class="form-control" rows="3" id="portrait" name="portrait" >${author.portrait}</textarea>
                                        <p class="help-block">${errors['portraitError']}</p>
                                    </div>
                                    <input type="hidden" name="id" value="${author.id}">
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
