package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Kayttaja {

    private int id;
    private String tunnus;
    private String salasana;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTunnus() {
        return tunnus;
    }

    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public static Kayttaja etsiKayttajaTunnuksilla(String tunnus, String salasana) throws SQLException, NamingException {
        String sql = "SELECT id, tunnus, salasana FROM Kayttaja WHERE tunnus = ? AND salasana = ?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, tunnus);
        kysely.setString(2, salasana);
        ResultSet tulokset = kysely.executeQuery();
        Kayttaja kirjautunut = null;
        if (tulokset.next()) {
            kirjautunut = new Kayttaja();
            kirjautunut.setId(tulokset.getInt("id"));
            kirjautunut.setTunnus(tulokset.getString("tunnus"));
            kirjautunut.setSalasana(tulokset.getString("salasana"));
        }
        t.sulje(tulokset, kysely);
        return kirjautunut;
    }

    public static List<Kayttaja> getKayttajat() throws NamingException, SQLException {

        String sql = "SELECT id, tunnus, salasana FROM Kayttaja;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();

        while (tulokset.next()) {
            Kayttaja k = new Kayttaja();
            k.setId(tulokset.getInt("id"));
            k.setTunnus(tulokset.getString("tunnus"));
            k.setSalasana(tulokset.getString("salasana"));
            kayttajat.add(k);
        }
        t.sulje(tulokset, kysely);
        return kayttajat;
    }
}
