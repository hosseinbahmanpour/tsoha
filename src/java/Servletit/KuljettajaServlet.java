package Servletit;

import Mallit.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KuljettajaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");

        if (kirjautunut == null) {
            naytaJSP("kirjautuminen.jsp", request, response);
        } else {
            naytaJSP("kuljettajat.jsp", request, response);
        }

    }

    public void asetaVirhe(String viesti, HttpServletRequest request) {
        request.setAttribute("virheViesti", viesti);
    }

    public void naytaJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

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
    }
}
