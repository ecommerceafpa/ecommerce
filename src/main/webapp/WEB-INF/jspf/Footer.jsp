<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="<c:url value="/resources/bower_components/jquery/dist/jquery.min.js"/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"/>"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value="/resources/bower_components/metisMenu/dist/metisMenu.min.js"/>"></script>

<!-- DataTables JavaScript -->
<script src="<c:url value="/resources/bower_components/datatables/media/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"/>"></script>

<!-- Custom Theme JavaScript -->
<script src="<c:url value="/resources/dist/js/sb-admin-2.js"/>"></script>

<script>
    $(document).ready(function () {
        $('#dataTable').DataTable({
            responsive: true
        });
    });
</script>


</body>

</html>


