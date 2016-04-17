<%-- 
    Document   : Auto
    Created on : 08-Apr-2016, 13:35:54
    Author     : xbax
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:pohja pageTitle="Autot">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Taksi #</th>
                <th>Rekisteri #</th>
                <th>Aemapaikka</th>
                <th>Merkki</th>
                <th>Malli</th>
            </tr>
        </thead>
    </table>
    <c:forEach var="auto" items="${autot}">
        <div class="auto">${auto.rekkari}</div>
        <a href="Auto?id=${auto.id}">${auto.rekkari}</a>
        <c:out value="${auto.rekkari}"/>
    </c:forEach>    
</t:pohja>