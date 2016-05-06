<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Muokkaa Auto">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/PoistaAutoServlet" method="POST">
        <div class="form-group">
            <label class="col-md-2 control-label">Auto</label>
            <div class="col-md-10">
                <select name="auto">
                    <c:forEach var="auto" items="${autot}">
                        <option value="${auto.rekkari}">${auto.rekkari}</option>
                    </c:forEach>                        
                </select>
                <input type="hidden" name="id" value="${auto.id}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-default">Poista Auto</button>
            </div>
        </div>
    </form>
</t:pohja>