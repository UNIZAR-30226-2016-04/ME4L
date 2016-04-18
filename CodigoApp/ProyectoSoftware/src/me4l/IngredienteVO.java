package me4l;

public class IngredienteVO {
	
	private String nombre;
	private String tipo;
	
	public IngredienteVO() {
		
	}
	
	public IngredienteVO(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public String getNombre () {
		return this.nombre;
	}
	
	public void setNombre (String nuevoNombre) {
		this.nombre = nuevoNombre;
	}
	
	public String getTipo () {
		return this.tipo;
	}
	
	public void setTipo (String nuevoTipo) {
		this.tipo = nuevoTipo;
	}
}
