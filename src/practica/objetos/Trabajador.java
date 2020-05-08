package practica.objetos;

import java.util.ArrayList;

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
	double minutosTrabajados;
	int unidadesTrabajadas;
	Areas area;
	public String [] habilidades = new String[3];
	ArrayList<Tarea> tareas;
	Herramienta herramienta;

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
		setArea("A");
		setHabilidades();
		this.minutosTrabajados = 0;
		this.unidadesTrabajadas = 0;
		setHerramienta();
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
	public String getHabMax(int Pos){
		return habilidades[Pos];
	}
	public String[] getHabMax(){
		return habilidades;
	}

	public void setHabilidades() {
		if(getHabPodar() >= getHabLimpiar() && getHabPodar() >= getHabReparar()){
			habilidades[0] = "podar";
			if(getHabLimpiar() >= getHabReparar()){
				habilidades[1] = "limpiar";
				habilidades[2] = "reparar";
			}else {
				habilidades[1] = "reparar";
				habilidades[2] = "limpiar";
			}
		}else if(getHabLimpiar() >= getHabPodar() && getHabLimpiar() >= getHabReparar()){
			habilidades[0] = "limpiar";
			if(getHabPodar() >= getHabReparar()){
				habilidades[1] = "podar";
				habilidades[2] = "reparar";
			}else {
				habilidades[1] = "reparar";
				habilidades[2] = "podar";
			}
		}else if(getHabReparar() >= getHabPodar() && getHabReparar() >= getHabLimpiar()){
			habilidades[0] = "reparar";
			if(getHabPodar() >= getHabLimpiar()){
				habilidades[1] = "podar";
				habilidades[2] = "limpiar";
			}else {
				habilidades[1] = "limpiar";
				habilidades[2] = "podar";
			}
		}
	}
	public String getHerramienta(int hab){
		switch (habilidades[hab]){
			case "podar":
				return "Tijeras de podar";
			case "limpiar":
				return  "Escoba";
			case "reparar":
				return "Destornillador";
			default:
				return null;
		}
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

	public void setMinutosTrabajados(Areas Area, int Unidades, int Avanzado) {
		double minutos = 0;
		switch (herramienta.getTrabajo()) {
			case "podar":
				minutos = (double)(Unidades * 60) / (getHabPodar() + herramienta.getMejora());
				break;
			case "limpiar":
				minutos = (double)(Unidades * 60) / (getHabLimpiar() + herramienta.getMejora());
				break;
			case "reparar":
				minutos = (double)(Unidades * 60) / (getHabReparar() + herramienta.getMejora());
				break;
		}
		//System.out.println("Trabajador " + getNombre() + " ha hecho " + herramienta.getTrabajo() + " " + Unidades +" que en total ha hecho " +getUnidadesTrabajadas() + " y ha tardado " + minutos);
		this.minutosTrabajados += Informacion.getCoste(this.area, Area, herramienta.getPeso()) + minutos;
		setUnidadesTrabajadas(Area, Unidades , Avanzado);
	}
	public void setMinutosTrabajados(double MinutosTrabajados){
		this.minutosTrabajados=MinutosTrabajados;
	}

	public void setMinutosTrabajados(String Area){
		this.minutosTrabajados += Informacion.getCoste(this.area , Areas.valueOf(Area), herramienta.getPeso());
	}
	public double getMinutosTrabajados() {
		return minutosTrabajados;
	}


	public boolean isMin(Tarea Tarea){
		if( Tarea.getUnidades() > 0 && Tarea.getTipo().equals(herramienta.getTrabajo()) &&  Tarea.getDisponible(getNombre()) ){
			double minutos = Double.MAX_VALUE;
			Tarea tareaMinima = null;
			for (Tarea tarea : tareas){
				if(tarea.getUnidades() > 0 && tarea.getTipo().equals(herramienta.getTrabajo()) && tarea.getDisponible(getNombre()) ){
					if (Informacion.getCoste(getArea(),tarea.getArea(), herramienta.getPeso()) < minutos){
						tareaMinima = tarea;
						minutos = Informacion.getCoste(getArea(),tarea.getArea(), herramienta.getPeso());
					}
				}
			}
			if(Tarea == tareaMinima){
				Tarea.setAsignada(getNombre());
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean isAvailable(){
		for(Tarea tarea : tareas){
			if(tarea.getUnidades() > 0 && tarea.getTipo().equals(herramienta.getTrabajo()) && tarea.getDisponible(getNombre()))
				return true;
		}
		return false;
	}

	public void setTareas(ArrayList<Tarea> Tareas){
		this.tareas = Tareas;
	}

	public void setUnidadesTrabajadas(Areas Area, int Unidades, int Avanzado){
		unidadesTrabajadas += Unidades;
		if(Avanzado == 1){
			if(herramienta.getTrabajo().equals("podar")){//Si la tarea es de tipo poda
				for (Tarea tarea : tareas ){//recorremos todas las tareas
					if(tarea.getArea().equals(Area) && tarea.getTipo().equals("limpiar")){//Si se encuentra en el mismo area y es de tipo limpiar suma una unidad
						tarea.setUnidades(tarea.getUnidades() + Unidades);
						break;
					}
				}
			}
		}
	}

	public void setUnidadesTrabajadas(int UnidadesTrabajadas) {
		this.unidadesTrabajadas = UnidadesTrabajadas;
	}

	public int getUnidadesTrabajadas(){
		return unidadesTrabajadas;
	}

	public void setHerramienta(Herramienta Herramienta) {
		this.herramienta = Herramienta;
	}
	public void setHerramienta(){
		this.herramienta = null;
	}
	public Herramienta getHerramienta() {
		return herramienta;
	}

}
