package Taksidb.Tietokanta;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Tietokanta {

    static InitialContext cxt;
    static DataSource yhteysVarasto;
    static Connection yhteys;

    public Tietokanta() throws NamingException {

        cxt = new InitialContext();
        yhteysVarasto = (DataSource) cxt.lookup("java:/comp/env/jdbc/tietokanta");

    }

    public static Connection getYhteys() throws SQLException {
        yhteys = yhteysVarasto.getConnection();
        return yhteys;
    }

    public void sammutaYhteys() throws SQLException {
        yhteys.close();
    }

}