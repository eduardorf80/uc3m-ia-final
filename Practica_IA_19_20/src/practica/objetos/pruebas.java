package practica.objetos;


public class pruebas {
    public static void main(String[] args) {
        System.out.println(Informacion.getCoste(Areas.R,Areas.B));
        Trabajador test = new Trabajador("Paco",1,2,3);
        System.out.println(test.getHerramienta());
    }
}
