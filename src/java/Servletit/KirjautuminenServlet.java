package Servletit;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KirjautuminenServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String salasana = request.getParameter("password");
        String kayttaja = request.getParameter("tunnus");

        if (kayttaja == null || salasana == null) {
            naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        if (kayttaja == null || kayttaja.equals("")) {
            asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
            naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        request.setAttribute("kayttaja", kayttaja);

        if (salasana == null || salasana.equals("")) {
            asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
            naytaJSP("kirjautuminen.jsp", request, response);
            return;
        }

        if (kayttaja.equals("hossein") && salasana.equals("hba")) {
            response.sendRedirect("etusivu.jsp");
        } else {
            asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
            naytaJSP("kirjautuminen.jsp", request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void naytaJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

    public void asetaVirhe(String viesti, HttpServletRequest request) {        
        request.setAttribute(viesti, this);
    }

}
