package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private List<Tarea> criticas, noCriticas;
	private Hashtable<String, Tarea> indiceID;
	private Tarea[] indicePrioridad;
	private List<Procesador> procesadores;
	private List<Tarea> tareas;

	/*
	 * Expresar la complejidad temporal del constructor.
	 */
	public Servicios(String pathProcesadores, String pathTareas) {

		CSVReader reader = new CSVReader();
		List<Procesador> procesadores = reader.readProcessors(pathProcesadores);
		List<Tarea> tareas = reader.readTasks(pathTareas);

		this.indiceID = new Hashtable<>();
		this.criticas = new ArrayList<>();
		this.noCriticas = new ArrayList<>();
		this.indicePrioridad = new Tarea[tareas.size()];
		this.procesadores = procesadores;
		this.tareas = tareas;

		clasificar(tareas);
		hashear(tareas);
		ordenar(tareas);
	}

	/*
	 * Expresar la complejidad temporal del servicio 1.
	 */
	public Tarea servicio1(String id) {
		return this.indiceID.get(id);
	}

	/*
	 * Expresar la complejidad temporal del servicio 2.
	 */
	public List<Tarea> servicio2(boolean esCritica) {
		if (esCritica)
			return this.criticas;
		return this.noCriticas;
	}

	/*
	 * Expresar la complejidad temporal del servicio 3.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {

		List<Tarea> tareasEnRango = new ArrayList<Tarea>();
		int length = this.indicePrioridad.length;

		// int i = buscarIndice(prioridadInferior);
		int i = binariaRecursiva2(prioridadInferior, 0, length - 1);

		for (; i < length; i++) {

			Tarea t = this.indicePrioridad[i];

			if (t.getPrioridad() <= prioridadSuperior) {
				tareasEnRango.add(t);
			}
		}

		return tareasEnRango;
	}

	public List<Procesador> servicio4(int tiempoMaximo) {

		return (backtracking(tiempoMaximo));

	}

	// Metodos privados

	private void clasificar(List<Tarea> tareas) {
		for (Tarea tarea : tareas) {
			if (tarea.getCritica()) {
				this.criticas.add(tarea);
			} else {
				this.noCriticas.add(tarea);
			}
		}
	}

	private void hashear(List<Tarea> tareas) {
		for (Tarea tarea : tareas) {
			this.indiceID.put(tarea.getID(), tarea);
		}
	}

	private void ordenar(List<Tarea> tareas) {

		Collections.sort(tareas);

		for (int i = 0; i < tareas.size(); i++) {
			this.indicePrioridad[i] = tareas.get(i);
		}
	}

	private int binariaRecursiva2(int prioridad, int inicio, int fin) {

		int medio;

		if (inicio > fin) {
			return inicio; // si no se encuentra el elemento, devolvemos inicio, lo que nos dara el indice
							// que el elemento buscado deberia ocupar en el array
		} else {

			medio = (inicio + fin) / 2;
			int medioPrio = this.indicePrioridad[medio].getPrioridad();

			if (prioridad > medioPrio)
				return binariaRecursiva2(prioridad, medio + 1, fin);
			else if (prioridad < medioPrio)
				return binariaRecursiva2(prioridad, inicio, medio - 1);
			else
				return medio;
		}
	}

	public List<Procesador> backtracking(int tiempoMaximo) {
		Backtracking backtracking = new Backtracking();
		List<Procesador> salida = backtracking.resolver(procesadores, tareas, tiempoMaximo);
		return salida;
	}

/* 	private List<Object> greedy(int tiempoMaximo) {
		return null;
	} */

	// NOT

/* 	private int buscarIndice(int prioridad) {

		// int res = busquedaNormal(prioridad);
		int res = binariaRecursiva(this.indicePrioridad, prioridad, 0, this.indicePrioridad.length - 1);

		System.out.println("i=" + res);
		return res;
	} */

/* 	private int binariaRecursiva(Tarea[] tareas, int prioridad, int inicio, int fin) {

		int medio;

		if (inicio > fin) {
			return inicio; // si no se encuentra el elemento, devolvemos inicio, lo que nos dara el indice
							// que el elemento buscado deberia ocupar en el array
		} else {

			medio = (inicio + fin) / 2;
			if (prioridad > tareas[medio].getPrioridad())
				return binariaRecursiva(tareas, prioridad, medio + 1, fin);
			else if (prioridad < tareas[medio].getPrioridad())
				return binariaRecursiva(tareas, prioridad, inicio, medio - 1);
			else
				return medio;
		}
	}

	private int busquedaNormal(int prioridad) {

		int res = -1;
		for (int i = 0; i < indicePrioridad.length; i++) {
			if (indicePrioridad[i].getPrioridad() >= prioridad) {
				res = i;
				i = indicePrioridad.length;
			}
		}

		return res;
	} */

	
}