<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Lisää Kyyti">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/AddKyytiServlet" method="POST">
        <div class="form-group">
            <label class="col-md-2 control-label">Auto</label>
            <div class="col-md-10">
                <select name="auto">
                    <c:forEach var="auto" items="${autot}">
                        <option value="${auto.id}">${auto.rekkari}</option>
                    </c:forEach>
                </select>
            </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">Kuljettaja</label>
                <div class="col-md-10">
                    <select name="kuljettaja">
                        <c:forEach var="kuljettaja" items="${kuskit}">
                            <option value="${kuljettaja.id}">${kuljettaja.sukunimi}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="inputHinta" class="col-md-2 control-label">Hinta</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputHinta" name="hinta" placeholder="Hinta euroissa esim: 22.50" value="${hinta}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputKm" class="col-md-2 control-label">Km</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputKm" name="km" placeholder="Matka kilometreinä esim: 13.6" value="${km}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputAika" class="col-md-2 control-label">Aika</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" id="inputAika" name="aika" placeholder="Aika minuutteina esim 13" value="${aika}">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <button type="submit" class="btn btn-default">Lisää Kyyti</button>
                </div>
            </div>
    </form>
</t:pohja>