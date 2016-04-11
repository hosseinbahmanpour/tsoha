package Servletit;

import Mallit.Auto;
import Mallit.Kayttaja;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutotServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        session.removeAttribute("kirjautunut");

        Kayttaja kirjautunut = (Kayttaja) session.getAttribute("kirjautunut");
        if (kirjautunut == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("kirjautuminen.jsp");
            dispatcher.forward(request, response);
        }

        PrintWriter out = response.getWriter();

        request.setAttribute("virheViesti", "Sinulla ei ole ainuttakaan autoa!");
        request.setAttribute("pageTitle", "Autot");

        List<Auto> autot = Auto.getAutot();

        for (Auto a : autot) {
            out.println("<li>" + a.getId() + a.getRekkari() + a.getAsemapaikka() + a.getMalli() + a.getMerkki() + "</li>");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("autot.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AutotServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AutotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(AutotServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AutotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
