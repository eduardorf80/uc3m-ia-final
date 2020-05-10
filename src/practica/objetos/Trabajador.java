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
	String nombre; //Nombre del Trabajador
	int habPodar; //Habilidad de poda que tiene
	int habLimpiar; //Habilidad de limpieza que tiene
	int habReparar; //Habilidad de reparación que tiene
	double minutosTrabajados; //Minutos que lleva trabajados
	int unidadesTrabajadas; //Unidades de trabajo realizadas
	Areas area; //Área en el que se encuentra
	public String [] habilidades = new String[3]; //Array de Habilidades ordenada de mayor a menor habilidad
	ArrayList<Tarea> tareas; //ArrayList que apunta a la lista de tareas principal. Utilizamos esto para poder acceder a la lista de tareas desde la clase trabajador.
	Herramienta herramienta; //Herramienta que tiene asignada

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Trabajador(String nombre, int habPodar, int habLimpiar, int habReparar) {
		this.nombre      = nombre;
		this.habPodar    = habPodar;
		this.habLimpiar  = habLimpiar;
		this.habReparar  = habReparar;

		//Inicializamos las variables que no venían en el código inicial dado
		setArea("A"); //Hacemos que el trabajador empiece en el almacén
		setHabilidades();
		this.minutosTrabajados = 0;
		this.unidadesTrabajadas = 0;
		setHerramienta();
	}
	
	/**
	 * Añadir (si procede) métodos auxiliares, como getters o setters
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

	/*En este método hacemos una simple comparación de las distintas habilidades de un trabajador,
	 y las insertamos en orden de mayor a menor en la matriz "habilidades"*/
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

	/*Este método devuelve la utilidad de una herramienta cuando se le pasa
	su posición en la matriz de habilidades*/
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

	//Este método calcula los minutos trabajados por el trabajador.
	public void setMinutosTrabajados(Areas Area, int Unidades, int Avanzado) {
		double minutos = 0;
		switch (herramienta.getTrabajo()) {
			//Calculamos cuanto va a tardar en minutos en hacer una tarea dependiendo de su habilidad, y de su peso si es el problema avanzado
			case "podar":
				minutos = (double)(Unidades * 60) / (getHabPodar() + ((Avanzado == 1) ? herramienta.getMejora() : 0));
				break;
			case "limpiar":
				minutos = (double)(Unidades * 60) / (getHabLimpiar() + ((Avanzado == 1) ? herramienta.getMejora() : 0));
				break;
			case "reparar":
				minutos = (double)(Unidades * 60) / (getHabReparar() + ((Avanzado == 1) ? herramienta.getMejora() : 0));
				break;
		}
		//Le sumamos a los minutos totales trabajados el tiempo de desplazamiento y el tiempo en realizar las tareas.
		this.minutosTrabajados += Informacion.getCoste(this.area, Area, ((Avanzado == 1) ? herramienta.getPeso() : 0)) + minutos;
		//Sumamos las unidades trabajadas a las totales, y generamos una tarea de limpieza si el problema es avanzado y es una tarea de poda
		setUnidadesTrabajadas(Area, Unidades , Avanzado);
	}

	public void setMinutosTrabajados(double MinutosTrabajados){
		this.minutosTrabajados=MinutosTrabajados;
	}

	//Calcula los minutos que se tardan en ir de donde está el trabajador al área que se pase como parámetro, y se lo suma a los minutos trabajados totales.
	public void setMinutosTrabajados(String Area, int Avanzado){
		this.minutosTrabajados += Informacion.getCoste(this.area , Areas.valueOf(Area), ((Avanzado == 1) ? herramienta.getPeso() : 0));
	}

	public double getMinutosTrabajados() {
		return minutosTrabajados;
	}

	// El metodo te dice si la tarea que le pasas es la mas cercana al trabajador
	public boolean isMin(Tarea Tarea){
		//La tarea tiene que tener unidad restantes, ser del mismo tipo que la herramienta y tiene que estar disponible para ese trabajador
		if( Tarea.getUnidades() > 0 && Tarea.getTipo().equals(herramienta.getTrabajo()) &&  Tarea.getDisponible(getNombre()) ){
			//Para hallar el minimo de creamos una variable con su valor maximo
			double minutos = Double.MAX_VALUE;
			//Creamos una tarea para posteriormente guardar la minima
			Tarea tareaMinima = null;
			//Recorremos todas las tareas
			for (Tarea tarea : tareas){
				//comprobamos que tenga unidades restantes, que sea del tipo correacto y que este disponible para el trabajdor
				if(tarea.getUnidades() > 0 && tarea.getTipo().equals(herramienta.getTrabajo()) && tarea.getDisponible(getNombre()) ){
					//Si el coste de desplazarse es menor que la que ya tenemos como minima se actualiza la minima
					if(Informacion.getCoste(getArea(),tarea.getArea(), herramienta.getPeso()) < minutos){
						tareaMinima = tarea;
						minutos = Informacion.getCoste(getArea(),tarea.getArea(), herramienta.getPeso());
					}
				}
			}
			//Comprobamos que la que nos han pasado es la minima
			if(Tarea == tareaMinima){
				//Si lo es se la asignamos al trabajador y devolvemos true
				Tarea.setAsignada(getNombre());
				return true;
			}
			return false;
		}
		return false;
	}
	//Devuelve true si hay alguna tarea disponible para el trabjador
	//Es decir que es del mismo tipo que la herramienta, tiene unidades restantes y esta disponible para ese trabajador
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
	//EStablece las unidades trabajadas
	public void setUnidadesTrabajadas(Areas Area, int Unidades, int Avanzado){
		unidadesTrabajadas += Unidades;
		//Si es avanzado el problema tiene que sumar una unidad de limpieza donde halla podado
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
