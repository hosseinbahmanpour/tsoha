package Servletit.Lisaaminen;

import Mallit.Auto;
import Mallit.Kayttaja;
import Servletit.ToistuvatMetoditServleteille;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LisaaAutoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        tms.haeIlmoitus(session, request);
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut == null) {
            tms.asetaVirhe("Ole hyvä, ja kirjaudu sisään!", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);
        } else {
            Auto uusiAuto = new Auto();
            uusiAuto.setRekkari(request.getParameter("rekkari"));
            uusiAuto.setAsemapaikka(request.getParameter("asemapaikka"));
            uusiAuto.setMerkki(request.getParameter("merkki"));
            uusiAuto.setMalli(request.getParameter("malli"));
            if (uusiAuto.onkoKelvollinen()) {
                uusiAuto.lisaaKantaan();
                response.sendRedirect("AutoServlet");
                session.setAttribute("ilmoitus", "Auto lisätty onnistuneesti.");
            } else {
                Collection<String> virheet = uusiAuto.getVirheet();
                String rekkari = request.getParameter("rekkari");
                String asemapaikka = request.getParameter("asemapaikka");
                String merkki = request.getParameter("merkki");
                String malli = request.getParameter("malli");
                if (!rekkari.equals("")) {
                    request.setAttribute("rekkari", rekkari);
                }
                if (!asemapaikka.equals("")) {
                    request.setAttribute("asemapaikka", asemapaikka);
                }
                if (!merkki.equals("")) {
                    request.setAttribute("merkki", merkki);
                }
                if (!malli.equals("")) {
                    request.setAttribute("malli", malli);
                }
                request.setAttribute("virheet", virheet);
                request.setAttribute("auto", uusiAuto);
                tms.naytaJSP("NaytaAddAutoServlet", request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(NaytaAddKuljettajaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NaytaAddKuljettajaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(NaytaAddKuljettajaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NaytaAddKuljettajaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
