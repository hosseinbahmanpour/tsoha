package Mallit;

import Tietokanta.Tietokanta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class Kayttaja {

    private int id;
    private String tunnus;
    private String salasana;

    public Kayttaja(int id, String tunnus, String salasana) {
        this.id = id;
        this.tunnus = tunnus;
        this.salasana = salasana;
    }
    
    public Kayttaja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTunnus() {
        return tunnus;
    }

    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    public static List<Kayttaja> getKayttajat() throws NamingException, SQLException {
       
        String sql = "SELECT id, tunnus, salasana FROM kayttaja";
        Tietokanta t = new Tietokanta();
        Connection yhteys = t.getYhteys();
        PreparedStatement kysely = yhteys.prepareStatement(sql);
        ResultSet tulokset = kysely.executeQuery();

        ArrayList<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
        while (tulokset.next()) {
            Kayttaja k = new Kayttaja();
            k.setId(tulokset.getInt("id"));
            k.setTunnus(tulokset.getString("tunnus"));
            k.setSalasana(tulokset.getString("salasana"));

            kayttajat.add(k);
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

        return kayttajat;
    }
}
