package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tpe.utils.ComparadorTiempoEjecucion;

public class Greedy {
    Solucion solucion;

    public Greedy() {
        this.solucion = new Solucion();
    }

    public Solucion resolver(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaximo) {

        List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
        Comparator<Tarea> porTiempoDeEjecucion = new ComparadorTiempoEjecucion();

        Collections.sort(tareas, porTiempoDeEjecucion);

        return solucion;
    }

}
