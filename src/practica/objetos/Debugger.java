package practica.objetos;

import java.util.ArrayList;

/***********************************
 * Esta clase no se pide pero ha sido desarrollada
 * para poder comprobar el funcionamiento del problema
 *
 */
public class Debugger {
    ArrayList<Herramienta> herramientas;
    ArrayList<Trabajador>  trabajadores;
    ArrayList<Tarea> tareas;

    //Constantes para mostrarlo con colores en la terminal
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    //PAra crearlo necesita tener los tres arraylist para poder mostrar su informacion
    public Debugger(ArrayList<Herramienta> Herramientas,ArrayList<Trabajador>  Trabajadores,ArrayList<Tarea> Tareas){
        this.herramientas = Herramientas;
        this.trabajadores = Trabajadores;
        this.tareas = Tareas;
    }
    //Muestra el estado actual de los trabajadores
    public void printTrabajadores(){
        System.out.println(ANSI_CYAN + "\nEstado actual de los trabajadores\n" + ANSI_RESET);
        for(Trabajador trabajador : trabajadores){
            System.out.println(ANSI_BLUE + "\t" + String.format("%-8s",trabajador.getNombre()) + " lleva trabajadas " + String.format("%-2s",trabajador.getUnidadesTrabajadas()) + " unidades que corresponden a " + String.format("%-4s",Math.round(trabajador.getMinutosTrabajados())) + " minutos, " + String.format("%-22s",(trabajador.getHerramienta()==null)?"no tiene herramienta":"tiene "+trabajador.getHerramienta().getNombre()) + " y se encuentra en " + trabajador.getArea() + ANSI_RESET);
        }
        System.out.println();
    }
    /**********************************************
     * Muestra el estado actual de los trabajadores
     * @param Todas si es true muestra todas las tareas sino solo las que tengan unidades restantes
     */
    public void printTareas(boolean Todas){

        System.out.println(ANSI_CYAN + "\nEstado actual de las tareas\n" + ANSI_RESET);
        for (Tarea tarea: tareas) {
            if(Todas || tarea.getUnidades() > 0)
                System.out.println( ANSI_PURPLE + "\t" + "La tarea de " + tarea.getTipo() + "\tdel area " + tarea.getArea() + "\ttiene " + ((tarea.getUnidades()>=10)?tarea.getUnidades():(tarea.getUnidades()+ " ")) + " unidades restantes y esta asignada a " + tarea.getAsignada() + ANSI_RESET);
        }
        System.out.println();
    }
    /**********************************************
     * Muestra el estado actual de los trabajadores
     * @param Todas si es true muestra todas las tareas sino solo las que tengan unidades restantes
     * @param Tipo filtra por tipo de tarea, poda, limpieza o reparar
     */
    public void printTareas(boolean Todas , String Tipo) {
        System.out.println(ANSI_CYAN + "\nEstado actual de las tareas\n" + ANSI_RESET);
        for (Tarea tarea : tareas) {
            if ((Todas || tarea.getUnidades() > 0) && tarea.getTipo().equals(Tipo))
                System.out.println(ANSI_PURPLE + "\t" + "La tarea de " + tarea.getTipo() + " del area " + tarea.getArea() + " tiene " + tarea.getUnidades() + " unidades restantes y esta asignada a  " + tarea.getAsignada() + ANSI_RESET);
        }
        System.out.println();
    }
    //Muestra el estado actual de las herramientas
    public void printHerramientas(){
        System.out.println(ANSI_CYAN + "\nEstado actual de las herramientas\n" + ANSI_RESET);
        for (Herramienta herramienta: herramientas){
            System.out.println(ANSI_GREEN + "\t"+ "La herramienta " + String.format("%-20s",herramienta.getNombre()) + " sirve para " + String.format("%-7s",herramienta.getTrabajo()) + " de la cual hay " + herramienta.getCantidad() + " unidades de las cuales quedan disponibles " + herramienta.getDisponibles() + " unidades y da una mejora de " + herramienta.getMejora() + " y pesa " + herramienta.getPeso() + ANSI_RESET);
        }
        System.out.println();
    }
}
