package Mallit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuljeYhteys {

    public SuljeYhteys(ResultSet tulokset, PreparedStatement kysely, Connection yhteys) {
        try { tulokset.close(); } catch (Exception e) { }
        try { kysely.close(); } catch (Exception e) { }
        try { yhteys.close(); } catch (Exception e) { }
    }

}