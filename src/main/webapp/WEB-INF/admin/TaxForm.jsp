<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des taxes</h1><!--TODO-->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Taxe <!--TODO--> 
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Tax">List des taxes</a><!--TODO-->
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
                                <form role="form" action="Tax" method="POST"> 

                                    <div class="form-group ${errors['name'] != null ? 'has-error':''}">
                                        <label for="name">Nom *</label>
                                        <input class="form-control" placeholder="Nom" id="name" name="name" value="${tax.name}">
                                        <p class="help-block">${errors['name']}</p>
                                    </div>
                                    
                                    <div class="form-group ${errors['value'] != null ? 'has-error':''}">
                                        <label for="value">Valeur *</label>
                                        <input class="form-control" placeholder="Valeur" id="value" name="value" value="${tax.value}">
                                        <p class="help-block">${errors['value']}</p>
                                    </div>

                                    <input type="hidden" name="id" value="${tax.id}">
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
