package Servletit;

import Mallit.Kayttaja;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KirjautuminenServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");

        String salasana = request.getParameter("password");
        String tunnus = request.getParameter("tunnus");

        if (tunnus == null || salasana == null) {
            naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        if (tunnus == null || tunnus.equals("")) {
            asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
            naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        request.setAttribute("kayttaja", tunnus);

        if (salasana == null || salasana.equals("")) {
            asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
            naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        Kayttaja k = Kayttaja.etsiKayttajaTunnuksilla(tunnus, salasana);
        if (k == null) {
            asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
            naytaJSP("kirjautuminen.jsp", request, response);

        } else if (tunnus.equals(k.getTunnus()) && salasana.equals(k.getSalasana())) {
            response.sendRedirect("etusivu.jsp");
        }
    }

    public void naytaJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

    public void asetaVirhe(String viesti, HttpServletRequest request) {
        request.setAttribute("virheViesti", viesti);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(KirjautuminenServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(KirjautuminenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(KirjautuminenServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(KirjautuminenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
