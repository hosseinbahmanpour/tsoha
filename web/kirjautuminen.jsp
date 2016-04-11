<%-- 
    Document   : index
    Created on : 03-Apr-2016, 18:09:17
    Author     : xbax
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Taksitietokata</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-theme.css" rel="stylesheet">
        <link href="../css/main.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Tervetuloa tietokantaan, ole hyvä ja kirjaudu sisään.</h1>
            <form class="form-horizontal" role="form" action="KirjautuminenServlet" method="POST">
                <div class="form-group">
                    <label for="inputTunnus1" class="col-md-2 control-label">Tunnus</label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" id="inputTunnus1" name="tunnus" placeholder="Käyttäjätunnus">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword1" class="col-md-2 control-label">Salasana</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" id="inputPassword1" name="password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Muista kirjautuminen
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Kirjaudu sisään</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
