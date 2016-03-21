package app;

public class RecetaVO {

	private String id;
	private String nombre;
	private String descripcion;
	private String plato;
	private String numPersonas;
	
	public RecetaVO () {
		
	}
	
	public RecetaVO (String nombre, String descripcion, String plato, String numPersonas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plato = plato;
		this.numPersonas = numPersonas;
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
}
