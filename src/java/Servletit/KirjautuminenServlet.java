package Servletit;

import Mallit.Kayttaja;
import Testit.YhteysTesti;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KirjautuminenServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {

        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");
        String salasana = request.getParameter("password");
        String tunnus = request.getParameter("tunnus");

        if (tunnus.equals("") && salasana.equals("")) {
            tms.asetaVirhe("Kirjautuminen epäonnistui! Syötä tunnus ja salasana.", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        if (tunnus.equals("")) {
            tms.asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        request.setAttribute("kayttaja", tunnus);

        if (salasana.equals("")) {
            tms.asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        Kayttaja k = Kayttaja.etsiKayttajaTunnuksilla(tunnus, salasana);

        if (k == null) {
            tms.asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);

        } else if (tunnus.equals(k.getTunnus()) && salasana.equals(k.getSalasana())) {
            HttpSession session = request.getSession();
            session.setAttribute("kirjautunut", k);
            tms.haeIlmoitus(session, request);
            response.sendRedirect("EtusivuServlet");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(YhteysTesti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(YhteysTesti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(YhteysTesti.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(YhteysTesti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>    
}
