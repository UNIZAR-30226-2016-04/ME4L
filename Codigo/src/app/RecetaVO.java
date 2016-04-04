package app;

import java.util.ArrayList;

public class RecetaVO {

	private String id;
	private String nombre;
	private String descripcion;
	private String plato;
	private String numPersonas;
	private String validada;
	private ArrayList<String> ingredientes;
	private ArrayList<String> pesoIngredientes;
	
	public RecetaVO () {
		
	}
	
	public RecetaVO (String nombre, String descripcion, String plato, String numPersonas,
			ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plato = plato;
		this.numPersonas = numPersonas;
		this.ingredientes = ingredientes;
		this.pesoIngredientes = pesoIngredientes;
	}
	
	public RecetaVO (String id, String nombre, String descripcion, String plato, String numPersonas,
			String validada, ArrayList<String> ingredientes, ArrayList<String> pesoIngredientes) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plato = plato;
		this.numPersonas = numPersonas;
		this.validada = validada;
		this.ingredientes = ingredientes;
		this.pesoIngredientes = pesoIngredientes;
	}
	
	public String getId () {
		return this.id;
	}
	
	public void setId (String nuevo) {
		this.id = nuevo;
	}
	
	public String getNombre () {
		return this.nombre;
	}
	
	public void setNombre (String nuevo) {
		this.nombre = nuevo;
	}
	
	public String getDescripcion () {
		return this.descripcion;
	}
	
	public void setDescripcion (String nuevo) {
		this.descripcion = nuevo;
	}
	
	public String getPlato () {
		return this.plato;
	}
	
	public void setPlato (String nuevo) {
		this.plato = nuevo;
	}
	
	public String getNumPersonas () {
		return this.numPersonas;
	}
	
	public void setNumPersonas (String nuevo) {
		this.numPersonas = nuevo;
	}
	
	public String getValidada () {
		return this.validada;
	}
	
	public void setValidada (String nuevo) {
		this.validada = nuevo;
	}
	
	public ArrayList<String> getIngredientes() {
		return this.ingredientes;
	}
	
	public void setIngredientes (ArrayList<String> nuevo) {
		this.ingredientes = nuevo;
	}
	
	public ArrayList<String> getPesoIngredientes() {
		return this.pesoIngredientes;
	}
	
	public void setPesoIngredientes(ArrayList<String> nuevo) {
		this.pesoIngredientes = nuevo;
	}
}
