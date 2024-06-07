package tpe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UserInterface {

    public static void start(Servicios servicios) {

        String userString = "11";
        System.out.println("\nBienvenido");

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            while (userString.length() >= 1) {

                System.out.println("\nPor favor, ingrese el numero de servicio que desea utilizar: [1][2][3][4]");
                userString = input.readLine();

                switch (userString) {
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                        System.out.println("Eligio el servicio [" + userString + "]");

                        switch (userString) {
                            case "1":

                                System.out.println("Por favor, ingrese el ID de la tarea que desea solicitar:");
                                userString = input.readLine();

                                Tarea tarea = servicios.servicio1(userString);
                                if (tarea != null) {
                                    System.out.println(tarea.toString());
                                } else {
                                    System.out.println("El ID ingresado es incorrecto");
                                }

                                break;
                            case "2":

                                System.out.println(
                                        "Por favor, especifique si desea ver las tareas [C]ritias, o las [N]o criticas: [C][N]");
                                userString = input.readLine();
                                List<Tarea> tareas;

                                if (userString.equals("C")) {
                                    tareas = servicios.servicio2(true);
                                } else if (userString.equals("N")) {
                                    tareas = servicios.servicio2(false);
                                } else {
                                    System.out.println("El comando ingresado es incorrecto.");
                                    break;
                                }

                                System.out.println("Lista:");
                                for (Tarea t : tareas) {
                                    System.out.println(t.toString());
                                }

                                break;
                            case "3":

                                System.out.println(
                                        "Por favor, ingrese el limite inferior de del rango de prioridad ( numero entre 0 y 100):");
                                int inf = Integer.parseInt(input.readLine());

                                if (inf < 0 || inf > 100) {
                                    System.err.println("Los limites deben estar entre 0 y 100, inclusive.");
                                    break;
                                }

                                System.out.println(
                                        "Por favor, ingrese el limite superior de del rango de prioridad ( numero entre 0 y 100):");
                                int sup = Integer.parseInt(input.readLine());

                                if (sup < inf) {
                                    System.out.println("El limite superior debe ser mayor o igual al limite inferior.");
                                    break;
                                } else if (sup > 100) {
                                    System.out.println("Los limites deben estar entre 0 y 100, inclusive.");
                                    break;
                                }

                                List<Tarea> rango = servicios.servicio3(inf, sup);

                                if (rango.size() == 0) {
                                    System.out.println("No existen tareas en el rango de prioridad solicitado.");
                                } else {
                                    for (Tarea t : rango) {
                                        System.out.println(t.toString());
                                    }
                                }

                                break;
                            case "4":

                                System.out.println(
                                        "Por favor ingrese el tiempo maximo de ejecucion para los procesadores no refrigerados:");
                                int X = Integer.parseInt(input.readLine());

                                List<Procesador> resultado = servicios.servicio4(X);
                                
                                if (resultado.isEmpty()) {
                                    System.out.println("No se encontró una asignación de tareas adecuada.");
                                } else {
                                    System.out.println("Mejor asignación de tareas:");
                                    for (Procesador p : resultado) {
                                        System.out.println(p.toString());
                                    }
                                }
                                break;
                        }
                        break;
                    default:
                        System.out.println("El servicio solicitado no existe");
                        break;
                }
            }
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }
}
