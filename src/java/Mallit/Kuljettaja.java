package Mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Kuljettaja {

    private int id;
    private String etunimi;
    private String sukunimi;
    public static ToistuvatMetoditMalleille tmm = new ToistuvatMetoditMalleille();

    public Kuljettaja(int id, String etunimi, String sukunimi) {
        this.id = id;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
    }

    public Kuljettaja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public static int lukumaara() throws NamingException, SQLException {

        String sql = "SELECT Count(*) AS lkm FROM Kuljettaja;";
        Connection yhteys = tmm.yhdista();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        int lkm = tulokset.getInt("lkm");
        tmm.sulje(tulokset, kysely, yhteys);
        return lkm;
    }

    public static List<Kuljettaja> getKuljettajat() throws NamingException, SQLException {

        String sql = "SELECT * FROM Kuljettaja;";
        Connection yhteys = tmm.yhdista();
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
        tmm.sulje(tulokset, kysely, yhteys);
        return kuskit;
    }

}
