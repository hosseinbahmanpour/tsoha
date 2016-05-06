package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

public class Kuljettaja {

    private int id;
    private String etunimi;
    private String sukunimi;
    private final Map<String, String> virheet = new HashMap<String, String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String uusiEtunimi) {
        if (uusiEtunimi == null || uusiEtunimi.trim().equals("")) {
            this.virheet.put("etunimi", "Kuljettajalla täytyy olla etunimi.");
            return;
        }
        this.etunimi = uusiEtunimi.trim();
        if (this.etunimi.length() == 0 || this.etunimi.length() > 22) {
            this.virheet.put("etunimi", "Kuljettajan etunimen tulee olla 1-22 kirjainta.");
        } else {
            this.virheet.remove("etunimi");
        }
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String uusiSukunimi) {
        if (uusiSukunimi == null || uusiSukunimi.trim().equals("")) {
            this.virheet.put("sukunimi", "Kuljettajalla täytyy olla sukunimi.");
            return;
        }
        this.sukunimi = uusiSukunimi.trim();
        if (this.sukunimi.length() == 0 || this.sukunimi.length() > 22) {
            this.virheet.put("sukunimi", "Kuljettajan sukunimen tulee olla 1-22 kirjainta.");
        } else {
            this.virheet.remove("sukunimi");
        }
    }

    public static List<Kuljettaja> getKuljettajat() throws NamingException, SQLException {

        String sql = "SELECT * FROM Kuljettaja;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        ArrayList<Kuljettaja> kuskit = new ArrayList<Kuljettaja>();

        while (tulokset.next()) {
            Kuljettaja k = new Kuljettaja();
            k.setId(tulokset.getInt("id"));
            k.setEtunimi(tulokset.getString("etunimi"));
            k.setSukunimi(tulokset.getString("sukunimi"));
            kuskit.add(k);
        }
        t.sulje(tulokset, kysely);
        return kuskit;
    }

    public void lisaaKantaan() throws NamingException, SQLException {
        String sql = "INSERT INTO Kuljettaja(etunimi, sukunimi) VALUES(?,?) RETURNING id;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, this.getEtunimi());
        kysely.setString(2, this.getSukunimi());
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        this.id = tulokset.getInt(1);
        t.sulje(tulokset, kysely);
    }

    public void poistaKannasta(int id) throws NamingException, SQLException {
        String sql = "DELETE FROM Kuljettaja WHERE id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, id);
        kysely.executeUpdate();
        t.sulje(kysely);
    }

    public static int lukumaara() throws NamingException, SQLException {
        String sql = "SELECT Count(*) AS lkm FROM Kuljettaja;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        int lkm = tulokset.getInt("lkm");
        t.sulje(tulokset, kysely);
        return lkm;
    }

    public boolean onkoKelvollinen() {
        return this.virheet.isEmpty();
    }

    public Collection<String> getVirheet() {
        return this.virheet.values();
    }
}
