<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Lisää Auto">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/MuokkaaAutoServlet" method="POST">
        <input type="hidden" name="id" value="${id}">
        <div class="form-group">
            <label for="inputRekkari" class="col-md-2 control-label">Rekisterinumero</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputRekkari" name="rekkari" placeholder="${rekkari}" value="${rekkari}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAsemapaikka" class="col-md-2 control-label">Asemapaikka</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputAsemapaikka" name="asemapaikka" placeholder="${asemapaikka}" value="${asemapaikka}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputMerkki" class="col-md-2 control-label">Merkki</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputMerkki" name="merkki" placeholder="${merkki}" value="${merkki}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputMalli" class="col-md-2 control-label">Malli</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputMalli" name="malli" placeholder="${malli}" value="${malli}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-default">Muokkaa Auto</button>
            </div>
        </div>
    </form>
</t:pohja>