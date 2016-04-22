<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Autot">
<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/KirjautuminenServlet" method="POST">
                <div class="form-group">
                    <label for="inputTunnus1" class="col-md-2 control-label">Tunnus</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="inputTunnus1" name="tunnus" placeholder="Käyttäjätunnus" value="${kayttaja}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword1" class="col-md-2 control-label">Salasana</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" id="inputPassword1" name="password" placeholder="Salasana" value="${salasana}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Kirjaudu sisään</button>
                    </div>
                </div>
            </form>
</t:pohja>