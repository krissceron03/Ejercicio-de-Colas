public class proceso {
    private String id;
    private int cedula;
    private int tiempo;

    public proceso(String id, int cedula, int tiempo) {
        this.id = id;
        this.cedula= cedula;
        this.tiempo= tiempo;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    @Override
    public String toString(){
        return "Múmero de proceso: "+id+"\nCédula: "+cedula+"\nTiempo: "+tiempo;
    }
}
