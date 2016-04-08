package Mallit;

public class Kyyti {

    private int id;
    private int ajovuoroId;
    private double hinta;
    private double km;
    private double aika;

    public Kyyti(int id, int ajovuoroId, double hinta, double km, double aika) {
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

    public double getAika() {
        return aika;
    }

    public void setAika(double aika) {
        this.aika = aika;
    }

}
