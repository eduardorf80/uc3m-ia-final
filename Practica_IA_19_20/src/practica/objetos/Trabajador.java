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
		this.herr = null;
		this.area = null ;
	}
	
	/**
	 * A�adir (si procede) m�todos auxiliares, como getters o setters
	 */
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
	public void setHerramienta(String herr){
		switch (herr.toLowerCase()){
			case "escoba":
				this.herr = Tipos_Herramientas.Escoba;
				break;
			case "espirador":
				this.herr = Tipos_Herramientas.Aspirador;
				break;
			case "caja de herramientas":
				this.herr = Tipos_Herramientas.Caja_de_herramientas;
				break;
			case "destornillador":
				this.herr = Tipos_Herramientas.Destornillador;
				break;
			case "motosierra":
				this.herr = Tipos_Herramientas.Motosierra;
				break;
			case "tijeras de podar":
				this.herr = Tipos_Herramientas.Tijeras_de_podar;
				break;

			default:
				this.herr = null;
		}
	}
	public  void setArea(String area){
		switch (area.toLowerCase()){
			case "a":
				this.area = Areas.A;
				break;
			case "b":
				this.area = Areas.B;
				break;
			case "c1":
				this.area = Areas.C1;
				break;
			case "c2":
				this.area = Areas.C2;
				break;
			case "j1":
				this.area = Areas.J1;
				break;
			case "j2":
				this.area = Areas.J2;
				break;
			case "j3":
				this.area = Areas.J3;
				break;
			case "r":
				this.area = Areas.R;
				break;
			case "u":
				this.area = Areas.U;
				break;
			default:
				this.area = null;

		}
	}
}
