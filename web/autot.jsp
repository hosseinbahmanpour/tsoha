<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Autot">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Taksi #</th>
                <th>Rekkari</th>
                <th>Asemapaikka</th>
                <th>Merkki</th>
                <th>Malli</th>
                <th>Muokkaa</th>
                <th>Poista</th>
            </tr>
        <tbody>                  
            <c:forEach var="auto" items="${autot}">
                <tr>
                    <th><a href="AutonKyyditServlet?id=${auto.id}"><div class="auto">${auto.id}</div></a></th>
                    <th><div class="auto">${auto.rekkari}</div></th>
                    <th><div class="auto">${auto.asemapaikka}</div></th>
                    <th><div class="auto">${auto.merkki}</div></th>
                    <th><div class="auto">${auto.malli}</div></th>
                    <th><a href="NaytaEditAutoServlet?id=${auto.id}"><input type="submit" value="Muokkaa" /></a></th>
                    <th><a href="PoistaAutoServlet?id=${auto.id}"><input type="submit" value="Poista" /></a></th>
                </tr>
            </c:forEach>    
            </thead>
    </table>
</t:pohja>