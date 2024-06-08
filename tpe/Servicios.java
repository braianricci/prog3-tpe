package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	// estructuras para los servicios 1-3
	/*
	 * Decidimos utilizar estructuras sencillas, a modo de indices; priorizando
	 * optimizar el acceso a los datos y no considerar los costos de construccion,
	 * insercion, borrado, etc; dada la ausencia de insercion, modificacion y
	 * borrado en tiempo de ejecucion.
	 */
	private HashMap<String, Tarea> indiceID;
	private List<Tarea> criticas, noCriticas;
	private List<Tarea> indicePrioridad;

	// estructuras para el servicio 4, no optimizadas y en el mismo orden presente
	// en los .csv.
	private List<Tarea> tareas;
	private List<Procesador> procesadores;

	/*
	 * Constructor: O(n.log n).
	 * 
	 * Multiples veces en el constructor, realizamos la tarea de crear y recorrer
	 * 1 por 1 todos los elementos expresados en el dataset, lo que
	 * significa una complejidad O(x.n), que resulta en O(n) al ignorar el valor
	 * constante x.
	 * Lamentablemente, la complejidad se eleva por la inclusion de el metodo de
	 * ordenamiento Collections.sort() llamado por la funcion ordenar(). Utiliza
	 * ordenamiento Timsort, una version mejorada de Mergesort que en el peor de
	 * los casos tiene una complejidad de O(n.log n)
	 */
	public Servicios(String pathProcesadores, String pathTareas) {

		CSVReader reader = new CSVReader();
		this.procesadores = reader.readProcessors(pathProcesadores);
		this.tareas = reader.readTasks(pathTareas);

		this.indiceID = new HashMap<>();
		this.criticas = new ArrayList<>();
		this.noCriticas = new ArrayList<>();
		this.indicePrioridad = new ArrayList<>(this.tareas);

		hashear();
		clasificar();
		ordenar();
	}

	/*
	 * Servicio 1: O(1).
	 *
	 * Por tratarse de un HashMap, la complejidad sera de O(1).
	 */
	public Tarea servicio1(String id) {
		return this.indiceID.get(id);
	}

	/*
	 * Servicio 2: O(1).
	 *
	 * Al retornar cualquiera de las dos listas solicitadas; la complejidad
	 * computacional sera de O(1) ya que la operacion implica el retorno de una
	 * referencia a un objeto List<Tarea>.
	 */
	public List<Tarea> servicio2(boolean esCritica) {
		if (esCritica)
			return this.criticas;
		return this.noCriticas;
	}

	/*
	 * Servicio 3: O(log n).
	 *
	 * Utilizamos una busqueda binaria recursiva sobre una lista ordenada. La
	 * complejidad de cada busqueda es O(log n), y aunque utilicemos 2 busquedas
	 * consecutivas, los factores constantes no se tendran en cuenta y O(2.log n)
	 * seguira siendo equivalente a O(log n).
	 * Luego creamos una subList utilizando los indices encontrados. Como una
	 * subList es una "vista" de la lista original, no hay nuevos accesos a memoria
	 * y solo se trata de una referencia a las tareas originales, por lo que la
	 * complejidad de esta operacion, O(1), no suma a la complejidad predominante en
	 * la funcion.
	 */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {

		int length = this.indicePrioridad.size();

		int i = binariaRecursiva(prioridadInferior, 0, length - 1);
		int s = binariaRecursiva(prioridadSuperior, 0, length - 1);

		return this.tareas.subList(i, s);
	}

	// implementamos la asignacion de tareas como servicio 4.
	public Solucion servicio4(int tiempoMaximo) {

		Backtracking backtracking = new Backtracking();
		return backtracking.resolver(procesadores, tareas, tiempoMaximo);
	}

	// Metodos privados

	private void clasificar() {
		for (Tarea tarea : this.tareas) {
			if (tarea.getCritica()) {
				this.criticas.add(tarea);
			} else {
				this.noCriticas.add(tarea);
			}
		}
	}

	private void hashear() {
		for (Tarea tarea : this.tareas) {
			this.indiceID.put(tarea.getId(), tarea);
		}
	}

	private void ordenar() {
		Collections.sort(this.indicePrioridad);
	}

	private int binariaRecursiva(int prioridad, int inicio, int fin) {

		int medio;

		if (inicio > fin) {
			return inicio; // si no se encuentra el elemento, devolvemos inicio, lo que nos dara el indice
							// que el elemento buscado deberia ocupar en el array
		} else {

			medio = (inicio + fin) / 2;
			int medioPrio = this.indicePrioridad.get(medio).getPrioridad();

			if (prioridad > medioPrio)
				return binariaRecursiva(prioridad, medio + 1, fin);
			else if (prioridad < medioPrio)
				return binariaRecursiva(prioridad, inicio, medio - 1);
			else
				return medio;
		}
	}
}