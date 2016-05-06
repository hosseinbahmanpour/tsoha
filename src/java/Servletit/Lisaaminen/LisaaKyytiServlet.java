package Servletit.Lisaaminen;

import Mallit.Kayttaja;
import Servletit.ToistuvatMetoditServleteille;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LisaaKyytiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");
        if (kirjautunut == null) {
            tms.asetaVirhe("Ole hyvä, ja kirjaudu sisään!", request);
            tms.naytaJSP("kirjautuminen.jsp", request, response);
        } else {
            tms.asetaVirhe("Ominaisuutta ei ole vielä toteutettu!", request);
            tms.haeIlmoitus(session, request);
            tms.naytaJSP("NaytaAddKyytiServlet", request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
