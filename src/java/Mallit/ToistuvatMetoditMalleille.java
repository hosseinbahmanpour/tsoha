package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class ToistuvatMetoditMalleille {
    
    public Connection yhdista() throws NamingException, SQLException {
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        return yhteys;
    }

    public void sulje(ResultSet tulokset, PreparedStatement kysely, Connection yhteys) {
        try { tulokset.close(); } catch (Exception e) { }
        try { kysely.close(); } catch (Exception e) { }
        try { yhteys.close(); } catch (Exception e) { }
    }
}