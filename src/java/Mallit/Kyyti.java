package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Kyyti {

    private int id;
    private int ajovuoroId;
    private double hinta;
    private double km;
    private double aika;

    public Kyyti(int id, int ajovuoroId, double hinta, double km, double aika) {
        this.id = id;
        this.ajovuoroId = ajovuoroId;
        this.hinta = hinta;
        this.km = km;
        this.aika = aika;
    }

    public Kyyti() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAjovuoroId() {
        return ajovuoroId;
    }

    public void setAjovuoroId(int ajovuoroId) {
        this.ajovuoroId = ajovuoroId;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getAika() {
        return aika;
    }

    public void setAika(double aika) {
        this.aika = aika;
    }

    public static int lukumaara() throws NamingException, SQLException {
        String sql = "SELECT Count(*) AS lkm FROM Kyyti";
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

    public static List<Kyyti> getKyydit() throws NamingException, SQLException {

        String sql = "SELECT * FROM Kyyti";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        ArrayList<Kyyti> kyydit = new ArrayList<Kyyti>();
        while (tulokset.next()) {
            Kyyti k = new Kyyti();
            k.setId(tulokset.getInt("id"));
            k.setAjovuoroId(tulokset.getInt("ajovuoroId"));
            k.setHinta(tulokset.getDouble("hinta"));
            k.setKm(tulokset.getDouble("km"));
            k.setAika(tulokset.getDouble("aika"));

            kyydit.add(k);
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

        return kyydit;
    }

}
