package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Auto {

    private int id;
    private String rekkari;
    private String asemapaikka;
    private String merkki;
    private String malli;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRekkari() {
        return rekkari;
    }

    public void setRekkari(String rekkari) {
        this.rekkari = rekkari;
    }

    public String getAsemapaikka() {
        return asemapaikka;
    }

    public void setAsemapaikka(String asemapaikka) {
        this.asemapaikka = asemapaikka;
    }

    public String getMerkki() {
        return merkki;
    }

    public void setMerkki(String merkki) {
        this.merkki = merkki;
    }

    public String getMalli() {
        return malli;
    }

    public void setMalli(String malli) {
        this.malli = malli;
    }

    public static List<Auto> getAutot() throws NamingException, SQLException {

        String sql = "SELECT * FROM Auto;";  
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();
        ArrayList<Auto> autot = new ArrayList<Auto>();

        while (tulokset.next()) {
            Auto a = new Auto();
            a.setId(tulokset.getInt("id"));
            a.setRekkari(tulokset.getString("rekkari"));
            a.setAsemapaikka(tulokset.getString("asemapaikka"));
            a.setMerkki(tulokset.getString("merkki"));
            a.setMalli(tulokset.getString("malli"));
            autot.add(a);
        }
        t.sulje(tulokset, kysely);
        return autot;
    }

    public void lisaaKantaan() throws NamingException, SQLException {
        String sql = "INSERT INTO Auto(rekkari, asemapaikka, merkki, malli) VALUES(?,?,?,?) RETURNING id;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, this.getRekkari());
        kysely.setString(2, this.getAsemapaikka());
        kysely.setString(3, this.getMerkki());
        kysely.setString(4, this.getMalli());
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        this.id = tulokset.getInt(1);
        t.sulje(tulokset, kysely);
    }
}
