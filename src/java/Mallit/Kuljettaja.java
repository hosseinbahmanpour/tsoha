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

    public void lisaaKantaan() throws NamingException, SQLException {
        String sql = "INSERT INTO Kuljettaja(etunimi, sukunimi) VALUES(?,?) RETURNING id;";
        Connection yhteys = tmm.yhdista();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        kysely.setString(1, this.getEtunimi());
        kysely.setString(2, this.getSukunimi());
        ResultSet tulokset = kysely.executeQuery();
        tulokset.next();
        this.id = tulokset.getInt(1);
        tmm.sulje(tulokset, kysely, yhteys);
    }
}
