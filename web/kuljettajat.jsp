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
            </tr>
        <tbody>                  
            <c:forEach var="kuljettaja" items="${kuskit}">
                <tr>
                    <th><a href="Kuljettaja?id=${kuljettaja.id}">${kuljettaja.id} ei toimi viel</a></th>
                    <th><div class="kuljettaja">${kuljettaja.etunimi}</div></th>
                    <th><div class="kuljettaja">${kuljettaja.sukunimi}</div></th>
                </tr>
            </c:forEach>    
            </thead>
    </table>
</t:pohja>