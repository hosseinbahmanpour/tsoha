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

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public int getKuljettajaId() {
        return kuljettajaId;
    }

    public void setKuljettajaId(int kuljettajaId) {
        this.kuljettajaId = kuljettajaId;
    }

    public void setKuljettajaId(String kuljettajaId) {
        this.kuljettajaId = Integer.parseInt(kuljettajaId);
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = Integer.parseInt(autoId);
    }

    public static Ajovuoro etsi(int kuljettajaId, int autoId) throws NamingException, SQLException {
        String sql = "SELECT id FROM Ajovuoro WHERE kuljettaja_id=? AND auto_id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, kuljettajaId);
        kysely.setInt(2, autoId);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        Ajovuoro a = new Ajovuoro();
        a.setId(tulokset.getInt("id"));
        a.setKuljettajaId(kuljettajaId);
        a.setAutoId(tulokset.getString(autoId));
        t.sulje(tulokset, kysely);
        return a;
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
