<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
