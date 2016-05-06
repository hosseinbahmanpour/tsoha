package Servletit.Lisaaminen;

import Mallit.Auto;
import Mallit.Kayttaja;
import Mallit.Kuljettaja;
import Mallit.Kyyti;
import Servletit.ToistuvatMetoditServleteille;
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

public class NaytaAddKyytiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut == null) {
            tms.asetaVirhe("Ole hyvä, ja kirjaudu sisään!", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);
        } else {
            tms.haeIlmoitus(session, request);
            request.setAttribute("autot", Auto.getAutot());
            request.setAttribute("kuskit", Kuljettaja.getKuljettajat());
            tms.naytaJSP("addkyyti.jsp", request, response);
            Kyyti uusiKyyti = new Kyyti();
//            uusiKyyti.setAjovuoroId(request.getParameter("auto"), request.getParameter("kuljettaja"));
            uusiKyyti.setHinta(1);
            uusiKyyti.setKm(2);
            uusiKyyti.setAika(0);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(NaytaAddKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NaytaAddKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(NaytaAddKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NaytaAddKyytiServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
