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

    public Auto(int id, String rekkari, String asemapaikka, String merkki, String malli) {
        this.id = id;
        this.rekkari = rekkari;
        this.asemapaikka = asemapaikka;
        this.merkki = merkki;
        this.malli = malli;
    }

    public Auto() {
    }

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

    public static Auto etsi(int idParam) throws NamingException, SQLException {
        String sql = "SELECT * FROM Auto WHERE Auto.id = ?";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();

        Auto a = new Auto();
        a.setId(tulokset.getInt("id"));
        a.setRekkari(tulokset.getString("rekkari"));
        a.setAsemapaikka(tulokset.getString("asemapaikka"));
        a.setMerkki(tulokset.getString("merkki"));
        a.setMalli(tulokset.getString("malli"));

        try {
            tulokset.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }
        return a;
    }

    public static int lukumaara() throws NamingException, SQLException {
        String sql = "SELECT Count(*) AS lkm FROM Auto";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        tulokset.next();
        int lkm = tulokset.getInt("lkm");

        try {
            tulokset.close();
        } catch (Exception e) {
        }
        try {
            kysely.close();
        } catch (Exception e) {
        }
        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return lkm;
    }

    public static List<Auto> getAutot() throws NamingException, SQLException {

        String sql = "SELECT * FROM Auto";
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

        try {
            tulokset.close();
        } catch (Exception e) {
        }

        try {
            kysely.close();
        } catch (Exception e) {
        }

        try {
            yhteys.close();
        } catch (Exception e) {
        }

        return autot;
    }
}
