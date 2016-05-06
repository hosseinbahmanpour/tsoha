<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Logout">
    <h4>Oletko varma, että haluat kirjautua ulos?</h4>
    <form action="${pageContext.request.contextPath}/UloskirjautuminenServlet" method="POST"> 
        <input type="submit" value="Kyllä" />
    </form>
</t:pohja>