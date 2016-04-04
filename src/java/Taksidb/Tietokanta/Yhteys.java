package Taksidb.Tietokanta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Yhteys {

    public Yhteys() {

        InitialContext cxt = null;
        try {
            cxt = new InitialContext();
        } catch (NamingException ex) {
            System.out.println("Error");
        }
        DataSource yhteysVarasto = null;
        try {
            yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");
        } catch (NamingException ex) {
            System.out.println("Error");
        }
        Connection yhteys = null;
        try {
            yhteys = yhteysVarasto.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Yhteys.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try { yhteys.close(); } catch (Exception e) {  }

    }
}
