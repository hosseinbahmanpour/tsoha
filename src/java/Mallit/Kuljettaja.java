package Mallit;

public class Kuljettaja {
    
    private int id;
    private String etunimi;
    private String sukunimi;

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
    
    
    
}
