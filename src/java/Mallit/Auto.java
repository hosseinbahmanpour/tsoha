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
    private Map<String, String> virheet = new HashMap<String, String>();

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
        this.rekkari = uusiRekkari;
        if (uusiRekkari.contains("§½!#¤%&/()=?`´+}][{‚$£@€¨^~'*_.,;:<>| ")) {
            this.virheet.put("rekkari", "Rekisterinumeron täytyy koostua aakkosista ja numeroista. Esim: ABC-123.");
        } else {
            this.virheet.remove("rekkari");
        }
        if (uusiRekkari.length() > 7 || this.rekkari.length() < 4) {
            this.virheet.put("rekkari", "Rekisterinumeron täytyy olla 4-7 merkkiä. Esim: ABC-123 tai AB-1.");
        } else {
            this.virheet.remove("rekkari");
        }
    }

    public String getAsemapaikka() {
        return asemapaikka;
    }

    public void setAsemapaikka(String uusiAsemapaikka) {
        this.asemapaikka = uusiAsemapaikka;
        if (uusiAsemapaikka.contains("§½!#¤%&/()=?`´+}][{‚$£@€¨^~'*_.,;:<>| ")) {
            this.virheet.put("asemapaikka", "Auton asemapaikan täytyy koostua aakkosista.");
        } else {
            this.virheet.remove("asemapaikka");
        }
        if (uusiAsemapaikka.length() == 0) {
            this.virheet.put("asemapaikka", "Autolle täytyy syöttää asemapaikka. Esim: Helsinki.");
        } else {
            this.virheet.remove("asemapaikka");
        }
    }

    public String getMerkki() {
        return merkki;
    }

    public void setMerkki(String uusiMerkki) {
        this.merkki = uusiMerkki;
        if (uusiMerkki.trim().contains("§½!#¤%&/()=?`´+}][{‚$£@€¨^~'*_.,;:<>| ")) {
            this.virheet.put("merkki", "Auton merkin täytyy koostua aakkosista.");
        } else {
            this.virheet.remove("merkki");
        }
        if (uusiMerkki.length() == 0) {
            this.virheet.put("merkki", "Autolla täytyy olla merkki. Esim: Mercedes-Benz.");
        } else {
            this.virheet.remove("merkki");
        }
    }

    public String getMalli() {
        return malli;
    }

    public void setMalli(String uusiMalli) {
        this.malli = uusiMalli;
        if (uusiMalli.contains("§½!#¤%&/()=?`´+}][{‚$£@€¨^~'*_.,;:<>| ")) {
            this.virheet.put("malli", "Auton mallin täytyy koostua aakkosista.");
        } else {
            this.virheet.remove("malli");
        }
        if (uusiMalli.length() == 0) {
            this.virheet.put("malli", "Autolla täytyy olla malli. Esim: 523i.");
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

    public boolean onkoKelvollinen() {
        return this.virheet.isEmpty();
    }

    public Collection<String> getVirheet() {
        return this.virheet.values();
    }
}
