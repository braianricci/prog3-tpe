package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tpe.utils.ComparadorTiempoEjecucion;
import tpe.utils.ComparadorTiempoEjecucionTotal;

public class Greedy {
    Solucion solucion;

    public Greedy() {
        this.solucion = new Solucion();
    }

    /*
     * Nuestra estrategia consiste en tratar de asignar la tarea mas costosa, al
     * procesador con menos trabajo que capaz de recibirla
     */
    public Solucion resolver(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaximo) {

        // creamos nuevas listas para reordenarlas sin perder las originales,
        // garantizando reusabilidad.
        List<Tarea> tareasOrdenadas = new ArrayList<>(tareas);
        List<Procesador> procOrdenados = new ArrayList<>(procesadores);

        // se ordenan las tareas por tiempo de ejecucion, de mayor a menor, y las
        // utilizamos en ese orden.
        Collections.sort(tareasOrdenadas, new ComparadorTiempoEjecucion());
        for (Tarea t : tareasOrdenadas) {

            // se ordenan los procesadores por tiempo de ejecucion total, de menor a mayor,
            // y en ese orden intentamos asignarles la tarea actual. En caso de igual
            // tiempo, se priorizan los procesadores refrigerados (con capacidad no
            // limitada).
            Collections.sort(procOrdenados, new ComparadorTiempoEjecucionTotal());
            Boolean tareaAsignada = false;
            for (Procesador p : procOrdenados) {

                // se evaluan las restricciones y de ser posible, se agrega la tarea.
                if (Solucion.puedeAsignar(p, t, tiempoMaximo)) {
                    p.agregarProceso(t);
                    solucion.sumarEstado();
                    tareaAsignada = true;
                    // salimos del loop para no seguir evaluando procesadores.
                    break;
                }
            }

            // si una tarea no se puede asignar a ningun procesador, nuestra
            // estrategia fallo en encontrar una solucion y terminamos.
            if (!tareaAsignada) {
                limpiarProcesos(procesadores);
                return solucion;
            }
        }

        // al tener todas las tareas asignadas, guardamos la solucion final
        solucion.evaluarSolucion(procesadores);

        // reseteamos los procesadores en caso de querer utilizar el servicio nuevamente
        limpiarProcesos(procesadores);
        return solucion;
    }

    private void limpiarProcesos(List<Procesador> procesadores) {
        for (Procesador p : procesadores) {
            p.limpiarProcesos();
        }
    }
}