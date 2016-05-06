<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pohja pageTitle="Etusivu">
    <h1>Käbi-Käbs Oy</h1>
    <p>Tervetuloa Käbi-Käbs Oy:n Taksitietokantaan.</p>
    <p>Voit selata navigaatiobaarin avulla autoja ja kuljettajia, ja niiden ajamia kyytejä.</p>
    <p>Tietoja voi myös syöttää kantaan tai poistaa sieltä, ja niitä voi muokata.</p>
    <h4>Tilastoja:</h4>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Autoja</th>
                <th>Kuljettajia</th>
                <th>Kyytejä</th>
            </tr>
        <tbody> 
            <tr>
                <th>${autot}</th>
                <th>${kuskit}</th>
                <th>${kyydit}</th>
            </tr>
            </thead>
    </table>
    <h4>Live long and prosper.</h4>
</t:pohja>