package Servletit;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToistuvatMetoditServleteille {

    public void asetaVirhe(String viesti, HttpServletRequest request) {
        request.setAttribute("virheViesti", viesti);
    }

    public void naytaJSP(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

}
