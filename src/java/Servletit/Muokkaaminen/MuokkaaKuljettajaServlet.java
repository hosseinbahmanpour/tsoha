package Servletit.Muokkaaminen;

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

public class MuokkaaKuljettajaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {
        
        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");
        Kuljettaja muokattuKuljettaja = new Kuljettaja();
        muokattuKuljettaja.setId(Integer.parseInt(request.getParameter("id")));
        muokattuKuljettaja.setEtunimi(request.getParameter("etunimi"));
        muokattuKuljettaja.setSukunimi(request.getParameter("sukunimi"));
        if (muokattuKuljettaja.onkoKelvollinen()) {
            muokattuKuljettaja.tallennaMuokkaukset();
            HttpSession session = request.getSession();
            session.setAttribute("ilmoitus", "Kuski muokattu onnistuneesti.");
            tms.naytaJSP("KuljettajaServlet", request, response);
        } else {
            Collection<String> virheet = muokattuKuljettaja.getVirheet();
            String etunimi = request.getParameter("etunimi");
            String sukunimi = request.getParameter("sukunimi");
            if (!etunimi.equals("")) {
                request.setAttribute("etunimi", etunimi);
            }
            if (!sukunimi.equals("")) {
                request.setAttribute("sukunimi", sukunimi);
            }
            request.setAttribute("virheet", virheet);
            request.setAttribute("id", muokattuKuljettaja.getId());
            request.setAttribute("etunimi", muokattuKuljettaja.getEtunimi());
            request.setAttribute("sukunimi", muokattuKuljettaja.getSukunimi());
            tms.naytaJSP("NaytaEditKuljettajaServlet", request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(MuokkaaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MuokkaaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(MuokkaaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MuokkaaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}