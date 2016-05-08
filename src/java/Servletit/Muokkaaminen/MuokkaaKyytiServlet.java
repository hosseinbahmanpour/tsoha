package Servletit.Muokkaaminen;

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

public class MuokkaaKyytiServlet extends HttpServlet {

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
            Kyyti muokattuKyyti = new Kyyti();
            muokattuKyyti.setId(Integer.parseInt(request.getParameter("id")));
            muokattuKyyti.setAjovuoroId(request.getParameter("ajovuoro"));
            muokattuKyyti.setHinta(request.getParameter("hinta"));
            muokattuKyyti.setKm(request.getParameter("km"));
            muokattuKyyti.setAika(request.getParameter("aika"));
            if (muokattuKyyti.onkoKelvollinen()) {
                muokattuKyyti.tallennaMuokkaukset();
                session.setAttribute("ilmoitus", "Kyyti muokattu onnistuneesti.");
                tms.naytaJSP("EtusivuServlet", request, response);
            } else {
                Collection<String> virheet = muokattuKyyti.getVirheet();
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
                request.setAttribute("id", muokattuKyyti.getId());
                request.setAttribute("ajovuoro", muokattuKyyti.getAjovuoroId());
                request.setAttribute("hinta", muokattuKyyti.getHinta());
                request.setAttribute("km", muokattuKyyti.getKm());
                request.setAttribute("aika", muokattuKyyti.getAika());
                tms.naytaJSP("NaytaEditKyytiServlet", request, response);
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
