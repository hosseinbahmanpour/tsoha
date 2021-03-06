package Servletit;

import Mallit.Kayttaja;
import Mallit.Kyyti;
import Testit.YhteysTesti;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KuljettajanKyyditServlet extends HttpServlet {

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
            String idParam = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (Exception e) {
                id = 0;
            }

            List<Kyyti> k = Kyyti.etsiKuljettajanKyydit(id);

            if (k.isEmpty()) {
                tms.asetaVirhe("Kuskia ei ole olemassa, tai hän ei ole vielä tehnyt töitä!", request);
                tms.naytaJSP("KuljettajaServlet", request, response);
            } else {
                request.setAttribute("kyydit", k);
                tms.naytaJSP("kyydit.jsp", request, response);
            }
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
