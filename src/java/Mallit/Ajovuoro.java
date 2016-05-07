package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class Ajovuoro {

    private int id;
    private int kuljettajaId;
    private int autoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKuljettajaId() {
        return kuljettajaId;
    }

    public void setKuljettajaId(int kuljettajaId) {
        this.kuljettajaId = kuljettajaId;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public static int etsi(int kuskiId, int autoId) throws NamingException, SQLException {
        String sql = "SELECT id FROM Ajovuoro WHERE kuljettaja_id=?, auto_id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, kuskiId);
        kysely.setInt(2, autoId);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        int id = Integer.parseInt(tulokset.getString("id"));
        t.sulje(tulokset, kysely);
        return id;
    }

    public void lisaaKantaan() throws NamingException, SQLException {
        String sql = "INSERT INTO Ajovuoro(kuljettaja_id, auto_id) VALUES(?,?) RETURNING id;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, this.getKuljettajaId());
        kysely.setInt(2, this.getAutoId());
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        this.id = tulokset.getInt(1);
        t.sulje(tulokset, kysely);
    }
}
