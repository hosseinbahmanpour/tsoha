package Servletit;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ToistuvatMetoditServleteille {

    public void asetaVirhe(String viesti, HttpServletRequest request) {
        request.setAttribute("virheViesti", viesti);
    }

    public void naytaJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

    public void haeIlmoitus(HttpSession session, HttpServletRequest request) {
        String ilmoitus = (String) session.getAttribute("ilmoitus");
        if (ilmoitus != null) {
            session.removeAttribute("ilmoitus");
            request.setAttribute("ilmoitus", ilmoitus);
        }
    }
}
