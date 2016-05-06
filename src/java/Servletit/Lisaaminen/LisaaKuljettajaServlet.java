package Servletit.Lisaaminen;

import Mallit.Kuljettaja;
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

public class LisaaKuljettajaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");
        Kuljettaja uusiKuski = new Kuljettaja();
        uusiKuski.setEtunimi(request.getParameter("etunimi"));
        uusiKuski.setSukunimi(request.getParameter("sukunimi"));
        if (uusiKuski.onkoKelvollinen()) {
            uusiKuski.lisaaKantaan();
            response.sendRedirect("KuljettajaServlet");
            HttpSession session = request.getSession();
            session.setAttribute("ilmoitus", "Kuljettaja lisätty onnistuneesti.");
        } else {
            Collection<String> virheet = uusiKuski.getVirheet();
            String etunimi = request.getParameter("etunimi");
            String sukunimi = request.getParameter("sukunimi");
            if (!etunimi.equals("")) {
                request.setAttribute("etunimi", etunimi);
            }
            if (!sukunimi.equals("")) {
                request.setAttribute("sukunimi", sukunimi);
            }
            request.setAttribute("virheet", virheet);
            request.setAttribute("kuljettaja", uusiKuski);
            tms.naytaJSP("NaytaAddKuljettajaServlet", request, response);
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
