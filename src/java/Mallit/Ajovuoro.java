package Mallit;

public class Ajovuoro {
    
    private int id;
    private int kuljettajaId;
    private int autoId;
    private double km;
    private double aika;

    public Ajovuoro(int id, int kuljettajaId, int autoId, double km, double aika) {
        this.id = id;
        this.kuljettajaId = kuljettajaId;
        this.autoId = autoId;
        this.km = km;
        this.aika = aika;
    }

    public Ajovuoro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKuljettajaId() {
        return kuljettajaId;
    }

    public void setKuljettajaId(int kuljettajaId) {
        this.kuljettajaId = kuljettajaId;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
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
