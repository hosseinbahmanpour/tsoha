<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Kuljettajat">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Kuski #</th>
                <th>Etunimi</th>
                <th>Sukunimi</th>
                <th>Muokkaa</th>
                <th>Poista</th>
            </tr>
        <tbody>                  
            <c:forEach var="kuljettaja" items="${kuskit}">
                <tr>
                    <th><a href="KuljettajanKyyditServlet?id=${kuljettaja.id}"><div class="kuljettaja">${kuljettaja.id}</div></a></th>
                    <th><div class="kuljettaja">${kuljettaja.etunimi}</div></th>
                    <th><div class="kuljettaja">${kuljettaja.sukunimi}</div></th>
                    <th><a href="NaytaEditKuljettajaServlet?id=${kuljettaja.id}"><input type="submit" value="Muokkaa" /></a></th>
                    <th><a href="PoistaKuljettajaServlet?id=${kuljettaja.id}"><input type="submit" value="Poista" /></a></th>
                </tr>
            </c:forEach>    
            </thead>
    </table>
</t:pohja>