package Mallit;

public class Auto {

    private int id;
    private String rekkari;
    private String asemapaikka;
    private String merkki;
    private String malli;

    public Auto(int id, String rekkari, String asemapaikka, String merkki, String malli) {
        this.id = id;
        this.rekkari = rekkari;
        this.asemapaikka = asemapaikka;
        this.merkki = merkki;
        this.malli = malli;
    }

    public Auto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRekkari() {
        return rekkari;
    }

    public void setRekkari(String rekkari) {
        this.rekkari = rekkari;
    }

    public String getAsemapaikka() {
        return asemapaikka;
    }

    public void setAsemapaikka(String asemapaikka) {
        this.asemapaikka = asemapaikka;
    }

    public String getMerkki() {
        return merkki;
    }

    public void setMerkki(String merkki) {
        this.merkki = merkki;
    }

    public String getMalli() {
        return malli;
    }

    public void setMalli(String malli) {
        this.malli = malli;
    }

}
