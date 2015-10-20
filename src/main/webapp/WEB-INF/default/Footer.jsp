<%@page import="java.util.Date"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<footer>
    <div class="row">
        <div class="col-lg-12">
            <p>Copyright &copy; E-commerce <fmt:formatDate pattern="yyyy"  value="<%= new Date()%>"/></p>
        </div>
    </div>
</footer>

</div>

<script src="<c:url value="/resources/bower_components/jquery/dist/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>

</body>

</html>
