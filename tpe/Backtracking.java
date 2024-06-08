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
     * En cada recursion lo primero que chequeamos es el tiempo maximo de nuestra
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

        // poda por tiempo de ejecucion de la solucion parcial
        if (!puedeMejorar(procesadores)) {
            return;
        }
        // si todas las tareas han sido asignadas llegamos a una solucion posible
        if (tareaIndex == tareas.size()) {
            // de ser la mejor solucion hasta el momento solucion la guardara
            solucion.compararResultado(procesadores);
            return;
        }

        // obtener la tarea actual
        Tarea tarea = tareas.get(tareaIndex);

        for (Procesador p : procesadores) {

            // poda por restricciones entre tareas y procesadores
            if (puedeAsignar(p, tarea, tiempoMax)) {

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

    // chequeo de restricciones de asignacion
    private boolean puedeAsignar(Procesador p, Tarea t, int tiempoMax) {

        return (t.getCritica() ? p.getTareasCriticas() < 2 : true)
                && (p.getRefrigerado() || (p.getTiempoTotal() + t.getTiempoEjecucion() <= tiempoMax));
    }

    // chequeo de tiempo actual contra mejor tiempo
    private boolean puedeMejorar(List<Procesador> solucionParcial) {
        return (Solucion.calcularTiempoMaximo(solucionParcial) < solucion.getMejorTiempo());
    }

    private void asignarTarea(Procesador p, Tarea t) {
        p.agregarProceso(t);
    }

    private void desasignarTarea(Procesador p, Tarea t) {
        p.removerProceso(t);
    }
}