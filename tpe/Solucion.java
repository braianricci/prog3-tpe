package tpe;

import java.util.ArrayList;
import java.util.List;

public class Solucion {
    private int cantidadDeEstados, cantidadDeSolucionesPosibles, mejorTiempo;
    private List<String[]> mejorResultado;

    public Solucion() {
        this.cantidadDeEstados = 0;
        this.cantidadDeSolucionesPosibles = 0;
        this.mejorTiempo = Integer.MAX_VALUE;
        this.mejorResultado = new ArrayList<>();
    }

    public void compararResultado(List<Procesador> solucion) {

        this.cantidadDeSolucionesPosibles++;

        int tiempoMaximo = Solucion.calcularTiempoMaximo(solucion);

        if (tiempoMaximo < this.mejorTiempo) {
            this.mejorTiempo = tiempoMaximo;
            this.guardarSolucion(solucion, mejorTiempo);
        }
    }

    public void sumarEstado() {
        this.cantidadDeEstados++;
    }

    public int getMejorTiempo() {
        return this.mejorTiempo;
    }

    public static int calcularTiempoMaximo(List<Procesador> procesadores) {

        int tiempoMaximo = 0;
        for (Procesador p : procesadores) {
            int tiempoDeProcesado = p.getTiempoTotal();
            if (tiempoDeProcesado > tiempoMaximo) {
                tiempoMaximo = tiempoDeProcesado;
            }
        }

        return tiempoMaximo;
    }

    private void guardarSolucion(List<Procesador> solucion, int tiempo) {

        this.mejorResultado.clear();
        for (Procesador p : solucion) {
            this.agregarProcesadorASolucion(p);
        }
    }

    private void agregarProcesadorASolucion(Procesador procesador) {

        int numProcesos = procesador.getProcesos().size();
        String[] dataProcesador = new String[numProcesos + 2];
        dataProcesador[0] = procesador.getId();
        dataProcesador[1] = String.valueOf(procesador.getTiempoTotal());

        for (int i = 2; i < dataProcesador.length; i++) {
            dataProcesador[i] = procesador.getProcesos().get(i - 2).getId();
        }

        this.mejorResultado.add(dataProcesador);
    }

    @Override
    public String toString() {

        if (this.mejorResultado.isEmpty()) {
            return "No se encontró una asignación de tareas adecuada.";
        }

        String solucion = "\nSolución encontrada:";

        for (String[] procesador : this.mejorResultado) {
            solucion += "\nTareas asignadas al proc. " + procesador[0] + ":";

            for (int i = 2; i < procesador.length; i++) {
                solucion += " tarea " + procesador[i] + ",";
            }

            solucion += " tiempo de ejecucion: " + procesador[1] + ".";
        }

        return solucion += "\nTiempo maximo de ejecución: " + this.mejorTiempo + "\nSe generaron "
                + this.cantidadDeEstados + " estados, que resultaron en " + cantidadDeSolucionesPosibles
                + " soluciones posibles evaluadas.";
    }
}