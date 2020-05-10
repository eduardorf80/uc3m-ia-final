package practica.objetos;

public abstract class Informacion {
    //Matriz creada para ver los costes de desplazamiento entre areas
    static byte costes[][] = new byte[][]{
            //R J3 A   C2  J2  C1   U  J1  B
            {0, 5, 10, 10, 15, 15, 20, 20, 25},
            {5, 0, 5, 5, 10, 10, 15, 15, 20},
            {10, 5, 0, 5, 5, 10, 10, 10, 15},
            {10, 5, 5, 0, 5, 5, 10, 10, 15},
            {15, 10, 5, 5, 0, 5, 5, 5, 10},
            {15, 10, 10, 5, 5, 0, 10, 5, 10},
            {20, 15, 10, 10, 5, 10, 0, 5, 5},
            {20, 15, 10, 10, 5, 5, 5, 0, 5},
            {25, 20, 15, 15, 10, 10, 5, 5, 0},
    };
    //Calcula el coste de desplazamiento ente dos areas teniendo en cuenta el peso de la herramienta
    public static double getCoste(Areas Origen, Areas Destino, double peso) {
        //Busca los indices de la matriz
        int fila = getIndex(Origen) , columna = getIndex(Destino);
        //Mira que sean valids, si lo son devulve el coste de desplazamiento
        if(!(fila == -1 || columna == -1))
            return (double)(costes[fila][columna]*((5+peso)/5));
        return -1;
    }
    //Traduce las areas en indicies de la matriz
    private static byte getIndex(Areas Area){
        switch (Area) {
            case R:
                return 0;
            case J3:
                return 1;
            case A:
                return 2;
            case C2:
                return 3;
            case J2:
                return 4;
            case C1:
                return 5;
            case U:
                return 6;
            case J1:
                return 7;
            case B:
                return 8;
            default:
                return -1;
        }
    }
}