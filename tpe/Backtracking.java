package tpe;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {
    // Atributos necesarios para almacenar la mejor solución
    private List<Procesador> mejorAsignacion;
    private int tiempoMaximoMinimo;
    private int tiempoActual; // Variable de instancia para manejar el tiempo actual
    private int tareaIndex; // Variable de instancia para manejar el índice de la tarea

    // Constructor para inicializar
    public Backtracking() {
        this.mejorAsignacion = new ArrayList<>();
        this.tiempoMaximoMinimo = Integer.MAX_VALUE; // Inicializar con el valor máximo
        this.tiempoActual = 0; // Inicializar el tiempo actual en 0
        this.tareaIndex = 0; // Inicializar el índice de tarea en 0
    }

    // Método principal que se llama para resolver
    public List<Procesador> resolver(List<Procesador> procesadores, List<Tarea> tareas, int X) {
        tareaIndex = 0; // Asegurarse de que el índice de la tarea se inicializa a 0
        backtrack(procesadores, tareas, X); // Llamar a backtrack sin pasar el índice
        return mejorAsignacion;
    }

    // Método recursivo de backtracking
    private void backtrack(List<Procesador> procesadores, List<Tarea> tareas, int X) {
        // Caso base: todas las tareas han sido asignadas
        if (tareaIndex == tareas.size()) {
            if (tiempoActual < tiempoMaximoMinimo) {
                tiempoMaximoMinimo = tiempoActual;
                mejorAsignacion = new ArrayList<>(procesadores);
            }
            return;
        }

        // Obtener la tarea actual
        Tarea tarea = tareas.get(tareaIndex);
        
        // Intentar asignar la tarea a cada procesador
        for (Procesador p : procesadores) {
            if (puedeAsignar(p, tarea, X)) {
                asignarTarea(p, tarea);
                int tiempoAnterior = tiempoActual; // Guardar el tiempo actual antes de la asignación
                tiempoActual = Math.max(tiempoActual, p.getTiempoTotal()); // Actualizar el tiempo actual
                tareaIndex++; // Incrementar tareaIndex
                backtrack(procesadores, tareas, X); // Llamada recursiva
                tareaIndex--; // Restaurar el índice de la tarea después de desasignar la tarea
                desasignarTarea(p, tarea);
                tiempoActual = tiempoAnterior; // Restaurar el tiempo actual después de desasignar la tarea
            }
        }
    }

    // Método para verificar si se puede asignar una tarea a un procesador
    private boolean puedeAsignar(Procesador p, Tarea t, int X) {
        // Verificar restricciones
        return (t.getCritica() ? p.getTareasCriticas() < 2 : true) &&
               (p.getRefrigerado() || (p.getTiempoTotal() + t.getTiempoEjecucion() <= X));
    }

    // Método para asignar una tarea a un procesador
    private void asignarTarea(Procesador p, Tarea t) {
        p.agregarProceso(t);
    }

    // Método para desasignar una tarea de un procesador
    private void desasignarTarea(Procesador p, Tarea t) {
        p.removerProceso(t);
    }
}
