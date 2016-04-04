package Taksidb.Tietokanta;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class YhteysTestaaja extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Connection yhteys = null;
        try {
            yhteys = Tietokanta.getYhteys(); //Haetaan tietokantaluokalta yhteysolio
        } catch (SQLException ex) {
            Logger.getLogger(YhteysTestaaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement kysely = null;
        ResultSet tulokset = null;
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain;charset=UTF-8");

        try {
            //Alustetaan muuttuja jossa on Select-kysely, joka palauttaa lukuarvon:
            String sqlkysely = "SELECT 1+1 as two";

            kysely = yhteys.prepareStatement(sqlkysely);
            tulokset = kysely.executeQuery();
            if (tulokset.next()) {
                //Tuloksen arvoksi pitäisi tulla numero kaksi.
                int tulos = tulokset.getInt("two");
                out.println("Tulos: " + tulos);
            } else {
                out.println("Virhe!");
            }
        } catch (Exception e) {
            out.println("Virhe: " + e.getMessage()
            );
        }

        tulokset.close();
        kysely.close();
    }
}
