package practica.objetos;

/**
 * Clase creada como objeto base para la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Tarea {

	// Variables del objeto Tarea
	String tipo;
	Areas area;
	int unidades;
	// A�ADIR LAS VARIABLES NECESARIAS
	String asignada;

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Tarea(String tipo, String area, int unidades) {
		setTipo(tipo);
		setArea(area);
		setUnidades(unidades);
		setAsignada(null);
		// A�adir el estado inicial (est�tico) de las variables que se a�adan
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
	}
	
	// M�todos getters y setters
	/**
	 * A�adir (si procede) m�todos auxiliares, como getters o setters
	 */
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Areas getArea() {
		return area;
	}
	public void setArea(String Area) {
		area = Areas.valueOf(Area);
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public void setAsignada(String asignada) {
		this.asignada = asignada;
	}
	public String getAsignada() {
		return asignada;
	}
	public boolean getAsignada(String nombre) {
		if(nombre.equals("Bernardo")){
			System.out.print("");
		}
		return asignada != null && !asignada.equals(nombre);
	}
}
