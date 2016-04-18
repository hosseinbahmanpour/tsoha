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
            </tr>
        <tbody>                  
            <c:forEach var="auto" items="${autot}">
                <tr>
                    <th><div class="auto">${auto.id}</div></th>
                    <th><div class="auto">${auto.rekkari}</div></th>
                    <th><div class="auto">${auto.asemapaikka}</div></th>
                    <th><div class="auto">${auto.merkki}</div></th>
                    <th><div class="auto">${auto.malli}</div></th>
                </tr>
            </c:forEach>    
            </thead>
    </table>
</t:pohja>