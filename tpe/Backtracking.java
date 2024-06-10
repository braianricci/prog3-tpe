package tpe;

import java.util.List;

public class Backtracking {

    private Solucion solucion;
    private int tareaIndex; // variable para controlar que tarea estamos asignando

    public Backtracking() {
        this.solucion = new Solucion();
        this.tareaIndex = 0; // inicializar el indice en 0
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
    public Solucion resolver(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMax) {
        backtrack(procesadores, tareas, tiempoMax);
        return solucion;
    }

    // Metodo recursivo
    private void backtrack(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMax) {

        // si todas las tareas han sido asignadas llegamos a una solucion posible
        if (tareaIndex == tareas.size()) {
            // de ser la mejor hasta el momento, se guardara, de lo contrario solo se
            // contabilizara
            solucion.evaluarSolucion(procesadores);
            return;
        }

        // obtener la tarea actual
        Tarea tarea = tareas.get(tareaIndex);

        for (Procesador p : procesadores) {

            // poda por restricciones entre tareas y procesadores, y poda chequeando si la
            // tarea fuera asignada aun podria resultar en la mejor solucion
            if (Solucion.puedeAsignar(p, tarea, tiempoMax) && puedeMejorar(procesadores, p, tarea)) {

                asignarTarea(p, tarea);
                tareaIndex++;

                // al decidir asignar la tarea, estamos generando un nuevo estado
                solucion.sumarEstado();
                backtrack(procesadores, tareas, tiempoMax);

                // al volver de la recursion, se desasigna la tarea y se decrece el indice para
                // explorar otra rama
                desasignarTarea(p, tarea);
                tareaIndex--;
            }
        }
    }

    // chequeo de tiempo resultante de agregar tarea a un procesador, contra mejor
    // tiempo hasta ahora
    private boolean puedeMejorar(List<Procesador> solucionParcial, Procesador p, Tarea tarea) {
        int tiempoP = p.getTiempoTotal() + tarea.getTiempoEjecucion();
        int tiempoMax = Solucion.calcularTiempoMaximo(solucionParcial);

        return Math.max(tiempoP, tiempoMax) < solucion.getMejorTiempo();
    }

    private void asignarTarea(Procesador p, Tarea t) {
        p.agregarProceso(t);
    }

    private void desasignarTarea(Procesador p, Tarea t) {
        p.removerProceso(t);
    }
}
