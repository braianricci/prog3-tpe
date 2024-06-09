package tpe.utils;

import java.util.Comparator;
import tpe.Tarea;

public class ComparadorPrioridad implements Comparator<Tarea> {

    @Override
    public int compare(Tarea t1, Tarea t2) {
        return t1.getPrioridad() - t2.getPrioridad();
    }
}
