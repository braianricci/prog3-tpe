# TUDAI - Programación 3 - Trabajo Práctico Especial - Parte 1 y 2.

Integrantes:

- Braian Ricci, DNI: 35.562.572, e-mail: braianricci@gmail.com
- Esteban Gabriel Villanueva, DNI: 34.759.729, e-mail: estebangv.2023@gmail.com

Se incluye una clase Main, que llama una clase UserInterface utilizada para la prueba de los servicios requeridos.
Se decidió incluir la asignación de tareas a procesadores con las estrategias solicitadas, como servicio [4].

# Reentrega

Mantenemos una duda con respecto a la correccion, y es que se indica que usamos erroneamente una lista vinculada para el servicio 3. Nosotros usamos una ArrayList y en la documentacion que consultamos ArrayList es sencillamente un arreglo dinamico, que durante el servicio 3 no pasa por ningun crecimiento/decrecimiento, por lo que el servicio (que utiliza busqueda binaria) no deberia incurrir en ninguna complejidad mayor a O(log n).

Pese a esto, igualmente modificamos la clase Servicios basandonos en las pautas de correccion. Agradeceriamos enormemente clarificacion sobre la diferencia entre utilizar para este caso, un array o una ArrayList.

## Puntos modificados para la reentrega:

- ## En Servicios.java:

  - indicePrioridad es ahora un arreglo (cambiado de ArrayList) para garantizar la eficiencia de la busqueda binaria en el servicio 3.
  - servicio3() ahora incluye manejo de edgecases previo a la creacion de la sublista (habia casos muy puntuales que podian omitir el ultimo elemento del rango buscado).
  - ordenar() utiliza Arrays.sort() (cambiado de Collections.sort()).

- ## En Procesador.java:

  - tiempoTotal es ahora una variable de instancia.
  - tareasCriticas es ahora una variable de instancia.
  - getTiempoTotal y getTareasCriticas devuelven ahora sus variables correspondientes, bajando su complejidad a O(1).
  - agregarProceso() es el metodo encargado de evaluar y sumar a tiempoTotal y tareasCriticas cuando una tarea es agregada.
  - removerProceso() y limpiarProcesos() se encargan de restar a, o resetear, estas variables en caso de ser necesario.

- ## En Backtracking.java:

  - El tiempo maximo de procesamiento de la solucion actual es ahora una variable de instancia.
  - puedeMejorar() ya no llama la funcion estatica Solucion.calcularTiempoMaximo(), sino que compara con la nueva variable de instancia.
  - puedeMejorar() ya no recibe como parametro una lista de procesadores.
  - El constructor ahora recibe el tiempo maximo para procesadores no refrigerados como parametro y lo pasa al constructor de su objeto Solucion().

- ## En Greedy.java:

  - El constructor ahora recibe el tiempo maximo para procesadores no refrigerados como parametro y lo pasa al constructor de su objeto Solucion().
  - El tiempo maximo actual se va llevando actualizado y se pasa como parametro a la solucion (previamente el objeto Solucion lo calculaba).

- ## En Solucion.java:

  - evaluarSolucion() ahora recibe como parametro adicional el tiempo maximo actual (previamente lo calculaba) y lo compara directamente con la variable de instancia mejorTiempo.
  - El tiempo maximo de procesamiento para procesadores no refrigerados es ahora una variable de instancia cargada como parametro desde el constructor de la clase Solucion.
  - La funcion puedeAsignar() ya no es estatica y al contar con una variable de instancia ya no recibe el tiempo maximo para no refrigerados como parametro.
  - La funcion estatica calcularTiempoMaximo() fue removida.
