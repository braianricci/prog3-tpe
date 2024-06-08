package tpe;

import java.util.Comparator;

public class ComparadorTiempoEjecucion implements Comparator<Tarea> {

    @Override
    public int compare(Tarea t1, Tarea t2) {
        return t2.getTiempoEjecucion() - t1.getTiempoEjecucion();
    }

}
