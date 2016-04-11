<%-- 
    Document   : Ajovuoro
    Created on : 08-Apr-2016, 13:04:09
    Author     : xbax
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<c:if test="${virheViesti != null}">
  <div class="alert alert-danger">Virhe! ${virheViesti}</div>
</c:if>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajovuoro</title>
    </head>
    <body>
        <h1>Tässä näkyy lista kaikista ajovuoron aikana ajamista kyydeistä</h1>
    </body>
</html>
