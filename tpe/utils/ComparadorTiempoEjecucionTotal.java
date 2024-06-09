package tpe.utils;

import java.util.Comparator;
import tpe.Procesador;

public class ComparadorTiempoEjecucionTotal implements Comparator<Procesador> {

    // en el ordenamiento de los procesadores por tiempo de ejecucion total,
    // priorizamos los procesadores refrigerados en caso de igual tiempo.
    @Override
    public int compare(Procesador p1, Procesador p2) {
        int dif = p1.getTiempoTotal() - p2.getTiempoTotal();
        if (dif == 0) {
            if (p1.getRefrigerado() == p2.getRefrigerado()) {
                return 0;
            } else if (p1.getRefrigerado()) {
                return -1;
            } else
                return 1;
        }
        return dif;
    }
}
