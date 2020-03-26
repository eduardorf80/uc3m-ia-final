package practica.objetos;

/**
 * Clase creada como objeto base para la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Trabajador {

	// Variables del objeto Trabajador
	String nombre;
	int habPodar;
	int habLimpiar;
	int habReparar;
	// Nuestras variables
	Tipos_Herramientas herr;
	Areas area;

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Trabajador(String nombre, int habPodar, int habLimpiar, int habReparar) {
		this.nombre      = nombre;
		this.habPodar    = habPodar;
		this.habLimpiar  = habLimpiar;
		this.habReparar  = habReparar;
		// A�adir el estado inicial (est�tico) de las variables que se a�adan
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
		setHerramienta();
		setArea("A");
	}
	
	/**
	 * A�adir (si procede) m�todos auxiliares, como getters o setters
	 */
	public Areas getTareaCercana(){
		//Busca en funcion de herramienta actual y area
		return null;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHabPodar() {
		return habPodar;
	}
	public void setHabPodar(int habPodar) {
		this.habPodar = habPodar;
	}
	public int getHabLimpiar() {
		return habLimpiar;
	}
	public void setHabLimpiar(int habLimpiar) {
		this.habLimpiar = habLimpiar;
	}
	public int getHabReparar() {
		return habReparar;
	}
	public void setHabReparar(int habReparar) {
		this.habReparar = habReparar;
	}
	public void setHerramienta(String Herr){
		herr = Tipos_Herramientas.valueOf(Herr);
	}
	public void setHerramienta(){
		herr = null;
	}
	public Tipos_Herramientas getHerramienta() {
		return herr;
	}
	public String getUso() {
		/*switch (this.herr) {
			case Escoba:
			case Aspirador:
				return "limpiar";

			case Motosierra:
			case Tijeras_de_podar:
				return "podar";

			case Destornillador:
			case Caja_de_herramientas:
				return "reparar";
			default:
				return null;
		}*/
		return("podar");
	}
	public void setArea(String Area){
		area = Areas.valueOf(Area);
	}
	public void setArea(Areas Area){
		this.area = Area;
	}
	public Areas getArea() {
		return area;
	}
}
