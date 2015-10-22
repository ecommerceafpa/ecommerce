<jsp:include page="HeaderRegistration.jsp"/>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Inscription</h3>
                </div>
                <div class="panel-body">
                    <form role="form" method="POST" action="Registration">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="Nom" name="lastname"  autofocus>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Prénom" name="firstname" >
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="E-mail" name="username" type="email" >
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Mot de passe" name="password" type="password" value="">
                            </div>
                            
                            <!-- Change this to a button or input when using this as a form -->                            
                            <input type="submit" class="btn btn-lg btn-success btn-block" value="Valider">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

<!-- /.container -->
<hr>

<jsp:include page="Footer.jsp"/>
