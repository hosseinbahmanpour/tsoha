package Mallit;

public class Ajovuoro {

    private int id;
    private int kuljettajaId;
    private int autoId;
    public static ToistuvatMetoditMalleille tmm = new ToistuvatMetoditMalleille();

    public Ajovuoro(int id, int kuljettajaId, int autoId) {
        this.id = id;
        this.kuljettajaId = kuljettajaId;
        this.autoId = autoId;
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

}
