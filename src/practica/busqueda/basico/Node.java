package practica.busqueda.basico;

import java.util.ArrayList;

import practica.objetos.*;

/**
 * Clase creada como base para la parte 2 de la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 *
 */

public class Node {
	private double cost;							// Valor del coste de llegada al nodo
	private double heuristic;						// Valor de la heur�stica del nodo
	private double evaluation;						// Valor de la evaluaci�n
	private Node parent;							// Nodo padre del arbol A*
	private Node nextNodeList = null;				// Para la gesti�n de la lista
	ArrayList<Herramienta> herramientas;
	ArrayList<Trabajador>  trabajadores;
	ArrayList<Tarea>       tareas;
	Debugger debugger;
	// A�adir m�s variables si se desea

	/**
	 * MODIFICAR
	 * Constructor para introducir un nuevo nodo en el algoritmo A estrella
	 */
	public Node(Node parentNode, ArrayList<Herramienta> herramientas, ArrayList<Trabajador> trabajadores, ArrayList<Tarea> tareas) {
		this.parent       = parentNode;  // padre en el �rbol A*
		this.herramientas = herramientas;
		this.trabajadores = trabajadores;
		this.tareas       = tareas;
		this.debugger = new Debugger(herramientas,trabajadores,tareas);
		// A�adir m�s variables si se desea
	}

	/**
	 * MODIFICAR
	 * Constructor auxiliar para la implementaci�n del algoritmo. Genera una copia de un nodo para introducirla en la OpenList
	 */
	public Node(Node original) {
		// Incluir todas las variables del nodo
		this.cost        = original.cost;
		this.heuristic   = original.heuristic;
		this.evaluation   = original.evaluation;
		this.parent       = original.parent;
		this.nextNodeList = original.nextNodeList;
		// A�adir m�s variables si se desea

		// Se copian los objetos de los ArrayList a uno nuevo de este Nodo
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
		ArrayList<Herramienta> herramientas = new ArrayList<Herramienta>();
		for (int i = 0; i < original.herramientas.size(); i++) {
			Herramienta herramienta = new Herramienta(original.herramientas.get(i).getNombre(), original.herramientas.get(i).getTrabajo(), original.herramientas.get(i).getPeso(), original.herramientas.get(i).getMejora(), original.herramientas.get(i).getCantidad());
			//Copiamos el valor de herramientas disponibles (aunque en el basico no se usa)
			herramienta.setDisponibles(original.herramientas.get(i).getDisponibles());
			herramientas.add(herramienta);
		}
		this.herramientas = herramientas;
		ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
		for (int i = 0; i < original.trabajadores.size(); i++) {
			Trabajador trabajador = new Trabajador(original.trabajadores.get(i).getNombre(), original.trabajadores.get(i).getHabPodar(), original.trabajadores.get(i).getHabLimpiar(), original.trabajadores.get(i).getHabReparar());
			//Seteamos la herramienta del trabajador, para eso tenemos que buscarla en el nuevo array de herramientas para que no apunte al array viejo
			for(int j = 0 ; j<original.herramientas.size(); j++){
				if(original.herramientas.get(j) == original.getTrabajadores().get(i).getHerramienta()){
					trabajador.setHerramienta(herramientas.get(j));
					break;
				}
			}
			//Copiamos los minutos trabajados las unidades y el area del trabajador
			trabajador.setMinutosTrabajados(original.trabajadores.get(i).getMinutosTrabajados());
			trabajador.setUnidadesTrabajadas(original.trabajadores.get(i).getUnidadesTrabajadas());
			trabajador.setArea(original.trabajadores.get(i).getArea());
			trabajadores.add(trabajador);
		}
		this.trabajadores = trabajadores;
		//Las tareas no hay que copiar nada nuevo ya que no las hemos tocado
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		for (int i = 0; i < original.tareas.size(); i++) {
			Tarea tarea = new Tarea(original.tareas.get(i).getTipo(), original.tareas.get(i).getArea().toString(), original.tareas.get(i).getUnidades());
			tareas.add(tarea);
		}
		this.tareas = tareas;
		this.debugger = new Debugger(herramientas,trabajadores,tareas);
	}

	/**
	 * Constructor auxiliar para generar el primer nodo de la lista abierta
	 */
	public Node() {	}

	/**
	 *  Calcula el valor de la heuristica del problema para el nodo 
	 *  MODIFICAR
	 * @param finalNode - El nodo sobre el que calcular la heur�stica
	 * this.heuristica  - Resultado
	 */
	public void computeHeuristic(Node finalNode) {
		this.heuristic = 0;
		//Calculamos la estimacion de tiempo que nos va a llevar realizar las tareas restantes
		for(Tarea tarea : tareas){
			switch (tarea.getTipo()) {
				case "podar":
					this.heuristic += (double) (tarea.getUnidades() * 60) / trabajadores.get(0).getHabPodar();
					break;
				case "limpiar":
					this.heuristic += (double) (tarea.getUnidades() * 60) / trabajadores.get(0).getHabLimpiar();
					break;
				case "reparar":
					this.heuristic += (double) (tarea.getUnidades() * 60) / trabajadores.get(0).getHabReparar();
					break;
			}
		}
		//this.heuristic += Informacion.getCoste(trabajadores.get(0).getArea(), Areas.A, 0);
	}

	/**
	 * Comprobaci�n de que la informaci�n de un nodo es equivalente a la de otro nodo
	 * Solo comparar la informaci�n necesaria para ver si es el mismo estado del problema
	 *
	 * @param other - el nodo con el que comparar this
	 * @return true: son iguales. false: no lo son
	 */
	public boolean equals(Node other) {
		//Comparamos que el numero de herramientas disponible sea igual(aunque en el problema basico no se tiene en cuenta el numero de herramientas)
		for(int indice = 0; indice < herramientas.size(); indice++){
			if(herramientas.get(indice).getDisponibles() != other.getHerramientas().get(indice).getDisponibles()) return false;
		}
		//Se comprueba las unidades de las tareas
		for(int indice = 0; indice < tareas.size(); indice++){
			if(tareas.get(indice).getUnidades() != other.getTareas().get(indice).getUnidades()) return false;
		}
		//comprobamos el area
		//comprobamos que el tipo de herramienta sea el mismo
		for(int indice = 0; indice < trabajadores.size(); indice++){
			if(trabajadores.get(indice).getArea() != other.getTrabajadores().get(indice).getArea()) return false;
			if(trabajadores.get(indice).getHerramienta() == null ^ other.getTrabajadores().get(indice).getHerramienta() == null ){
				return false;
			}else if(trabajadores.get(indice).getHerramienta() != null && other.getTrabajadores().get(indice).getHerramienta() !=null){
				if(!trabajadores.get(indice).getHerramienta().getNombre().equals(other.getTrabajadores().get(indice).getHerramienta().getNombre()))
					return false;
			}
		}
		return true;
	}


	/**
	 * Impresi�n de la informaci�n del nodo
	 * @param printDebug . Permite seleccionar cu�ntos mensajes imprimir
	 */
	public void printNodeData(int printDebug) {
		//Comprobamos cuanta informacion quiere, si es 0 no hacemos nada
		switch (printDebug){
			//Imprimimos la informacion del nodo, y hacemos uso del debugger para imprimir el estado de los trabajadores
			case 1:
				System.out.println("\u001B[31m" + "El nodo actual tiene un coste de " + this.getCost() + " una heuristica de " + this.getHeuristic() + " que resulta en un evaluacion de " + this.getEvaluation() + "\u001B[0m");
				getDebugger().printTrabajadores();
				break;
			//Imprimimos toda la informacion anterior mas la informacion de las tareas y las herramientas
			case 2:
				System.out.println("\u001B[31m" + "El nodo actual tiene un coste de " + this.getCost() + " una heuristica de " + this.getHeuristic() + " que resulta en un evaluacion de " + this.getEvaluation() + "\u001B[0m");
				getDebugger().printTrabajadores();
				getDebugger().printHerramientas();
				getDebugger().printTareas(true);
				break;
		}
	}

	/**
	 * Ejecuta la funci�n de evaluacion del problema para el nodo. IMPORTANTE: ejecutar despu�s de el c�lculo del coste y heur�stica
	 */
	public void computeEvaluation() {
		this.evaluation = this.cost + this.heuristic;
	}

	/**** Getters y Setters ****/
	/**
	 * MODIFICAR si se considera necesario. No es imprescindible, solo si consideras que puede ayudar a tu implementaci�n
	 */
	public double getEvaluation() {
		return evaluation;
	}
	public ArrayList<Herramienta> getHerramientas() {
		return herramientas;
	}
	public void setHerramientas(ArrayList<Herramienta> herramientas) {
		this.herramientas = herramientas;
	}
	public ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}
	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}
	public void setEvaluation(double evaluacion) {
		this.evaluation = evaluacion;
	}
	public double getCost() {
		return cost;
	}
	public void setCoste(double coste) {
		this.cost = coste;
	}
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristica) {
		this.heuristic = heuristica;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getNextNode() {
		return nextNodeList;
	}
	public void setNextNode(Node nextNode) {
		this.nextNodeList = nextNode;
	}

	public Debugger getDebugger() {
		return debugger;
	}
}