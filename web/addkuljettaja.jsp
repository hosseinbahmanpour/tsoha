<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Lisää Kuljettaja">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/LisaaKuljettajaServlet" method="POST">
        <div class="form-group">
            <label for="inputEtunimi" class="col-md-2 control-label">Etunimi</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputEtunimi" name="etunimi" placeholder="Vladimir" value="${etunimi}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputSukunimi" class="col-md-2 control-label">Sukunimi</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputSukunimi" name="sukunimi" placeholder="Putin" value="${sukunimi}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-default">Lisää Kuljettaja</button>
            </div>
        </div>
    </form>
</t:pohja>