package practica.objetos;


import java.util.ArrayList;

public class pruebas {
    public static void main(String[] args) {
        //System.out.println(Informacion.getCoste(Areas.R,Areas.R));
        Trabajador test = new Trabajador("Paco",1,2,3);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(0,9);
        System.out.println(arrayList.get(0));
        hola(arrayList);
        System.out.println(arrayList.get(0));

        System.out.println();
    }
    public static void hola(ArrayList<Integer> arrayList){
        arrayList.set(0,10);
    }
}
