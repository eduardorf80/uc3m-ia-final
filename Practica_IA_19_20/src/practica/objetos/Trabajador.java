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
	int minutosTrabajados;
	// Nuestras variables
	Tipos_Herramientas herr;
	Areas area;
	public String [] habilidades = new String[3];
	ArrayList<Tarea> tareas;

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
		setHabilidades();
		this.minutosTrabajados = 0;
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
	public String getHabMax(int Pos){
		return habilidades[Pos];
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

	public void setHerramienta(String Herr){
		herr = Tipos_Herramientas.valueOf(Herr);
	}
	public void setHerramienta(){
		herr = null;
	}
	public Tipos_Herramientas getHerramienta() {
		return herr;
	}
	public String getHerramienta(int hab){
		switch (habilidades[hab]){
			case "podar":
				return "Tijeras_de_podar";
			case "limpiar":
				return  "Escoba";
			case "reparar":
				return "Destornillador";
			default:
				return null;
		}
	}
	public String getUso() {
		switch (this.herr) {
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

	public void setMinutosTrabajados(Areas Area, int Unidades){
		int minutos = 0 ;
		switch (getUso()){
			case  "podar":
				minutos = (int)Math.ceil((float)(Unidades*60/getHabPodar()));
				break;
			case  "limpiar":
				minutos =(int)Math.ceil((float)(Unidades*60/getHabLimpiar()));
				break;
			case  "reparar":
				minutos =(int)Math.ceil((float)(Unidades*60/getHabReparar()));
				break;
		}
		System.out.println("Trabajador " +getNombre() +" ha hecho " + getUso()+" "+ Unidades + " y ha tardado " + minutos);
		this.minutosTrabajados += Informacion.getCoste(this.area , Area) + minutos;
	}

	public void setMinutosTrabajados(String Area){
		this.minutosTrabajados += Informacion.getCoste(this.area , Areas.valueOf(Area));
	}
	public int getMinutosTrabajados() {
		return minutosTrabajados;
	}

	/*
	public boolean isMin(Tarea Tarea){
		int coste = Integer.MAX_VALUE;
		Tarea minima = null;
		// Tarea.getAsignada(getNombre()) ||
		if( Tarea.getUnidades() <= 0 || !Tarea.getTipo().equals(getUso())){
			if(getNombre().equals("Bernardo")){
				System.out.print("");
			}
			return false;
		}
		for(Tarea tarea : this.tareas){
			if (!Tarea.getAsignada(getNombre()) && (tarea.getUnidades() > 0) && tarea.getTipo().equals(getUso()) && (Informacion.getCoste(getArea(), tarea.getArea()) < coste)) {
					coste = Informacion.getCoste(getArea(), tarea.getArea());
					minima = tarea;
				}
		}
		return Tarea.getArea().equals(minima.getArea());
	}
	*/

	public boolean isMin(Tarea Tarea){
		if( Tarea.getUnidades() > 0 && Tarea.getTipo().equals(getUso()) &&  Tarea.getDisponible(getNombre()) ){
			int minutos = Integer.MAX_VALUE;
			Tarea tareaMinima = null;
			for (Tarea tarea : tareas){
				if(tarea.getUnidades() > 0 && tarea.getTipo().equals(getUso()) && tarea.getDisponible(getNombre()) ){
					if (Informacion.getCoste(getArea(),tarea.getArea()) < minutos){
						tareaMinima = tarea;
						minutos = Informacion.getCoste(getArea(),tarea.getArea());
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

	public void setTareas(ArrayList<Tarea> Tareas){
		this.tareas = Tareas;
	}
}
