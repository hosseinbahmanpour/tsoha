package Mallit;

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
    private int aika;
    public static ToistuvatMetoditMalleille tmm = new ToistuvatMetoditMalleille();
    
    public Kyyti(int id, int ajovuoroId, double hinta, double km, int aika) {
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
    
    public int getAika() {
        return aika;
    }
    
    public void setAika(int aika) {
        this.aika = aika;
    }
    
    public static List<Kyyti> etsiAutonKyydit(int idParam) throws NamingException, SQLException {
        
        String sql = "SELECT Kyyti.id, Kyyti.ajovuoro_id, Kyyti.hinta, Kyyti.km, Kyyti.aika FROM Kyyti, Ajovuoro WHERE Ajovuoro.auto_id = ? AND Kyyti.ajovuoro_id = Ajovuoro.id;";
        Connection yhteys = tmm.yhdista();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, idParam);
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
        tmm.sulje(tulokset, kysely, yhteys);
        return kyydit;
    }
    
    public static List<Kyyti> etsiKuljettajanKyydit(int idParam) throws NamingException, SQLException {
        
        String sql = "SELECT Kyyti.id, Kyyti.ajovuoro_id, Kyyti.hinta, Kyyti.km, Kyyti.aika FROM Kyyti, Ajovuoro WHERE Ajovuoro.kuljettaja_id = ? AND Kyyti.ajovuoro_id = Ajovuoro.id;";
        Connection yhteys = tmm.yhdista();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setInt(1, idParam);
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
        tmm.sulje(tulokset, kysely, yhteys);
        return kyydit;
    }
    
    public static int lukumaara() throws NamingException, SQLException {
        String sql = "SELECT Count(*) AS lkm FROM Kyyti;";
        Connection yhteys = tmm.yhdista();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();        
        tulokset.next();
        int lkm = tulokset.getInt("lkm");
        tmm.sulje(tulokset, kysely, yhteys);
        return lkm;
    }
    
}
