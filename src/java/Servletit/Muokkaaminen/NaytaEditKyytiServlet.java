package Servletit.Muokkaaminen;

import Servletit.Lisaaminen.NaytaAddAutoServlet;
import Mallit.Kayttaja;
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

public class NaytaEditKyytiServlet extends HttpServlet {

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

            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {
                id = 0;
            }
            Kyyti k = Kyyti.etsi(id);
            request.setAttribute("id", k.getId());
            request.setAttribute("ajovuoro", k.getAjovuoroId());
            request.setAttribute("hinta", k.getHinta());
            request.setAttribute("km", k.getKm());
            request.setAttribute("aika", k.getAika());
            tms.naytaJSP("editkyyti.jsp", request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(NaytaEditAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NaytaEditAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (NamingException ex) {
            Logger.getLogger(NaytaAddAutoServlet.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(NaytaAddAutoServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
