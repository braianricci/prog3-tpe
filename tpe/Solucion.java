package tpe;

import java.util.ArrayList;
import java.util.List;

public class Solucion {
    private boolean existe;
    private int solucionesPosibles;

    private List<String[]> solucion;

    public Solucion() {
        this.solucionesPosibles = 0;
        this.setExiste(false);
        this.solucion = new ArrayList<>();
    }

    public void guardarSolucion(List<Procesador> solucion) {

        // this.solucion.clear();

        for (Procesador p : solucion) {
            this.agregarProcesador(p);
        }
        this.setExiste(true);
        this.sumarSolucionPosible();
    }

    public List<String[]> getSolucion() {
        return this.solucion;
    }

    public boolean existe() {
        return this.existe;
    }

    private void agregarProcesador(Procesador procesador) {

        int cantidadDeProcesos = procesador.getProcesos().size();
        String[] procesadorConProcesos = new String[cantidadDeProcesos + 2];
        procesadorConProcesos[0] = procesador.getId();
        procesadorConProcesos[1] = String.valueOf(procesador.getTiempoTotal());

        for (int i = 2; i < procesadorConProcesos.length; i++) {
            procesadorConProcesos[i] = procesador.getProcesos().get(i - 2).getId();
        }

        this.solucion.add(procesadorConProcesos);
    }

    private void sumarSolucionPosible() {
        this.solucionesPosibles++;
    }

    private void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override

    public String toString() {

        String solucion = "";
        int tiempoTotal = 0;

        for (String[] procesador : this.solucion) {
            solucion += "\nTareas asignadas al proc. " + procesador[0] + ":";

            for (int i = 2; i < procesador.length; i++) {
                solucion += " tarea " + procesador[i] + ",";
            }

            solucion += " tiempo de procesamiento: " + procesador[1] + ".";
        }
        solucion += "\nCantidad de soluciones posibles generadas: " + this.solucionesPosibles;
        return solucion;
    }
}