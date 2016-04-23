<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Kyydit">
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Kyyti #</th>
                <th>Ajovuoro #</th>
                <th>Hinta (€)</th>
                <th>Matka (km)</th>
                <th>Aika (min)</th>
            </tr>
        <tbody>                  
            <c:forEach var="kyyti" items="${kyydit}">
                <tr>
                    <th><div class="kyyti">${kyyti.id}</div></th>
                    <th><div class="kyyti">${kyyti.ajovuoroId}</div></th>
                    <th><div class="kyyti">${kyyti.hinta}</div></th>
                    <th><div class="kyyti">${kyyti.km}</div></th>
                    <th><div class="kyyti">${kyyti.aika}</div></th>
                </tr>
            </c:forEach>    
            </thead>
    </table>
</t:pohja>