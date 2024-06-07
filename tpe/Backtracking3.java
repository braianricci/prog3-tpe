package tpe;

import java.util.ArrayList;
import java.util.List;

public class Backtracking3 {

    private Solucion mejorSolucion;
    private int tareaIndex; // Variable de instancia para manejar el índice de la tarea

    public Backtracking3() {
        this.mejorSolucion = new Solucion();
        this.tareaIndex = 0; // Inicializar el índice de tarea en 0
    }

    public Solucion resolver(List<Procesador> procesadores, List<Tarea> tareas, int x) {
        backtrack(procesadores, tareas, x);
        return mejorSolucion;
    }

    // Metodo recursivo
    private void backtrack(List<Procesador> procesadores, List<Tarea> tareas, int x) {
        // Caso base: todas las tareas han sido asignadas
        if (tareaIndex == tareas.size()) {
            mejorSolucion.guardarSolucion(procesadores);
            return;
        }

        // Obtener la tarea actual
        Tarea tarea = tareas.get(tareaIndex);

        // Intentar asignar la tarea a cada procesador
        for (Procesador p : procesadores) {
            if (puedeAsignar(p, tarea, x)) {

                asignarTarea(p, tarea);
                tareaIndex++;

                backtrack(procesadores, tareas, x);

                // al volver de la recursion, se desasigna la tarea y se decrece el indice para
                // explorar otra rama
                desasignarTarea(p, tarea);
                tareaIndex--;
            }
        }
    }

    private boolean puedeAsignar(Procesador p, Tarea t, int x) {

        return (t.getCritica() ? p.getTareasCriticas() < 2 : true)
                && (p.getRefrigerado() || (p.getTiempoTotal() + t.getTiempoEjecucion() <= x));
    }

    private void asignarTarea(Procesador p, Tarea t) {
        p.agregarProceso(t);
    }

    private void desasignarTarea(Procesador p, Tarea t) {
        p.removerProceso(t);
    }
}