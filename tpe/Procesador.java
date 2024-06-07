package tpe;

import java.util.ArrayList;
import java.util.List;

public class Procesador {

    private String id;
    private String codigo;
    private boolean refrigerado;
    private int anio;
    private List<Tarea> procesos;

    public Procesador(String id, String codigo, boolean refrigerado, int anio) {
        this.procesos = new ArrayList<>();
        this.setId(id);
        this.setCodigo(codigo);
        this.setRefrigerado(refrigerado);
        this.setAnio(anio);
    }

    public void agregarProceso(Tarea tarea) {
        this.procesos.add(tarea);
    }

    public void removerProceso(Tarea tarea){
        procesos.remove(tarea);
    }


    public List<Tarea> getProcesos() {
        return procesos;
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getAnio() {
        return anio;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public boolean getRefrigerado() {
        return this.refrigerado;
    }

    private void setAnio(int anio) {
        this.anio = anio;
    }

    public int getTiempoTotal() {
        int tiempoTotal = 0;
        for (Tarea tarea : procesos) {
            tiempoTotal += tarea.getTiempoEjecucion();
        }
        return tiempoTotal;
    }

    public int getTareasCriticas() {
        int count = 0;
        for (Tarea tarea : procesos) {
            if (tarea.getCritica()) {
                count++;
            }
        }
        return count;
    }



}