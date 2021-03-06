package Tietokanta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public void sulje(ResultSet tulokset, PreparedStatement kysely) {
        try { tulokset.close(); } catch (Exception e) { }
        try { kysely.close(); } catch (Exception e) { }
        try { yhteys.close(); } catch (Exception e) { }
    }

    public void sulje(PreparedStatement kysely) {        
        try { kysely.close(); } catch (Exception e) { }
        try { yhteys.close(); } catch (Exception e) { }
    }
}