package tpe;

import java.util.List;

public class Backtracking {

    private Solucion solucion;
    private int tareaIndex; // variable para controlar que tarea estamos asignando
    private int tiempoMaxActual; // variable que controla el tiempo maximo de ejecucion de la solucion actual

    public Backtracking(int tiempoMaxEjecucionNoRefrigerados) {
        this.solucion = new Solucion(tiempoMaxEjecucionNoRefrigerados);
        this.tareaIndex = 0; // inicializar el indice en 0
        this.tiempoMaxActual = 0;
    }

    /*
     * Utilizaremos las listas originales de procesadores y tareas como solucion
     * parcial que pasamos como parametro junto al tamaño maximo ingresado por el
     * usuario. Asignaremos las tareas de ser posible, llamaremos la recursion, y al
     * regresar las vamos a desasignar para explorar otras posibilidades.
     * De no poder asignarlas se corta la recursion.
     * Antes de cada recursion tambien chequeamos el tiempo maximo de nuestra
     * solucion parcial contra nuestro mejor resultado; si no lo podemos mejorar,
     * podamos.
     * Al asignar todas las tareas, pasamos la solucion posible para ser contada,
     * comparada, y en caso de ser la mejor hasta el momento, guardada.
     */
    public Solucion resolver(List<Procesador> procesadores, List<Tarea> tareas) {
        backtrack(procesadores, tareas);
        return solucion;
    }

    // Metodo recursivo
    private void backtrack(List<Procesador> procesadores, List<Tarea> tareas) {

        // si todas las tareas han sido asignadas llegamos a una solucion posible
        if (tareaIndex == tareas.size()) {
            // de ser la mejor hasta el momento, se guardara, de lo contrario solo se
            // contabilizara
            solucion.evaluarSolucion(procesadores, tiempoMaxActual);
            return;
        }

        // obtener la tarea actual
        Tarea tarea = tareas.get(tareaIndex);

        for (Procesador p : procesadores) {

            // poda por restricciones entre tareas y procesadores, y poda chequeando si la
            // tarea fuera asignada aun podria resultar en la mejor solucion
            if (solucion.puedeAsignar(p, tarea) && puedeMejorar(p, tarea)) {

                asignarTarea(p, tarea);
                tareaIndex++;
                int tmp = tiempoMaxActual;
                tiempoMaxActual = Math.max(p.getTiempoTotal(), tiempoMaxActual);

                // al decidir asignar la tarea, estamos generando un nuevo estado
                solucion.sumarEstado();
                backtrack(procesadores, tareas);

                // al volver de la recursion, se desasigna la tarea, se decrece el indice y se
                // retorna al tiempo maximo anterior para explorar otra rama
                desasignarTarea(p, tarea);
                tareaIndex--;
                tiempoMaxActual = tmp;
            }
        }
    }

    // chequeo de tiempo resultante de agregar tarea a un procesador, contra mejor
    // tiempo hasta ahora
    private boolean puedeMejorar(Procesador p, Tarea tarea) {
        int tiempoP = p.getTiempoTotal() + tarea.getTiempoEjecucion();

        return Math.max(tiempoP, tiempoMaxActual) < solucion.getMejorTiempo();
    }

    private void asignarTarea(Procesador p, Tarea t) {
        p.agregarProceso(t);
    }

    private void desasignarTarea(Procesador p, Tarea t) {
        p.removerProceso(t);
    }
}
