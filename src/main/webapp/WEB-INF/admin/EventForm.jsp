<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../jspf/NavigationBar.jsp" %>
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Gestion des evenements</h1><!--TODO-->
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Evenement <!--TODO--> 
                        <a class="btn btn-outline btn-primary btn-xs pull-right" href="Event">List des evenements</a><!--TODO-->
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
                                <form role="form" action="Event" method="POST"> 

                                    <div class="form-group ${errors['name'] != null ? 'has-error':''}">
                                        <label for="name">Nom *</label>
                                        <input class="form-control" placeholder="Nom" id="name" name="name" value="${event.name}">
                                        <p class="help-block">${errors['name']}</p>
                                    </div>

                                    <div class="form-group ${errors['startDate'] != null ? 'has-error':''}">
                                        <label for="startDate">Date de début *</label>
                                        <input class="form-control" placeholder="dd/MM/yyyy" id="startDate" name="startDate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${event.startDate}"/>">
                                        <p class="help-block">${errors['startDate']}</p>
                                    </div>

                                    <div class="form-group ${errors['endDate'] != null ? 'has-error':''}">
                                        <label for="endDate">Date de fin *</label>
                                        <input class="form-control" placeholder="dd/MM/yyyy" id="endDate" name="endDate" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${event.endDate}"/>">
                                        <p class="help-block">${errors['endDate']}</p>
                                    </div>

                                    <div class="form-group ${errors['bookIds'] != null ? 'has-error':''}" >
                                        <label for="bookIds">Livre(s) *</label>
                                        <select id="bookIds" name="bookIds" class="form-control" multiple="multiple">
                                            <c:forEach var="b" items="${books}">
                                                <c:forEach var="bookEvent" items="${bookEvents}">
                                                    <c:if test="${b.id == bookEvent.bookId}">
                                                        <c:set var="isSelected" value="${true}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <option value="${b.id}" ${isSelected ? "selected" : "" }>${b.title}</option>
                                                <c:set var="isSelected" value="${false}"/>
                                            </c:forEach>
                                        </select>
                                        <p class="help-block">${errors['bookIds']}</p>
                                    </div>

                                    <input type="hidden" name="id" value="${event.id}">
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
