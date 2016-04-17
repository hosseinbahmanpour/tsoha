<%-- 
    Document   : Auto
    Created on : 08-Apr-2016, 13:35:54
    Author     : xbax
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:pohja pageTitle="Autot">
    <c:forEach var="auto" items="${autot}">
        <div class="auto">${auto.merkki}</div>
    </c:forEach>

    <c:out value="${auto.rekkari}"/>

</t:pohja>

