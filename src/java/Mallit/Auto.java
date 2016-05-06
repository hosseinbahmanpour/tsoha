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

public class Auto {

    private int id;
    private String rekkari;
    private String asemapaikka;
    private String merkki;
    private String malli;
    private final Map<String, String> virheet = new HashMap<String, String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRekkari() {
        return rekkari;
    }

    public void setRekkari(String uusiRekkari) {
        if (uusiRekkari == null || uusiRekkari.trim().equals("")) {
            this.virheet.put("rekkari", "Rekisterinumeron täytyy olla 4-7 merkkiä. Esim: ABC-123 tai AB-1.");
            return;
        }
        this.rekkari = uusiRekkari.trim().toUpperCase();
        if (this.rekkari.length() > 7 || this.rekkari.length() < 4) {
            this.virheet.put("rekkari", "Rekisterinumeron täytyy olla 4-7 merkkiä. Esim: ABC-123 tai AB-1.");
        } else {
            this.virheet.remove("rekkari");
        }
    }

    public String getAsemapaikka() {
        return asemapaikka;
    }

    public void setAsemapaikka(String uusiAsemapaikka) {
        if (uusiAsemapaikka == null || uusiAsemapaikka.trim().equals("")) {
            this.virheet.put("asemapaikka", "Autolle täytyy syöttää asemapaikka. Esim: Helsinki.");
            return;
        }
        this.asemapaikka = uusiAsemapaikka.trim();
        if (this.asemapaikka.length() == 0 || this.asemapaikka.length() > 22) {
            this.virheet.put("asemapaikka", "Syöttämäsi asemapaikan täytyy koostua 1-22 kirjaimesta.");
        } else {
            this.virheet.remove("asemapaikka");
        }
    }

    public String getMerkki() {
        return merkki;
    }

    public void setMerkki(String uusiMerkki) {
        if (uusiMerkki == null || uusiMerkki.trim().equals("")) {
            this.virheet.put("merkki", "Autolla täytyy olla merkki. Esim: Mercedes-Benz.");
            return;
        }
        this.merkki = uusiMerkki.trim();
        if (this.merkki.length() == 0 || this.merkki.length() > 22) {
            this.virheet.put("merkki", "Auton merkin täytyy koostua 1-22 kirjaimesta.");
        } else {
            this.virheet.remove("merkki");
        }
    }

    public String getMalli() {
        return malli;
    }

    public void setMalli(String uusiMalli) {
        if (uusiMalli == null || uusiMalli.trim().equals("")) {
            this.virheet.put("malli", "Autolla täytyy olla malli. Esim: 523i.");
            return;
        }
        this.malli = uusiMalli.trim();
        if (this.malli.length() == 0 || this.malli.length() > 22) {
            this.virheet.put("malli", "Auton mallin tulee koostua 1-22 kirjaimesta.");
        } else {
            this.virheet.remove("malli");
        }
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

    public void poistaKannasta(int id) throws NamingException, SQLException {
        String sql = "DELETE FROM Auto WHERE id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, id);
        kysely.executeUpdate();
        t.sulje(kysely);
    }

    public static int lukumaara() throws NamingException, SQLException {
        String sql = "SELECT Count(*) AS lkm FROM Auto;";
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
