package Servletit.Poistaminen;

import Mallit.Auto;
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

public class PoistaAutoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        ToistuvatMetoditServleteille tms = new ToistuvatMetoditServleteille();
        response.setContentType("text/html;charset=UTF-8");

        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            id = 0;
        }

        Auto poistettava = new Auto();
        poistettava.poistaKannasta(id);
        HttpSession session = request.getSession();
        session.setAttribute("ilmoitus", "Auto poistettu onnistuneesti.");
        tms.naytaJSP("AutoServlet", request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(PoistaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PoistaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(PoistaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PoistaAutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
