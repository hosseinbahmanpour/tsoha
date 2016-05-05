<%@tag description="Generic template for Taksidb pages" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${pageTitle}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="navbar navbar-default">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/EtusivuServlet">Etusivu</a></li> 
                    <li><a href="${pageContext.request.contextPath}/AutoServlet">Autot</a></li>
                    <li><a href="${pageContext.request.contextPath}/KuljettajaServlet">Kuljettajat</a></li>
                    <li><a href="${pageContext.request.contextPath}/AddAutoServlet">Lisää Auto</a></li>
                    <li><a href="${pageContext.request.contextPath}/AddKuljettajaServlet">Lisää Kuljettaja</a></li>
                    <li><a href="${pageContext.request.contextPath}/AddKyytiServlet">Lisää Kyyti</a></li>
                    <li><a href="${pageContext.request.contextPath}/UloskirjautuminenServlet">Kirjaudu Ulos</a></li>
                </ul>
            </div>
        </div>            
        <div class="container">
            <c:if test="${pageError != null}">
                <div class="alert alert-danger">${pageError}</div>
            </c:if>
            <c:if test="${virheViesti != null}">
                <div class="alert alert-danger">Virhe! ${virheViesti}</div>
            </c:if>
            <c:if test="${ilmoitus != null}">
                <div class="alert alert-info">${ilmoitus}</div>
            </c:if>
            <jsp:doBody/>
        </div>
    </body>
</html>