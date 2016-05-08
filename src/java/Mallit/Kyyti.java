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

public class Kyyti {

    private int id;
    private int ajovuoroId;
    private double hinta;
    private double km;
    private int aika;
    private final Map<String, String> virheet = new HashMap<String, String>();

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

    public void setAjovuoroId(String ajovuoroId) {
        setAjovuoroId(Integer.parseInt(ajovuoroId));
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double uusiHinta) {
        this.hinta = uusiHinta;
        if (uusiHinta <= 0) {
            this.virheet.put("hinta", "Kyydillä tulee olla positiivinen hinta.");
        }
    }

    public void setHinta(String hinta) {
        try {
            setHinta(Double.parseDouble(hinta));
        } catch (NumberFormatException e) {
            this.virheet.put("hinta", "Kyydin hinnan pitää olla desimaaliluku.");
        }
    }

    public double getKm() {
        return km;
    }

    public void setKm(double uusiKm) {
        this.km = uusiKm;
        if (uusiKm <= 0) {
            this.virheet.put("km", "Kyydillä tulee olla positiivinen määrä kilometrejä.");
        }
    }

    public void setKm(String km) {
        try {
            setKm(Double.parseDouble(km));
        } catch (NumberFormatException e) {
            virheet.put("km", "Matkan pituuden tulee olla desimaaliluku");
        }
    }

    public int getAika() {
        return aika;
    }

    public void setAika(int uusiAika) {
        this.aika = uusiAika;
        if (uusiAika <= 0) {
            this.virheet.put("aika", "Kyydillä tulee viedä positiivinen määrä aikaa.");
        }
    }

    public void setAika(String aika) {
        try {
            setAika(Integer.parseInt(aika));
        } catch (NumberFormatException e) {
            virheet.put("aika", "Kyydin ajan pituuden tulee olla kokonaisluku.");
        }
    }

    public static Kyyti etsi(int id) throws NamingException, SQLException {
        String sql = "SELECT ajovuoro_id, hinta, km, aika FROM Kyyti WHERE id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, id);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        Kyyti k = new Kyyti();
        k.setId(id);
        k.setAjovuoroId(tulokset.getInt("ajovuoro_id"));
        k.setHinta(tulokset.getInt("hinta"));
        k.setKm(tulokset.getInt("km"));
        k.setAika(tulokset.getInt("aika"));
        t.sulje(tulokset, kysely);
        return k;
    }

    public static List<Kyyti> etsiAutonKyydit(int id) throws NamingException, SQLException {

        String sql = "SELECT Kyyti.id, Kyyti.ajovuoro_id, Kyyti.hinta, Kyyti.km, Kyyti.aika FROM Kyyti, Ajovuoro WHERE Ajovuoro.auto_id = ? AND Kyyti.ajovuoro_id = Ajovuoro.id;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, id);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();

        ArrayList<Kyyti> kyydit = new ArrayList<Kyyti>();

        while (tulokset.next()) {
            Kyyti k = new Kyyti();
            k.setId(tulokset.getInt("id"));
            k.setAjovuoroId(tulokset.getInt("ajovuoro_id"));
            k.setHinta(tulokset.getDouble("hinta"));
            k.setKm(tulokset.getDouble("km"));
            k.setAika(tulokset.getInt("aika"));
            kyydit.add(k);
        }
        t.sulje(tulokset, kysely);
        return kyydit;
    }

    public static List<Kyyti> etsiKuljettajanKyydit(int id) throws NamingException, SQLException {

        String sql = "SELECT Kyyti.id, Kyyti.ajovuoro_id, Kyyti.hinta, Kyyti.km, Kyyti.aika FROM Kyyti, Ajovuoro WHERE Ajovuoro.kuljettaja_id = ? AND Kyyti.ajovuoro_id = Ajovuoro.id;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, id);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();

        ArrayList<Kyyti> kyydit = new ArrayList<Kyyti>();

        while (tulokset.next()) {
            Kyyti k = new Kyyti();
            k.setId(tulokset.getInt("id"));
            k.setAjovuoroId(tulokset.getInt("ajovuoro_id"));
            k.setHinta(tulokset.getDouble("hinta"));
            k.setKm(tulokset.getDouble("km"));
            k.setAika(tulokset.getInt("aika"));
            kyydit.add(k);
        }
        t.sulje(tulokset, kysely);
        return kyydit;
    }

    public void lisaaKantaan() throws NamingException, SQLException {
        String sql = "INSERT INTO Kyyti(ajovuoro_id, hinta, km, aika) VALUES(?,?,?,?) RETURNING id;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, this.getAjovuoroId());
        kysely.setDouble(2, this.getHinta());
        kysely.setDouble(3, this.getKm());
        kysely.setInt(4, this.getAika());
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        this.id = tulokset.getInt(1);
        t.sulje(tulokset, kysely);
    }

    public void tallennaMuokkaukset() throws NamingException, SQLException {
        String sql = "UPDATE Kyyti SET hinta=?, km=?, aika=? WHERE id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(4, this.getId());
        kysely.setDouble(1, this.getHinta());
        kysely.setDouble(2, this.getKm());
        kysely.setInt(3, this.getAika());
        kysely.executeUpdate();
        t.sulje(kysely);
    }

    public void poistaKannasta(int id) throws NamingException, SQLException {
        String sql = "DELETE FROM Kyyti WHERE id=?;";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, id);
        kysely.executeUpdate();
        t.sulje(kysely);
    }

    public static int lukumaara() throws NamingException, SQLException {
        String sql = "SELECT Count(*) AS lkm FROM Kyyti;";
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
