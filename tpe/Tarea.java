package tpe;

public class Tarea implements Comparable<Tarea> {

    private String id;
    private String nombre;
    private int tiempo;
    private boolean critica;
    private int prioridad;

    public Tarea(String id, String nombre, int tiempo, boolean critica, int prioridad) {
        this.setID(id);
        this.setNombre(nombre);
        this.setTiempo(tiempo);
        this.setCritica(critica);
        this.setPrioridad(prioridad);
    }

    public String getID() {
        return this.id;
    }

    private void setID(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return this.tiempo;
    }

    private void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean getCritica() {
        return this.critica;
    }

    private void setCritica(boolean critica) {
        this.critica = critica;
    }

    public int getPrioridad() {
        return this.prioridad;
    }

    private void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
    public int getTiempoEjecucion() {
        return this.tiempo;
    }
    

    @Override
    public String toString() {
        return "ID: " + this.getID() +
                " - Nombre: " + this.getNombre() +
                " - Tiempo de ejecución: " + this.getTiempo() +
                " - Es critica?: " + this.getCritica() +
                " - Prioridad: " + this.getPrioridad();
    }

    @Override
    public int compareTo(Tarea t) {
        return this.prioridad - t.prioridad;
    }
}
