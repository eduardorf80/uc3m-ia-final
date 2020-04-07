package practica.objetos;

import java.util.ArrayList;

public class Debugger {
    ArrayList<Herramienta> herramientas;
    ArrayList<Trabajador>  trabajadores;
    ArrayList<Tarea> tareas;

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public Debugger(ArrayList<Herramienta> Herramientas,ArrayList<Trabajador>  Trabajadores,ArrayList<Tarea> Tareas){
        this.herramientas = Herramientas;
        this.trabajadores = Trabajadores;
        this.tareas = Tareas;
    }
    public void printTrabajadores(){
        System.out.println(ANSI_CYAN + "\nEstado actual de los trabajadores\n" + ANSI_RESET);
        for(Trabajador trabajador : trabajadores){
            System.out.println(ANSI_BLUE + "\t" + trabajador.getNombre() + " lleva trabajados " + trabajador.getMinutosTrabajados() + " minutos, tiene " + trabajador.getHerramienta() + " y se encuentra en " + trabajador.getArea() + ANSI_RESET);
        }
        System.out.println();
    }
    public void printTareas(boolean Todas){
        System.out.println(ANSI_CYAN + "\nEstado actual de las tareas\n" + ANSI_RESET);
        for (Tarea tarea: tareas) {
            if(Todas || tarea.getUnidades() > 0)
                System.out.println( ANSI_PURPLE + "\t" + "La tarea de " + tarea.getTipo() + "\tdel area " + tarea.getArea() + "\ttiene " + ((tarea.getUnidades()>=10)?tarea.getUnidades():(tarea.getUnidades()+ " ")) + " unidades restantes y esta asignada a " + tarea.getAsignada() + ANSI_RESET);
        }
        System.out.println();
    }
    public void printTareas(boolean Todas , String Tipo){
        for (Tarea tarea: tareas) {
            if((Todas || tarea.getUnidades() > 0) && tarea.getTipo().equals(Tipo))
                System.out.println( ANSI_PURPLE + "\t" + "La tarea de " + tarea.getTipo() + " del area " + tarea.getArea() + " tiene " + tarea.getUnidades() + " unidades restantes y esta asignada a  " + tarea.getAsignada() + ANSI_RESET);
        }
        System.out.println();
    }
}
