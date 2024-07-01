TUDAI - Programación 3 - Trabajo Práctico Especial - Parte 1 y 2.

Integrantes: Braian Ricci, DNI: 35.562.572, e-mail: braianricci@gmail.com
Esteban Gabriel Villanueva, DNI: 34.759.729, e-mail: estebangv.2023@gmail.com

Se incluye una clase Main, que llama una clase UserInterface utilizada para la prueba de los servicios requeridos.
Se decidió incluir la asignación de tareas a procesadores con las estrategias solicitadas, como servicio [4].

Puntos modificados para la reentrega:

- ## En Servicios.java:

  - indicePrioridad es ahora un arreglo (cambiado de lista vinculada) para garantizar la eficiencia de la busqueda binaria en el servicio 3.
  - servicio3() ahora incluye manejo de edgecases en la creacion de la sublista.
  - ordenar() utiliza Arrays.sort() (cambiado de Collections.sort()).

- ## En Procesador.java:

  - tiempoTotal es ahora una variable de instancia.
  - tareasCriticas es ahora una variable de instancia.
  - getTiempoTotal y getTareasCriticas devuelven ahora sus variables correspondientes, bajando su complejidad a O(1).
  - agregarProceso() es el metodo encargado de evaluar y sumar a tiempoTotal y tareasCriticas cuando una tarea es agregada.
  - removerProceso() y limpiarProcesos() se encargan de restar a, o resetear, estas variables en caso de ser necesario.

- ## En Backtracking.java:

  - El tiempo maximo de procesamiento de la solucion actual es ahora una variable de instancia.
  - puedeMejorar() ya no recibe como parametro una lista de procesadores.
  - puedeMejorar() ya no llama la funcion estatica Solucion.calcularTiempoMaximo(), sino que compara con la nueva variable de instancia.
  - El constructor ahora recibe el tiempo maximo de ejecucion de procesadores no refrigerados como parametro y lo pasa al constructor de su objeto Solucion().

- ## En Greedy.java:

  - El constructor ahora recibe el tiempo maximo de ejecucion de procesadores no refrigerados como parametro y lo pasa al constructor de su objeto Solucion().
  - El tiempo maximo actual se va llevando actualizado y se pasa como parametro a la solucion (previamente el objeto Solucion lo calculaba).

- ## En Solucion.java:

  - El tiempo maximo de procesamiento para procesadores no refrigerados es ahora una variable de instancia cargada como parametro desde el constructor de la clase Solucion.
  - evaluarSolucion() ahora recibe como parametro adicional el tiempo maximo actual y lo compara directamente con la variable de instancia mejorTiempo.
  - La funcion puedeAsignar() ya no es estatica y al contar con una variable de instancia ya no recibe el tiempo maximo como parametro.
  - La funcion estatica calcularTiempoMaximo() fue removida.
