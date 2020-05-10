package practica.objetos;

/**
 * Clase creada como objeto base para la practica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Tarea {

	// Variables del objeto Tarea
	String tipo;
	Areas area;
	int unidades;

	//Guarda a que trabajador pertenece la tarea para que no trabajen dos en el mismo sitio
	String asignada;

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Tarea(String tipo, String area, int unidades) {
		setTipo(tipo);
		setArea(area);
		setUnidades(unidades);
		//inicialmente no le pertenece a nadie
		setAsignada(null);
	}
	
	// Metodos getters y setters
	/**
	 * Anadir (si procede) metodos auxiliares, como getters o setters
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
	public void setAsignada() {
		this.asignada = null;
	}
	public String getAsignada() {
		return asignada;
	}

	//Si la tarea esta asignada a ese trabajador o no la tiene nadie devuelve true(es decir la puede realizar ese trabajador)
	public boolean getDisponible(String nombre) {
		return asignada == null || asignada.equals(nombre);
	}
	//Le pasas el tipo de herramienta y te dice si es valida o no por el numero de unidades, si no esta asignada y si coinciden los tipos de la tarea y el del parametro
	public boolean isValid(String Tipo){
		return getUnidades() > 0 && getAsignada() == null && getTipo().equals(Tipo);
	}
}
