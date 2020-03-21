package practica.objetos;

public abstract class Costes {
    static byte costes[][] = new byte[][]{
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

    public static byte getCoste(char Fila, char Columna) {
        return 0;
    }
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