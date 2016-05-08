package Servletit.Lisaaminen;

import Mallit.Ajovuoro;
import Mallit.Kayttaja;
import Mallit.Kyyti;
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

public class LisaaKyytiServlet extends HttpServlet {

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
            int avK = Integer.parseInt(request.getParameter("kuljettaja"));
            int avA = Integer.parseInt(request.getParameter("auto"));
            Ajovuoro av = null;
            try {
                av = Ajovuoro.etsi(avK, avA);
            } catch (NamingException e) {
            } catch (SQLException e) {
            }
            if (av != null) {
                Kyyti uusiKyyti = new Kyyti();
                uusiKyyti.setAjovuoroId(av.getId());
                uusiKyyti.setHinta(request.getParameter("hinta"));
                uusiKyyti.setKm(request.getParameter("km"));
                uusiKyyti.setAika(request.getParameter("aika"));
                if (uusiKyyti.onkoKelvollinen()) {
                    uusiKyyti.lisaaKantaan();
                    response.sendRedirect("EtusivuServlet");
                    session.setAttribute("ilmoitus", "Kyyti lisätty onnistuneesti.");
                } else {
                    Collection<String> virheet = uusiKyyti.getVirheet();
                    String hinta = request.getParameter("hinta");
                    String km = request.getParameter("km");
                    String aika = request.getParameter("aika");
                    if (!hinta.equals("")) {
                        request.setAttribute("hinta", hinta);
                    }
                    if (!km.equals("")) {
                        request.setAttribute("km", km);
                    }
                    if (!aika.equals("")) {
                        request.setAttribute("aika", aika);
                    }
                    request.setAttribute("virheet", virheet);
                    request.setAttribute("kyyti", uusiKyyti);
                    tms.naytaJSP("NaytaAddKyytiServlet", request, response);
                }
            } else {
                Ajovuoro uusiAV = new Ajovuoro();
                uusiAV.setKuljettajaId(request.getParameter("kuljettaja"));
                uusiAV.setAutoId(request.getParameter("auto"));
                uusiAV.lisaaKantaan();
                Kyyti uusiKyyti = new Kyyti();
                uusiKyyti.setAjovuoroId(uusiAV.getId());
                uusiKyyti.setHinta(request.getParameter("hinta"));
                uusiKyyti.setKm(request.getParameter("km"));
                uusiKyyti.setAika(request.getParameter("aika"));
                if (uusiKyyti.onkoKelvollinen()) {
                    uusiKyyti.lisaaKantaan();
                    response.sendRedirect("EtusivuServlet");
                    session.setAttribute("ilmoitus", "Kyyti lisätty onnistuneesti.");
                } else {
                    Collection<String> virheet = uusiKyyti.getVirheet();
                    String hinta = request.getParameter("hinta");
                    String km = request.getParameter("km");
                    String aika = request.getParameter("aika");
                    if (!hinta.equals("")) {
                        request.setAttribute("hinta", hinta);
                    }
                    if (!km.equals("")) {
                        request.setAttribute("km", km);
                    }
                    if (!aika.equals("")) {
                        request.setAttribute("aika", aika);
                    }
                    request.setAttribute("virheet", virheet);
                    request.setAttribute("kyyti", uusiKyyti);
                    tms.naytaJSP("NaytaAddKyytiServlet", request, response);
                }
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
            Logger.getLogger(LisaaKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LisaaKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(LisaaKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LisaaKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
