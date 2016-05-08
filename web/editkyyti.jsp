<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Muokkaa Kyytiä">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/MuokkaaKyytiServlet" method="POST">
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="ajovuoro" value="${ajovuoro}">
        <div class="form-group">
            <label for="inputHinta" class="col-md-2 control-label">Hinta (€)</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputHinta" name="hinta" placeholder="${hinta}" value="${hinta}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputKm" class="col-md-2 control-label">Matka (km)</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputKm" name="km" placeholder="${km}" value="${km}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAika" class="col-md-2 control-label">Aika (min)</label>
            <div class="col-md-10">
                <input type="text" class="form-control" id="inputAika" name="aika" placeholder="${aika}" value="${aika}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-default">Muokkaa Kyyti</button>
            </div>
        </div>
    </form>
</t:pohja>